package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;
import dkeep.logic.*;

public class TestHeroCoverage {

	char[][] dungeon = {
			{'X', 'X', 'X', 'I', 'X', 'X'},
			{'X', 'k', ' ', 'H', 'k', 'X'},
			{'I', ' ', ' ', ' ', ' ', 'I'},
			{'I', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', 'I', 'X', 'X'},
			{'X', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'I', 'X', 'X'},
	}; 

	@Test
	public void testMoveHeroIntoFreeCell() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));
		game.hero.heroMovement('a', dungeon);
		assertFalse("Sucess !", game.hero.equals(1,3));
		assertTrue("Sucess !",game.hero.equals(1,2));

	}

	@Test
	public void testMoveHeroIntoWall() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));
		game.hero.heroMovement('w', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));

	}

	@Test
	public void testHeroFailToExitRight() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,3));

		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,4));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,4));
	}

	@Test
	public void testHeroFailToExitDown() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,3));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(3,3));
		
		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(3,3));
	}
	
	@Test
	public void testHeroFailToExitUp() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));

		game.hero.heroMovement('w', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));
	}
	
	@Test
	public void testLeverActiveOpenExitDoorsRight() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));	
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));

		dungeon = game.updateMap(dungeon); 
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		assertEquals("Sucess !", Game.door.get(2).state, 'S');
		assertEquals("Sucess !", Game.door.get(3).state, 'S');
		assertEquals("Sucess !", Game.door.get(4).state, 'Z');
		assertEquals("Sucess !", Game.door.get(5).state, 'S');
	}
	
	@Test
	public void testLeverActiveOpenExitDoorsLeft() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));	
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('a', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,2));
		
		game.hero.heroMovement('a', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,2));

		dungeon = game.updateMap(dungeon); 
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		assertEquals("Sucess !", Game.door.get(2).state, 'S');
		assertEquals("Sucess !", Game.door.get(3).state, 'S');
		assertEquals("Sucess !", Game.door.get(4).state, 'Z');
		assertEquals("Sucess !", Game.door.get(5).state, 'S');
	}
	
	@Test
	public void testLeverActiveOpenExitDoorsUp() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);

		assertTrue("Sucess !",game.hero.equals(1,3));	
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,3));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,4));
		
		game.hero.heroMovement('w', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,4));

		dungeon = game.updateMap(dungeon); 
		assertEquals("Sucess !", Lock.lockState, 'K');
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		assertEquals("Sucess !", Game.door.get(2).state, 'S');
		assertEquals("Sucess !", Game.door.get(3).state, 'S');
		assertEquals("Sucess !", Game.door.get(4).state, 'Z');
		assertEquals("Sucess !", Game.door.get(5).state, 'S');	
	}

	@Test
	public void testHeroProgessionToKeepRight() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		Lock.lockState = 'k';

		assertTrue("Sucess !",game.hero.equals(1,3));
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,3));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,4));

		assertEquals("Sucess !", Lock.lockState, 'K');

		dungeon = game.updateMap(dungeon);

		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		assertEquals("Sucess !", Game.door.get(2).state, 'S');
		assertEquals("Sucess !", Game.door.get(3).state, 'S');
		assertEquals("Sucess !", Game.door.get(4).state, 'Z');
		assertEquals("Sucess !", Game.door.get(5).state, 'S');
		
		game.hero.heroMovement('d', dungeon);
		assertEquals("Sucess !", Game.GameState.VICTORY, Game.gameState);
	}
	
	@Test
	public void testHeroProgessionToKeepDown() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		Lock.lockState = 'k';

		assertTrue("Sucess !",game.hero.equals(1,3));
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(2,3));

		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(3,3));

		dungeon = game.updateMap(dungeon);
		
		game.hero.heroMovement('s', dungeon);
		assertTrue("Sucess !",game.hero.equals(5,3));

		assertEquals("Sucess !", Lock.lockState, 'K');

		dungeon = game.updateMap(dungeon);
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		assertEquals("Sucess !", Game.door.get(2).state, 'S');
		assertEquals("Sucess !", Game.door.get(3).state, 'S');
		assertEquals("Sucess !", Game.door.get(4).state, 'Z');
		assertEquals("Sucess !", Game.door.get(5).state, 'S');
		
		game.hero.heroMovement('s', dungeon);
		assertEquals("Sucess !", Game.GameState.VICTORY, Game.gameState);
	}
	
	@Test
	public void testHeroProgessionToKeepUp() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		Lock.lockState = 'k';

		assertTrue("Sucess !",game.hero.equals(1,3));
		assertEquals("Sucess !", Lock.lockState, 'k');

		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));

		assertEquals("Sucess !", Lock.lockState, 'K');

		dungeon = game.updateMap(dungeon);

		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		assertEquals("Sucess !", Game.door.get(1).state, 'S');
		assertEquals("Sucess !", Game.door.get(2).state, 'S');
		assertEquals("Sucess !", Game.door.get(3).state, 'S');
		assertEquals("Sucess !", Game.door.get(4).state, 'Z');
		assertEquals("Sucess !", Game.door.get(5).state, 'S');
		
		game.hero.heroMovement('w', dungeon);
		assertEquals("Sucess !", Game.GameState.VICTORY, Game.gameState);
	}
	
	@Test
	public void testHeroMoveIntoNotExitDoorLeftAndRight() {
		Game.LEVEL = 1;
		dungeon[1][1] = ' ';
		dungeon[1][2] = 'I';
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,3));
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));
		
		dungeon = game.updateMap(dungeon);
		
		game.hero.heroMovement('a', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,1));
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));
	}
	
	@Test
	public void testHeroMoveIntoNotExitDoorUp() {
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !",game.hero.equals(1,3));
		assertEquals("Sucess !", Lock.lockState, 'k');
		
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(1,3));
		
		game.hero.heroMovement('s', dungeon);
		game.hero.heroMovement('s', dungeon);
		game.hero.heroMovement('a', dungeon);
		game.hero.heroMovement('s', dungeon);
		game.hero.heroMovement('s', dungeon);
		game.hero.heroMovement('d', dungeon);
		assertTrue("Sucess !",game.hero.equals(5,3));
		
		dungeon = game.updateMap(dungeon);
		
		game.hero.heroMovement('w', dungeon);
		assertTrue("Sucess !",game.hero.equals(3,3));
	}
	

	
	char[][] keep = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', 'k', ' ', 'X'},
			{'X', 'k', 'H', 'k', 'X'},
			{'X', ' ', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'} };
	
	@Test
	public void testHeroExitKeepUp() {
		Game.LEVEL = 2;
		keep[0][2]= 'I';
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(2,2));
		
		game.hero.heroMovement('w', keep);
		assertTrue("Sucess !",game.hero.equals(1,2));
		
		game.hero.heroMovement('w', keep);
		assertTrue("Sucess !",game.hero.equals(1,2));
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		keep = game.updateMap(keep);		
		
		game.hero.heroMovement('w', keep);
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	}
	
	@Test
	public void testHeroExitKeepDown() {
		Game.LEVEL = 2;
		keep[4][2]= 'I';
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(2,2));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(3,2));
		
		game.hero.heroMovement('s', keep);
		assertTrue("Sucess !",game.hero.equals(3,2));
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		keep = game.updateMap(keep);		
		
		game.hero.heroMovement('s', keep);
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	}
	
	@Test
	public void testHeroExitKeepLeft() {
		Game.LEVEL = 2;
		keep[2][0]= 'I';
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(2,2));
		
		game.hero.heroMovement('a', keep);
		assertTrue("Sucess !",game.hero.equals(2,1));
		
		game.hero.heroMovement('a', keep);
		assertTrue("Sucess !",game.hero.equals(2,1));
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		keep = game.updateMap(keep);		

		game.hero.heroMovement('a', keep);
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	}
	
	@Test
	public void testHeroExitKeepRight() {
		Game.LEVEL = 2;
		keep[2][4]= 'I';
		GameMap gameMap = new GameMap(keep);
		Game game = new Game(gameMap,"Rookie",1);
		
		assertTrue("Sucess !", game.hero.equals(2,2));
		
		game.hero.heroMovement('d', keep);
		assertTrue("Sucess !",game.hero.equals(2,3));
		
		game.hero.heroMovement('d', keep);
		assertTrue("Sucess !",game.hero.equals(2,3));
		assertEquals("Sucess !", Game.door.get(0).state, 'S');
		
		keep = game.updateMap(keep);		

		game.hero.heroMovement('d', keep);
		assertEquals("Sucess !", Game.gameState, Game.GameState.VICTORY);
	}
}