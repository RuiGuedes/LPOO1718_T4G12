package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lock;
import dkeep.logic.Door;
import java.util.ArrayList;

/**
 * Game.java - master class that list the entire game and contain all the components of the game
 * @author Rui Guedes and César Pinho
 * @see Door
 * @see Drunken
 * @see Elements
 * @see GameMap
 * @see Guard
 * @see Hero
 * @see Lock
 * @see Ogre
 * @see Rookie
 * @see Suspicious
 */
public class Game {

	public static enum GameState { PLAYING, GAMEOVER, VICTORY };

	//Global data members
	public static GameState gameState;
	public static int LEVEL = 0; // 1 or 2

	//Generic Data members
	public GameMap map; 
	public Hero hero;
	public static ArrayList<Door> door;
	public Lock lock; 

	//Guard data members
	public int guardRouting;
	public Guard[] guard;

	//Ogre data members
	public ArrayList<Ogre> ogre;
	public int horde = 1;

	/**
	 * Class constructor specifying the game map, guard type and the number of ogres.
	 * Initialize the gameState and map attributes and call the initElements method to initializer the other attributes.  
	 * 
	 * @param gameMap map of the game
	 * @param guardType initial guard type of the first level 
	 * @param ogresNumber number of ogres of the second level
	 */
	public Game(GameMap gameMap, String guardType, int ogresNumber) {
		Game.gameState = GameState.PLAYING;
		map = gameMap;
		Lock.lockState = 'k';
		door = new ArrayList<Door>();
		ogre = new ArrayList<Ogre>();
		horde = ogresNumber;
		initElements(guardType,ogresNumber);
	}

	/**
	 * Initialize the stable and unstable pieces calling the functions initStableElements and initUnstableElements.
	 * 
	 * @param guardType	guard type that needs to be create
	 * @param ogresNumber number of ogres that needs to be create
	 */
	public void initElements(String guardType, int ogresNumber) {

		char[][] tmpMap = map.getMap();

		for(int i = 0; i < tmpMap.length; i++) {
			for(int j = 0; j < tmpMap[i].length; j++) {
				initStableElements(new Elements(i,j), tmpMap);

				initUnstableElements(new Elements(i,j), guardType, ogresNumber);

				if(tmpMap[i][j] != 'X')
					tmpMap[i][j] = ' ';
			}
		}
		map.setMap(tmpMap);
	}

	/**
	 * Initialize the lock and door type elements calling the functions initLock and initDoor.
	 * @param position coordinates of the element
	 * @param tmpMap game map
	 */
	public void initStableElements(Elements position, char[][] tmpMap) {
		
		if(tmpMap[position.x][position.y] == 'k') 
			initLock(position);
		
		else if(tmpMap[position.x][position.y] == 'I') 
			initDoors(position,tmpMap);
	}

	/**
	 * Initialize the hero and the guard and ogre type elements calling the functions initGuard and initOgre.
	 * @param position coordinates of the element
	 * @param guardType initial guard type of the first level 
	 * @param ogresNumber number of ogres of the second level
	 */
	public void initUnstableElements(Elements position, String guardType, int ogresNumber) {
		char[][] tmpMap = map.getMap();

		if((tmpMap[position.x][position.y] == 'H') || (tmpMap[position.x][position.y] == 'A')) 
			hero = new Hero(position,tmpMap[position.x][position.y]);

		else if(tmpMap[position.x][position.y] == 'G') 
			initGuard(position, guardType);

		else if(tmpMap[position.x][position.y] == 'O') 
			initOgres(position, ogresNumber);
	}	

	/**
	 * Create a new lock of the lever type if is the level 1 or the key type otherwise
	 * @param position coordinates of the element
	 */
	public void initLock(Elements position) {
		if(Game.LEVEL == 1)
			lock = new Lock(position,false);
		else
			lock = new Lock(position,true);
	}

	/**
	 * Create a new guard take in account the type of guard.
	 * @param position coordinates of the element
	 * @param guardType type of guard to be create
	 */
	public void initGuard(Elements position, String guardType) {
		char[] guardRoute = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 
				'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'}; 

		guard = new Guard[3];
		guard[0] = new Rookie(position,guardRoute);
		guard[1] = new Drunken(position,guardRoute);
		guard[2] = new Suspicious(position,guardRoute);

		if(guardType.equals("Rookie"))
			guardRouting = 0;
		else if(guardType.equals("Drunken"))
			guardRouting = 1;
		else if(guardType.equals("Suspicious"))
			guardRouting = 2;
	}

	/**
	 * Create a new door take in account if it is in the map extremes (exit door) or not (normal door).
	 * @param position coordinates of the element
	 * @param tmpMap game map
	 */
	public void initDoors(Elements position, char[][] tmpMap) {
		if((position.x == 0) || (position.y == 0) || 
				(position.x == (tmpMap.length-1)) || (position.y == (tmpMap.length-2))) 
			door.add(new Door(position,'I',true));	//Exit Door
		else
			door.add(new Door(position,'I',false));	//Normal Door
	}

	/**
	 * Call the findClubPosition to find the club and create a ogresNumber of new ogres.
	 * @param position coordinates of the element
	 * @param ogresNumber number of ogres to be create
	 */
	public void initOgres(Elements position, int ogresNumber) {
		char[][] tmpMap = map.getMap();

		Elements clubPos = new Elements(position.x, position.y);

		clubPos = findClubPosition(clubPos, tmpMap);
		
		while(ogresNumber > 0) {
			ogre.add(new Ogre(position,clubPos));
			ogresNumber--;
		}
	}
	
	/**
	 * Find the position around the ogre where is the club.
	 * @param position position of the ogre
	 * @param tmpMap game map
	 * @return Return the position of the club.
	 */
	public Elements findClubPosition (Elements position, char[][] tmpMap) {

		if((tmpMap[position.x+1][position.y]) == '*') 
			position.x++;
		else if((tmpMap[position.x-1][position.y]) == '*')
			position.x--;
		else if((tmpMap[position.x][position.y+1]) == '*')
			position.y++;
		else if((tmpMap[position.x][position.y-1]) == '*')
			position.y--;

		return position;
	}
	
	/**
	 * Check the position of the hero and if he is captured or killed by a guard,
	 * if it is the first level, or a ogre, if it is the second level.
	 * If the hero has been captured, the gameState change to GameOver.
	 */
	public void checkGameStatus() {

		if((Game.LEVEL == 1) && (guard[guardRouting].state == 'G') && guard[guardRouting].checkProximity(hero)) 
			gameState = GameState.GAMEOVER;

		else if(Game.LEVEL == 2)

			for(int i = 0; i < horde; i++) 
				if(( (ogre.get(i).checkProximity(hero))	&& (ogre.get(i).state == 'O')) || 
						(hero.equals(ogre.get(i).clubX, ogre.get(i).clubY)))	{
					
					gameState = GameState.GAMEOVER;
					break;
				}
	}

	/**
	 * Receive a game map and changes it according to the changes previously made to the components of the game, 
	 * for example, when the doors is unlocked is need change the mark that echoes the door in the map.
	 * 
	 * @param tmpMap game map to be updated
	 * @return the updated game map
	 */
	public char[][] updateMap(char[][] tmpMap) {
		tmpMap[hero.x][hero.y] = hero.state;

		for(int i = 0; i < door.size(); i++)
			tmpMap[door.get(i).x][door.get(i).y] = door.get(i).state;

		if(guard != null) 
			tmpMap[guard[guardRouting].x][guard[guardRouting].y] = guard[guardRouting].state;

		else if(ogre.size() != 0)
			for(int i = 0; i < horde; i++) 
			{
				tmpMap[ogre.get(i).x][ogre.get(i).y] = ogre.get(i).state;
				if(tmpMap[ogre.get(i).clubX][ogre.get(i).clubY] != 'O')
					tmpMap[ogre.get(i).clubX][ogre.get(i).clubY] = ogre.get(i).club;
			}	
		
		tmpMap[lock.x][lock.y] = Lock.lockState;
		
		return tmpMap;
	}
}
