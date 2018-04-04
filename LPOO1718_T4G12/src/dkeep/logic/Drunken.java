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
	 * @param guard coordinates of the guard
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Drunken(Elements guard, char[] guardRoute) {
		super(guard, guardRoute);
	}

	/**
	 * Call the guardMove function to move the guard and check if the patrol route is over or not.
	 * If the route is over, restart the position and return true.
	 * Otherwise, increment or decrement the route position take in account the direction and return false.
	 * @return True if the route is over, false otherwise.
	 */
	public boolean checkAndMove() {

		if(position == 0)
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

		return false;
	}

	/**
	 * If the guard is not sleeping, generate numbers to determine if the guard continue the patrol route 
	 * or stay sleeping and a random direction of the next movement.
	 * Otherwise, decrease the number of turns going to sleep. 
	 */
	@Override
	public boolean guardMovement() {

		if(stop == 0) {
			state = 'G';
			
			Random rand = new Random();
			int tmp = rand.nextInt(10) + 1;
			
			if(tmp > 8) { 
				state = 'g';
				stop = rand.nextInt(3) + 1;
			}
			else {
				direction = rand.nextBoolean();
				return checkAndMove();
			}
		}
		else 
			stop--;

		return false;
	}
}
