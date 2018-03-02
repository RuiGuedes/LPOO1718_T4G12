package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lock;
import dkeep.logic.Door;
import java.util.Random;
import java.util.ArrayList;

public class Game {

	public static enum GameState { PLAYING, GAMEOVER, VICTORY };

	//Global data members
	public static GameState gameState = GameState.PLAYING;
	public static int LEVEL = 0;

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

	public Game(GameMap gameMap) {
		map = gameMap;
		
		initElements();
	}

	public void initElements() {
		
		char[][] tmpMap = map.getMap();
	
		door = new ArrayList<Door>();
		ogre = new ArrayList<Ogre>();
		
		for(int i = 0; i < tmpMap.length; i++)
		{
			for(int j = 0; j < tmpMap[i].length; j++) 
			{
				if(tmpMap[i][j] == 'H') {
					tmpMap[i][j] = ' ';
					hero = new Hero(i,j,'H');
				}
				else if(tmpMap[i][j] == 'A') {
					hero = new Hero(i,j,'A');
				}
				else if(tmpMap[i][j] == 'k') {
					if(Game.LEVEL == 1)
						lock = new Lock(i,j,false);
					else if(Game.LEVEL == 2)
						lock = new Lock(i,j,true);
					else	//Allows to choose the type of lock by changing it lockType
						lock = new Lock(i,j,false);
				}
				else if(tmpMap[i][j] == 'G') {
					if(LEVEL == 1) {

						char[] guardRoute = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 
								'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'}; 

						Random rand = new Random();
						guardRouting = rand.nextInt(3);
					
						//Create 3 different types of guard
						guard = new Guard[3];
						guard[0] = new Guard(i,j,"Rookie",guardRoute);
						guard[1] = new Guard(i,j,"Drunken",guardRoute);
						guard[2] = new Guard(i,j,"Suspicious",guardRoute);
					}
					else {
						guard = new Guard[1];
						guardRouting = 0;
						guard[guardRouting] = new Guard(i,j,"Rookie", null);
					}
				}
				else if(tmpMap[i][j] == 'I') {
					door.add(new Door(i,j,'I'));
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
					
					ogre.add(new Ogre(i,j,tmpX,tmpY));
				}

				if(tmpMap[i][j] != 'X')
					tmpMap[i][j] = ' ';
			}
		}
		
		map.setMap(tmpMap);
	}

	public void checkGameStatus(String gameType) {

		if(gameType == "Guard") {
			if((guard[guardRouting].x == hero.x) && (guard[guardRouting].state == 'G') && 
					((guard[guardRouting].y == (hero.y + 1)) || (guard[guardRouting].y == (hero.y - 1)))) {
				gameState = GameState.GAMEOVER;
			}
			else if((guard[guardRouting].y == hero.y) && (guard[guardRouting].state == 'G') &&
					( (guard[guardRouting].x == (hero.x + 1)) || (guard[guardRouting].x == (hero.x - 1)))) {
				gameState = GameState.GAMEOVER;
			}
		}
		else if(gameType == "Ogre")
		{	
			for(int i = 0; i < horde; i++) 
			{
				if( ((ogre.get(i).x == hero.x) && ((ogre.get(i).y == (hero.y + 1)) || (ogre.get(i).y == (hero.y - 1)))) ||
						((ogre.get(i).y == hero.y) && ((ogre.get(i).x == (hero.x + 1)) || (ogre.get(i).x == (hero.x - 1)))))
				{
					if((ogre.get(i).state == 'O') || ((ogre.get(i).clubX == hero.x) && (ogre.get(i).clubY == hero.y)))
						gameState = GameState.GAMEOVER;
				}

			}
		}
	}

	public char[][] updateMap(char[][] tmpMap) {

		tmpMap[hero.x][hero.y] = hero.state;

		for(int i = 0; i < door.size(); i++)
			tmpMap[door.get(i).x][door.get(i).y] = door.get(i).state;

		if(Game.LEVEL == 1) {
			tmpMap[guard[guardRouting].x][guard[guardRouting].y] = guard[guardRouting].state;

			if(!((hero.x == lock.x) && (hero.y == lock.y)))
				tmpMap[lock.x][lock.y] = Lock.lockState;
		}
		else {
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
