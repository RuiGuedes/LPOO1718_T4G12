package dkeep.logic.elements.guard;

import java.util.Random;

import dkeep.logic.elements.Elements;

/**
 * Suspicious.java - class to the suspicious guard representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 * @see Guard
 */
public class Suspicious extends Guard {

	/**
	 * Class constructor specifying coordinates and patrol route
	 * @param guard coordinates of the guard
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Suspicious(Elements guard, char[] guardRoute) {
		super(guard, guardRoute);
	}

	/**
	 * Call a function, guardMove, to move the guard and check the route.
	 * If the direction is the normal direction, check if the route is over.
	 * Otherwise, restores the normal direction of the patrol route.
	 */
	@Override
	public boolean guardMovement() {
		
		guardMove();
		
		if(direction)
			return checkEndRoute();
		else {
			direction = true;
			position--;
		}
		return false;
	}
	
	/**
	 * Check if the patrol route is over or not and generate a random direction for the next movement.
	 * If the route is over, restart the position and return true.
	 * Otherwise, increment the route position, generate the random direction and return false.
	 * @return True if the route is over, false otherwise.
	 */
	public boolean checkEndRoute() {
		Random rand = new Random();
		
		if(position == (guardRoute.length - 1)) {
			position = 0;
			return true;
		}
		else 
			position++;
		
		direction = rand.nextBoolean();
		return false;
	}
}
