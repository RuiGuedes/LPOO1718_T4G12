package dkeep.logic;

import java.util.Arrays;


public class GameMap {

	private static char[][] dungeon = { 
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'}, 
			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'} };

	private static char[][] keep = { 
			{'X','X','X','X','X','X','X','X','X'},
			{' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X'} };

	private char[][] map;

	public GameMap() {
		
	}
	
	public GameMap(char[][] givenMap) {
		if(Game.LEVEL == 1) {
			dungeon = givenMap;
		}
		else if(Game.LEVEL == 2){
			keep = givenMap;
		}
	}

	public static char[][] deepCopy(char[][] original) {
		if (original == null) {
			return null;
		}

		final char[][] result = new char[original.length][];
		for (int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
			// For Java versions prior to Java 6 use the next:
			// System.arraycopy(original[i], 0, result[i], 0, original[i].length);
		}
		return result;
	}

	public char[][] getOriginalMap() {
		if(Game.LEVEL == 1) {
			return (map = deepCopy(dungeon));
		}
		else {
			return (map = deepCopy(keep));
		}
	}
	
	public char[][] getMap() {
		return deepCopy(map);
	}

	public static void print(char[][] map) {

		for(int i = 0; i < map.length; i++)
		{
			for(int k = 0; k < map[i].length; k++)
				System.out.print(map[i][k] + " ");

			System.out.println();
		}
	}


}
