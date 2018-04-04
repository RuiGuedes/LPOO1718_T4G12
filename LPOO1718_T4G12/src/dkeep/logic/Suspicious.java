package dkeep.logic;

import java.util.Random;

/**
 * Suspicious.java - class to the suspicious guard representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 * @see Guard
 */
public class Suspicious extends Guard {

	/**
	 * Class constructor specifying coordinates and patrol route
	 * @param x map x coordinate
	 * @param y map y coordinate
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Suspicious(Elements guard, char[] guardRoute) {
		super(guard, guardRoute);
	}

	/**
	 * If the direction is the normal direction, it moves the guard and generates random direction.
	 * Otherwise, move the guard in the reverse direction and restores the normal direction of the patrol route.
	 */
	@Override
	public boolean guardMovement() {
		Random rand = new Random();
		
		guardMove();
		
		if(direction) {
			if(position == (guardRoute.length - 1)) {
				position = 0;
				return true;
			}
			else 
				position++;
			
			direction = rand.nextBoolean();
		}
		else {
			direction = true;
			position--;
		}
		return false;
	}

}
