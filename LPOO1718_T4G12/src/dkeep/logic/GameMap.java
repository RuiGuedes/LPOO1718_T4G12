package dkeep.logic;

import java.util.Arrays;

public class GameMap { 

	private char[][] map;
	
	public GameMap(char[][] givenMap) {
		map = givenMap;
	}

	public static char[][] deepCopy(char[][] original) {
		if (original == null) {
			return null;
		}

		final char[][] result = new char[original.length][];
		for (int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}
		return result;
	}
	
	public char[][] getMap() {
		return deepCopy(map);
	}
	
	public void setMap(char[][] newMap) {
		map = newMap;
	}

	public static void print(char[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++)
				System.out.print(map[i][k] + " ");
			System.out.println();
		}
	}
}
