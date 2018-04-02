package dkeep.logic;

/**
 * Rookie.java - class to the rookie guard representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 * @see Guard
 */
public class Rookie extends Guard {

	/**
	 * Class constructor specifying coordinates and patrol route
	 * @param x map x coordinate
	 * @param y map y coordinate
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Rookie(int x, int y, char[] guardRoute) {
		super(x, y, guardRoute);
	}

	/**
	 * Travels the route step by step without change direction or standing still.
	 */
	@Override
	public boolean guardMovement() {

		if(guardRoute[position] == 'w')
			x--;
		else if(guardRoute[position] == 's')
			this.x++;
		else if(guardRoute[position] == 'a')
			y--;
		else if(guardRoute[position] == 'd')
			this.y++;

		if(position == (guardRoute.length - 1)) {
			position = 0;
			return true;
		}
		else
			position++;

		return false;
	}
}
