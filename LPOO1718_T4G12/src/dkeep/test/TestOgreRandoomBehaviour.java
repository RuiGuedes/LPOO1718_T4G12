package dkeep.test;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.characters.Hero;
import dkeep.logic.elements.Elements;

public class TestOgreRandoomBehaviour {
	
	char[][] map = {
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
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,"Rookie",1);
		Hero h = new Hero(new Elements(0,0), 'H');
		while(!checkVisited()) {
			game.ogre.get(0).ogreMovement(h, map);
			visited[game.ogre.get(0).x - 1][game.ogre.get(0).y - 1] = true;
		}
	}

}
