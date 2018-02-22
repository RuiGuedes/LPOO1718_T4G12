package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lever;
import dkeep.logic.Door;

public class Game {
	
	public enum GameState { PLAYING, GAMEOVER, VICTORY };
	public static int LEVEL = 1;
	
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
			door[0] = new Door(new int[] {1,0});
		}
			
		map.printGameMap(hero, guard);
	}
	

}
