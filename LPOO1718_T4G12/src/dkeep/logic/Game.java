package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lever;
import dkeep.logic.Door;
import java.util.Random;
import java.util.ArrayList;

public class Game {

	public static enum GameState { PLAYING, GAMEOVER, VICTORY };

	//Global data members
	public static GameState gameState = GameState.PLAYING;
	public static int LEVEL = 1;

	//Generic Data members
	public GameMap map;
	public Hero hero;
	public static Door[] door;
	public Lever lever;

	//Guard data members
	public int guardRouting;
	public Guard[] guard;

	//Ogre data members
	public ArrayList<Ogre> ogre;
	public int horde = 2;

	public Game() {

		map = new GameMap();

		hero = new Hero();
		lever = new Lever();

		if(LEVEL == 1) {

			//Variables
			Random rand = new Random();
			int[][] coordinates = { {1,4}, {3,2}, {3,4}, {5,0}, {6,0}, {8,2}, {8,4} };
			guardRouting = rand.nextInt(3);

			//Create 3 different types of guard
			guard = new Guard[3];
			guard[0] = new Guard("Rookie");
			guard[1] = new Guard("Drunken");
			guard[2] = new Guard("Suspicious");

			door = new Door[coordinates.length];

			for(int i = 0; i < coordinates.length; i++) {
				door[i] = new Door(coordinates[i]);
			}

		}
		else {

			//Create a certain amount of ogres
			ogre = new ArrayList<Ogre>(horde);

			for(int i = 0; i < horde; i++)
				ogre.add(new Ogre());

			door = new Door[1];
			door[0] = new Door(new int[] {1,0});
		}

	}

	public void checkGameStatus() {

		if(Game.LEVEL == 1) {
			if((guard[guardRouting].x == hero.x) && (guard[guardRouting].state == 'G') && 
					((guard[guardRouting].y == (hero.y + 1)) || (guard[guardRouting].y == (hero.y - 1)))) {
				gameState = GameState.GAMEOVER;
			}
			else if((guard[guardRouting].y == hero.y) && (guard[guardRouting].state == 'G') &&
					( (guard[guardRouting].x == (hero.x + 1)) || (guard[guardRouting].x == (hero.x - 1)))) {
				gameState = GameState.GAMEOVER;
			}
		}
		else if(Game.LEVEL == 2)
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

		for(int i = 0; i < door.length; i++)
			tmpMap[door[i].x][door[i].y] = door[i].state;

		if(Game.LEVEL == 1) {
			tmpMap[guard[guardRouting].x][guard[guardRouting].y] = guard[guardRouting].state;

			if(!((hero.x == lever.x) && (hero.y == lever.y)))
				tmpMap[lever.x][lever.y] = Lever.leverState;
		}
		else {
			for(int i = 0; i < horde; i++) 
			{
				tmpMap[ogre.get(i).x][ogre.get(i).y] = ogre.get(i).state;
				if(tmpMap[ogre.get(i).clubX][ogre.get(i).clubY] != 'O')
					tmpMap[ogre.get(i).clubX][ogre.get(i).clubY] = ogre.get(i).club;
			}

			if(tmpMap[lever.x][lever.y] == ' ')
				tmpMap[lever.x][lever.y] = Lever.leverState;
		}

		return tmpMap;
	}
}
