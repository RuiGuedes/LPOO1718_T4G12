package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.elements.Elements;
import dkeep.logic.elements.Lock;
import dkeep.logic.elements.characters.Hero;

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

	@Test
	public void testMoveHeroIntoFreeCell() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		game.hero.heroMovement('s', dungeon);
		assertFalse("Sucess !", game.hero.equals(1,1));
		assertTrue("Sucess !",game.hero.equals(2,1));
 
	}

	@Test
	public void testMoveHeroIntoWall() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));
		game.hero.heroMovement('a', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,1));
 
	}

	@Test
	public void testHeroIsCapturedByGuard() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertNotEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,2));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));
		 
		game.checkGameStatus();
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
	}

	@Test
	public void testHeroFailToExit() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('a', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,1));
	}

	@Test
	public void testLeverActiveOpenExitDoors() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,1));	
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		dungeon = game.updateMap(dungeon); 
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
	}


	@Test
	public void testHeroProgessionToKeep() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		Lock.lockState = 'k';

		assertTrue("Sucess !",game.hero.equals(1,1));
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		assertEquals("Sucess !", Lock.lockState, 'K');

		dungeon = game.updateMap(dungeon);

		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');

		game.hero.heroMovement('a', dungeon);
		assertEquals("Sucess !", Game.GameState.VICTORY, Game.gameState);
	}
	
	@Test
	public void testCheckGameStatusForGuard() {
		Game.LEVEL = 1; 
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,2));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));
		
		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,3));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,4));
		
		game.checkGameStatus();
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);

	}
	
	@Test
	public void testGuardRookieMovementRoute() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

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
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Drunken",1);

		char[] guardRoute = { 'a', 's', 's', 'd', 'w', 'w' };
  
		game.guard[game.guardRouting].changeGuardRoute(guardRoute); 
		assertTrue("Sucess !",game.guard[game.guardRouting].equals(1,4));
		  
		for(int i = 0; i < 100; i++) {
			while(!game.guard[game.guardRouting].guardMovement()) {
			}
			assertTrue("Sucess !",game.guard[game.guardRouting].equals(1,4));
		}
		
		assertTrue("Sucess !",game.guard[game.guardRouting].equals(1,4));
	}

	@Test
	public void testGuardSuspiciousMovementRoute() {
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Suspicious",1);

		char[] guardRoute = { 'a', 's', 's', 'd', 'w', 'w' };
  
		game.guard[game.guardRouting].changeGuardRoute(guardRoute); 
		assertTrue("Sucess !",game.guard[game.guardRouting].equals(1,4));
		  
		for(int i = 0; i < 100; i++) {
			while(!game.guard[game.guardRouting].guardMovement()) {
			}
			assertTrue("Sucess !",game.guard[game.guardRouting].equals(1,4));
		}
		
		assertTrue("Sucess !",game.guard[game.guardRouting].equals(1,4));
	} 

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
	public void testHeroIsCapturedByOgre() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertNotEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
		
		game.hero.heroMovement('d', keep);
		assertTrue("Sucess !",game.hero.equals(1,2));
		
		game.hero.heroMovement('d', keep);
		assertTrue("Sucess !",game.hero.equals(1,3));
		
		game.checkGameStatus();
		assertEquals("Sucess !", Game.GameState.GAMEOVER, Game.gameState);
	}
	
	@Test
	public void testHeroMovesIntoExitDoorKeyCell() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(3,1));
		assertEquals("Sucess !", Lock.lockState, ' ');
		
	}
	
	
	@Test
	public void testHeroMovesIntoExitDoorWithoutKey() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		keep = game.updateMap(keep);
		assertTrue("Sucess !",game.hero.equals(2,1));
		assertEquals("Sucess !", Game.door.get(0).state, 'I');
		
	}
	
	@Test
	public void testHeroMovesIntoExitDoorWithKey() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(3,1));
		assertEquals("Sucess !", Lock.lockState, ' ');
		
		game.hero.heroMovement('w', keep);
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		keep = game.updateMap(keep);
		
		assertTrue("Sucess !", game.hero.equals(2,1));
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
	}
	
	@Test 
	public void testHeroSuccessfullyExitsKeep() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(1,1));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(3,1));
		assertEquals("Sucess !", Lock.lockState, ' ');
		
		game.hero.heroMovement('w', keep);
		assertTrue("Sucess !", game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		keep = game.updateMap(keep);
		
		assertTrue("Sucess !", game.hero.equals(2,1));
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		game.hero.heroMovement('a', keep);
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	} 
	
	@Test
	public void testHeroStuntOgreAndGetsCatched() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		game.hero.heroMovement('d', keep);
		keep = game.updateMap(keep);
		assertTrue("Sucess !",game.hero.equals(1,2));
		
		game.hero.heroMovement('d', keep);
		keep = game.updateMap(keep);
		assertTrue("Sucess !",game.hero.equals(1,3));
		
		Hero h = new Hero(new Elements(1,3), 'H');
		
		game.ogre.get(0).ogreMovement(h,keep); 
		assertEquals("Sucess !", game.ogre.get(0).state, '8');
		
		while(game.ogre.get(0).state == '8') {
			game.ogre.get(0).ogreMovement(h,keep);
		}
		
		assertEquals("Sucess !", game.ogre.get(0).state, 'O');
	}
	  
	@Test
	public void testOgreAndClubRandoomMovement() {
		Game.LEVEL = 2;
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		Hero h = new Hero(new Elements(0,0), 'H');
		
		while(Game.gameState != Game.GameState.GAMEOVER) {
			game.ogre.get(0).ogreMovement(h,gameMap.getMap());
			game.checkGameStatus();
		}
 
		assertEquals("Sucess !", Game.gameState, Game.GameState.GAMEOVER);
	}

	char[][] ogreMap = {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'X'}, 
			{'X', ' ', ' ', ' ', '*', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
	};
	
	boolean[][] visited = {
			{false, false, false, true, false, false, false},
			{false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false},	
	};
	
	public boolean checkVisited() {
		
		for(int i = 0; i < visited.length; i++)
		{
			for(int j = 0; j < visited[i].length; j++) {
				if(visited[i][j] == false)
					return false;
			}
		}
		return true;
	}
	
	@Test(timeout=1000) 
	public void testSomeRandoomBehaviour() {
		GameMap gameMap = new GameMap(ogreMap);
		Game game = new Game(gameMap,"Rookie",1);
		Hero h = new Hero(new Elements(0,0), 'H');
		
		while(!checkVisited()) {
			game.ogre.get(0).ogreMovement(h, ogreMap);
			visited[game.ogre.get(0).x - 1][game.ogre.get(0).y - 1] = true;
		}
	}
}
