package dkeep.logic.elements.guard;

import dkeep.logic.elements.Elements;

/**
 * Rookie.java - class to the rookie guard representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 * @see Guard
 */
public class Rookie extends Guard {

	/**
	 * Class constructor specifying coordinates and patrol route
	 * @param guard coordinates of the guard
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Rookie(Elements guard, char[] guardRoute) {
		super(guard, guardRoute);
	}

	/**
	 * Travels the route step by step without change direction or standing still.
	 */
	@Override
	public boolean guardMovement() {

		if(guardRoute[position] == 'w')
			this.x--;
		else if(guardRoute[position] == 's')
			this.x++;
		else if(guardRoute[position] == 'a')
			this.y--;
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
