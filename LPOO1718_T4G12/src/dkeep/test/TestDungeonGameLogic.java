package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic {
	
	char[][] map = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', 'G', 'X'},
			{'I', ' ', ' ', ' ', 'X'},
			{'I', 'k', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'},
	};
			
	@Test
	public void testMoveHeroIntoFreeCell() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('s', map);
		
		assertTrue("Sucess !",game.hero.equals(2,1));
		
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('a', map);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		Game.LEVEL = 1;
		assertNotEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
		game.hero.heroMovement('d', map);
		game.checkGameStatus();
		
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
	}
	
	@Test
	public void testHeroFailToExit() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('s', map);
		
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('a', map);
		
		assertTrue("Sucess !",game.hero.equals(2,1));
	}
	
	@Test
	public void testLeverActiveOpenExitDoors() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));	
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('s', map);
		map = game.updateMap(map);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
	}
	

	@Test
	public void testHeroProgessionToKeep() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		Lock.lockState = 'k';
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('s', map);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		map = game.updateMap(map);
//		
//		assertEquals("Sucess !", Game.door.get(0).state, 'S');
//		assertEquals("Sucess !", Game.door.get(1).state, 'S');
//		
		//game.hero.heroMovement('a', map);
//		
//		assertEquals("Sucess !", Game.GameState.VICTORY, Game.gameState);
	}
	
	

}
