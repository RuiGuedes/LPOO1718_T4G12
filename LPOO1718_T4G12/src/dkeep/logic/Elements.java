package dkeep.logic;

/**
 * Elements.java - class to store the coordinates of the elements 
 * @author Rui Guedes and César Pinho
 */
public class Elements {
	
	/**
	 * x coordinate on the map
	 */
	public int x;
	
	/**
	 * y coordinate on the map
	 */
	public int y;
	
	/**
	 * Class constructor specifying the coordinates
	 * @param x map x coordinate
	 * @param y map y coordinate
	 */
	public Elements(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	 /**
	  * Compare x and y coordinates of the object with the given coordinates.
	  * @param x map x coordinate to compare
	  * @param y map y coordinate to compare
	  * @return result of the comparison. If the coordinates are equals, return true, else return false.
	  */
	public boolean equals(int x, int y) {
		return ((this.x == x) && (this.y == y));
	}
}
