package dkeep.logic;

import java.util.Arrays;

/**
 * GameMap.java - class to store the game map
 * @author Rui Guedes and César Pinho
 * @see Game
 */
public class GameMap { 

	private char[][] map;

	/**
	 * Class constructor specifying the game map.
	 * @param givenMap game map
	 */
	public GameMap(char[][] givenMap) {
		map = givenMap;
	}

	/**
	 * Clone a two-dimensional array of char.
	 * @param original original array
	 * @return Cloned array of char, if original is not null.
	 */
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

	/**
	 * Call the deepCopy function to clone the game map and return the cloned
	 * @return A copy of the game map
	 */
	public char[][] getMap() {
		return deepCopy(map);
	}

	/**
	 * Discard and change the old game map for the new map.
	 * @param newMap new game map
	 */
	public void setMap(char[][] newMap) {
		map = newMap;
	}

}
