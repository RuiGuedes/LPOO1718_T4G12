package dkeep.logic;

import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Lever;
import dkeep.logic.Door;
import java.util.Random;

public class Game {
	
	public static enum GameState { PLAYING, GAMEOVER, VICTORY };
	
	public static GameState gameState = GameState.PLAYING;
	public static int LEVEL = 1;
	
	public GameMap map;
	public Hero hero;
	
	//Guard data members
	public int guardRouting;
	public Guard[] guard;
	
	public Ogre ogre;
	public static Door[] door;
	public Lever lever;
	
	public Game() {
		
		map = new GameMap();
		
		hero = new Hero();
		lever = new Lever();
		
		if(LEVEL == 1) {
			
			//Variables
			Random rand = new Random();
			int[][] coordinates = { {1,4}, {3,2}, {3,4}, {5,0}, {6,0}, {8,2}, {8,4} };
			guardRouting = rand.nextInt(3);
			
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
			ogre = new Ogre();
			
			door = new Door[1];
			door[0] = new Door(new int[] {1,0});
		}
		
	}
	
	public void checkGameStatus() {
		
		if(Game.LEVEL == 1) {
			if(guard[guardRouting].x == hero.x) {
				if(((guard[guardRouting].y == (hero.y + 1)) && (guard[guardRouting].state == 'G')) || 
					((guard[guardRouting].y == (hero.y - 1)) && (guard[guardRouting].state == 'G'))) {
					gameState = GameState.GAMEOVER;
				}
			}
			else if(guard[guardRouting].y == hero.y) {
				if(((guard[guardRouting].x == (hero.x + 1)) &&  (guard[guardRouting].state == 'G')) ||
					((guard[guardRouting].x == (hero.x - 1)) &&  (guard[guardRouting].state == 'G'))) {
					gameState = GameState.GAMEOVER;
				}
			}
		}
		else if(Game.LEVEL == 2)
		{
			if(ogre.x == hero.x) {
				if((ogre.y == (hero.y + 1)) || (ogre.y == (hero.y - 1))) {
					gameState = GameState.GAMEOVER;
				}
			}
			else if(ogre.y == hero.y) {
				if((ogre.x == (hero.x + 1)) || (ogre.x == (hero.x - 1))) {
					gameState = GameState.GAMEOVER;
				}
			}
		}
	}
	
	public char[][] updateMap(char[][] tmpMap) {
		
		tmpMap[hero.x][hero.y] = 'H';
		
		for(int i = 0; i < door.length; i++)
			tmpMap[door[i].x][door[i].y] = door[i].state;
		
		if(Game.LEVEL == 1) {
			tmpMap[guard[guardRouting].x][guard[guardRouting].y] = guard[guardRouting].state;
			
			if(!((hero.x == lever.x) && (hero.y == lever.y)))
				tmpMap[lever.x][lever.y] = Lever.leverState;
		}
		else {
			
			tmpMap[ogre.x][ogre.y] = ogre.state;
			tmpMap[ogre.clubX][ogre.clubY] = ogre.club;
			
			if((!((hero.x == lever.x) && (hero.y == lever.y))) && (!((ogre.x == lever.x) && (ogre.y == lever.y))) &&
					(!((ogre.clubX == lever.x) && (ogre.clubY == lever.y))))
				tmpMap[lever.x][lever.y] = Lever.leverState;	
		}
		
		return tmpMap;
	}
	
}
