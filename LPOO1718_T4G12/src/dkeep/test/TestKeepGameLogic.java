package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;

public class TestKeepGameLogic {
	
	char[][] map = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', 'O', 'X'},
			{'I', ' ', ' ', '*', 'X'},
			{'X', 'k', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'},
	};
	
	
	@Test
	public void testHeroIsCapturedByOgre() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		
		assertNotEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
		game.hero.heroMovement('d', map);
		game.checkGameStatus("Ogre");
		
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
	}
	
	@Test
	public void testHeroMovesIntoExitDoorKeyCell() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('s', map);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		
	}
	
	
	@Test
	public void testHeroMovesIntoExitDoorWithoutKey() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', map);
		
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', map);
		map = game.updateMap(map);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'I');
		
	}
	
	@Test
	public void testHeroMovesIntoExitDoorWithKey() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('w', map);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', map);
		map = game.updateMap(map);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
	}
	
	@Test
	public void testHeroSuccessfullyExitsKeep() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('s', map);
		game.hero.heroMovement('w', map);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', map);
		map = game.updateMap(map);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		game.hero.heroMovement('a', map);
		
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	}

}