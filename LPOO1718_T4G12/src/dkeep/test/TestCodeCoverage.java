package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.Guard;
import dkeep.logic.Lock;

public class TestCodeCoverage {
	
	char[][] dungeon = {
			{'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'G', 'X'},
			{'I', ' ', ' ', ' ', ' ', 'X'},
			{'I', 'k', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'I', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X'},
	};
	
	char[][] keep = {
			{'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'O', 'X'},
			{'I', ' ', ' ', ' ', '*', 'X'},
			{'I', 'k', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X'},
	};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('s', dungeon);
		
		assertTrue("Sucess !",game.hero.equals(2,1));
		
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('a', dungeon);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		assertNotEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
		game.hero.heroMovement('d', dungeon);
		game.hero.heroMovement('d', dungeon);
		
		game.checkGameStatus("Guard");
		
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
	}
	
	@Test
	public void testHeroFailToExit() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('s', dungeon);
		
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('a', dungeon);
		
		assertTrue("Sucess !",game.hero.equals(2,1));
	}
	
	@Test
	public void testLeverActiveOpenExitDoors() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !",game.hero.equals(1,1));	
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', dungeon);
		game.hero.heroMovement('s', dungeon);
		dungeon = game.updateMap(dungeon);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
	}
	

	@Test
	public void testHeroProgessionToKeep() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', dungeon);
		game.hero.heroMovement('s', dungeon);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		
		dungeon = game.updateMap(dungeon);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		
		game.hero.heroMovement('a', dungeon);
		
		assertEquals("Sucess !", Game.GameState.VICTORY, Game.gameState);
	}
	
	@Test
	public void testGuardRookieMovementRoute() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		char[] guardRoute = { 'a', 's', 's', 'd', 'w', 'w' };
		
		game.guard[0].changeGuardRoute(guardRoute);
		assertTrue("Sucess !",game.guard[0].equals(1,4));
		
		game.guard[0].guardMovement();
		assertTrue("Sucess !",game.guard[0].equals(1,3));
		
		game.guard[0].guardMovement();
		assertTrue("Sucess !",game.guard[0].equals(2,3));
		
		game.guard[0].guardMovement();
		assertTrue("Sucess !",game.guard[0].equals(3,3));
		
		game.guard[0].guardMovement();
		assertTrue("Sucess !",game.guard[0].equals(3,4));
		
		game.guard[0].guardMovement();
		assertTrue("Sucess !",game.guard[0].equals(2,4));
		
		game.guard[0].guardMovement();
		assertTrue("Sucess !",game.guard[0].equals(1,4));
		
	}
	
	@Test
	public void testGuardDrunkenMovementRoute() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		char[] guardRoute = { 'a', 's', 's', 'd', 'w', 'w' };
		
		game.guard[0] = new Guard(game.guard[0].x, game.guard[0].y,"Drunken", guardRoute);

		assertTrue("Sucess !",game.guard[0].equals(1,4));
		
		
		while(!game.guard[0].guardMovement()) {
			
		}
		
		assertTrue("Sucess !",game.guard[0].equals(1,4));
		
	}
	
	@Test
	public void testGuardSuspiciousMovementRoute() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap);
		
		char[] guardRoute = { 'a', 's', 's', 'd', 'w', 'w' };
		
		game.guard[0] = new Guard(game.guard[0].x, game.guard[0].y,"Suspicious", guardRoute);

		assertTrue("Sucess !",game.guard[0].equals(1,4));
		
		
		while(!game.guard[0].guardMovement()) {
			
		}
		
		assertTrue("Sucess !",game.guard[0].equals(1,4));
		
	}
	
	///////////////////////////////////////////////////// KEEP /////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testHeroIsCapturedByOgre() {
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap);
		
		assertNotEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
		game.hero.heroMovement('d', keep);
		game.hero.heroMovement('d', keep);
		game.checkGameStatus("Ogre");
		
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
	}
	
	@Test
	public void testHeroMovesIntoExitDoorKeyCell() {
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap);
		
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', keep);
		game.hero.heroMovement('s', keep);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		
	}
	
	@Test
	public void testHeroMovesIntoExitDoorWithoutKey() {
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', keep);
		
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		keep = game.updateMap(keep);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'I');
		
	}
	
	@Test
	public void testHeroMovesIntoExitDoorWithKey() {
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', keep);
		game.hero.heroMovement('s', keep);
		game.hero.heroMovement('w', keep);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		keep = game.updateMap(keep);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
	}
	
	@Test
	public void testHeroSuccessfullyExitsKeep() {
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', keep);
		game.hero.heroMovement('s', keep);
		game.hero.heroMovement('w', keep);
		
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		keep = game.updateMap(keep);
		
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		game.hero.heroMovement('a', keep);
		
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	}
	
	@Test
	public void testOgreAndClubMovement() {
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap);
		
		while(Game.gameState != Game.GameState.GAMEOVER) {
			game.ogre.get(0).ogreMovement(1, 1,gameMap.getMap());
			game.checkGameStatus("Ogre");
		}
		
		assertEquals("Sucess !", Game.gameState, Game.GameState.GAMEOVER);
	}
	
	
}
