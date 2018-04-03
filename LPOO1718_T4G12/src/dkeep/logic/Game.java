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
		initElements(guardType,ogresNumber);
	}

	/**
	 * Initialize only the necessaries pieces of the gameMap in the right position and quantity. 
	 * For each mark different of blank or wall (X), it is checked which action to perform.
	 *  
	 * @param guardType	guard type that needs to be create
	 * @param ogresNumber number of ogres that needs to be create
	 */
	public void initElements(String guardType, int ogresNumber) {

		char[][] tmpMap = map.getMap();

		Lock.lockState = 'k';
		door = new ArrayList<Door>();
		ogre = new ArrayList<Ogre>();
		horde = ogresNumber;

		for(int i = 0; i < tmpMap.length; i++) {
			for(int j = 0; j < tmpMap[i].length; j++) {
				if((tmpMap[i][j] == 'H') || (tmpMap[i][j] == 'A')) {
					hero = new Hero(i,j,tmpMap[i][j]);
				}
				else if(tmpMap[i][j] == 'k') {
					if(Game.LEVEL == 1)
						lock = new Lock(i,j,false);
					else
						lock = new Lock(i,j,true);
				}
				else if(tmpMap[i][j] == 'G') {
					char[] guardRoute = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 
							'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'}; 

					guard = new Guard[3];
					guard[0] = new Rookie(i,j,guardRoute);
					guard[1] = new Drunken(i,j,guardRoute);
					guard[2] = new Suspicious(i,j,guardRoute);

					if(guardType.equals("Rookie"))
						guardRouting = 0;
					else if(guardType.equals("Drunken"))
						guardRouting = 1;
					else if(guardType.equals("Suspicious"))
						guardRouting = 2;
				}
				else if(tmpMap[i][j] == 'I') {
					if((i == 0) || (j == 0) || (i == (tmpMap.length-1)) || (j == (tmpMap.length-2))) 
						door.add(new Door(i,j,'I',true));	//Exit Door
					else
						door.add(new Door(i,j,'I',false));	//Normal Door
				}
				else if(tmpMap[i][j] == 'O') {

					int tmpX = i;
					int tmpY = j;

					if((tmpMap[i+1][j]) == '*') 
						tmpX++;
					else if((tmpMap[i-1][j]) == '*')
						tmpX--;
					else if((tmpMap[i][j+1]) == '*')
						tmpY++;
					else if((tmpMap[i][j-1]) == '*')
						tmpY--;

					while(ogresNumber > 0) {
						ogre.add(new Ogre(i,j,tmpX,tmpY));
						ogresNumber--;
					}
				}
				if(tmpMap[i][j] != 'X')
					tmpMap[i][j] = ' ';
			}
		}
		map.setMap(tmpMap);
	}

	/**
	 * Check the position of the hero and if he is captured or killed by a guard,
	 * if it is the first level, or a ogre, if it is the second level.
	 * If the hero has been captured, the gameState change to GameOver.
	 */
	public void checkGameStatus() {

		if(Game.LEVEL == 1 && (guard[guardRouting].state == 'G')) {

			int guardX = guard[guardRouting].x, guardY = guard[guardRouting].y;

			if( 	((guardX == hero.x) && ((guardY == (hero.y + 1)) || (guardY == (hero.y - 1)))) || 
					(((guardX == (hero.x + 1)) || (guardX == (hero.x - 1))) && (guardY == hero.y)))
				gameState = GameState.GAMEOVER;

		} 
		else if(Game.LEVEL == 2){
			
			for(int i = 0; i < horde; i++) {
				
				if(( (((ogre.get(i).x == hero.x) && ((ogre.get(i).y == (hero.y + 1)) || (ogre.get(i).y == (hero.y - 1)))) ||
						((ogre.get(i).y == hero.y) && ((ogre.get(i).x == (hero.x + 1)) || (ogre.get(i).x == (hero.x - 1))))) 
						&& (ogre.get(i).state == 'O')) || 
						((ogre.get(i).clubX == hero.x) && (ogre.get(i).clubY == hero.y)))
				{
					gameState = GameState.GAMEOVER;
					break;
				}
			}
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

		if(guard != null) {
			tmpMap[guard[guardRouting].x][guard[guardRouting].y] = guard[guardRouting].state;

			if(!((hero.x == lock.x) && (hero.y == lock.y)))
				tmpMap[lock.x][lock.y] = Lock.lockState;
		}
		else if(ogre.size() != 0) {
			for(int i = 0; i < horde; i++) 
			{
				tmpMap[ogre.get(i).x][ogre.get(i).y] = ogre.get(i).state;
				if(tmpMap[ogre.get(i).clubX][ogre.get(i).clubY] != 'O')
					tmpMap[ogre.get(i).clubX][ogre.get(i).clubY] = ogre.get(i).club;
			}

			if(tmpMap[lock.x][lock.y] == ' ')
				tmpMap[lock.x][lock.y] = Lock.lockState;
		}
		return tmpMap;
	}
}
