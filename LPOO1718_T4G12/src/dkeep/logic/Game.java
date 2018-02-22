package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lever;
import dkeep.logic.Door;

public class Game {
	
	public enum GameState { PLAYING, GAMEOVER, VICTORY };
	public static int level = 1;
	
	public GameMap map;
	public Hero hero;
	public Guard guard;
	public Ogre ogre;
	public Door door[];
	public Lever lever;
	
	public Game(int init) {
		
		map = new GameMap();
		hero = new Hero();
		lever = new Lever();
		
		if(level == 1)
			guard = new Guard();
		else
			ogre = new Ogre();
			
		
	}
	

}
