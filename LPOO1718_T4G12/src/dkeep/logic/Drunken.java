package dkeep.logic;

import java.util.Random;

/**
 * Drunken.java - class to the drunken guard representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 * @see Guard
 */
public class Drunken extends Guard {

	/**
	 * Class constructor specifying coordinates and patrol route
	 * @param x map x coordinate
	 * @param y map y coordinate
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Drunken(Elements guard, char[] guardRoute) {
		super(guard, guardRoute);
	}
	
	/**
	 * Generate numbers to determine if the guard continue the patrol route or stay sleeping,
	 * stop by a random time [1-3], or whether invert the route.
	 */
	@Override
	public boolean guardMovement() {

		Random rand = new Random();
		int tmp = rand.nextInt(10) + 1;

		if(stop == 0) {
			state = 'G';
				
			if(tmp > 8) { 
				state = 'g';
				stop = rand.nextInt(3) + 1;
			}
			else {
				direction = rand.nextBoolean();

				if((position == 0) && !direction)
					direction = true;

				guardMove();

				if((position == (guardRoute.length - 1)) && direction) {
					position = 0;
					return true;
				}
				
				if(direction)
					position++;
				else
					position--;
			}
		}
		else 
			stop--;

		return false;
	}
}
