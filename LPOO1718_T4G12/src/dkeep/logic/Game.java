package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lever;
import dkeep.logic.Door;

public class Game {
	
	public static enum GameState { PLAYING, GAMEOVER, VICTORY };
	
	public static GameState gameState = GameState.PLAYING;
	public static int LEVEL;
	
	public GameMap map;
	public Hero hero;
	public Guard guard;
	public Ogre ogre;
	public Door[] door;
	public Lever lever;
	
	public Game() {
		
		map = new GameMap();
		
		hero = new Hero();
		lever = new Lever();
		
		if(LEVEL == 1) {
			
			int[][] coordinates = { {1,4}, {3,2}, {3,4}, {5,0}, {6,0}, {8,2}, {8,4} };
			
			guard = new Guard();
			
			door = new Door[coordinates.length];
			
			for(int i = 0; i < coordinates.length; i++) {
				door[i] = new Door(coordinates[i]);
			}
				
		}
		else {
			ogre = new Ogre();
			
			door = new Door[1];
			door[0] = new Door(new int[] {1,0});
		}
		
	}
	
	public char[][] updateMap(char[][] tmpMap) {
		
		if((hero.x == lever.x) && (hero.y == lever.y))
			tmpMap[hero.x][hero.y] = 'H';
		else {
			tmpMap[hero.x][hero.y] = 'H';
			tmpMap[lever.x][lever.y] = Lever.leverState;
		}
		
		for(int i = 0; i < door.length; i++)
			tmpMap[door[i].x][door[i].y] = door[i].state;
		
		if(Game.LEVEL == 1)
			tmpMap[guard.x][guard.y] = 'G';
		else { 
			tmpMap[ogre.x][ogre.y] = ogre.state;
			tmpMap[ogre.clubX][ogre.clubY] = ogre.club;
		}
		
		return tmpMap;
	}
	
	public Door[] openDoors(Door[] tmpDoor) {
		
		for(int i = 0; i < tmpDoor.length; i++) {
			
			if((i == 3) || (i == 4))
				tmpDoor[i].state = 'S';
			else
				tmpDoor[i].state = ' ';
		}
		
		return tmpDoor;
	}
	
}
