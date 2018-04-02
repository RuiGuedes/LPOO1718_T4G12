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
	public Suspicious(int x, int y, char[] guardRoute) {
		super(x, y, guardRoute);
	}

	/**
	 * If the direction is the normal direction, it moves the guard and generates random direction.
	 * Otherwise, move the guard in the reverse direction and restores the normal direction of the patrol route.
	 */
	@Override
	public boolean guardMovement() {
		Random rand = new Random();
		
		if(direction) {

			if(guardRoute[position] == 'w')
				this.x--;
			else if(guardRoute[position] == 's')
				this.x++;
			else if(guardRoute[position] == 'a')
				this.y--;
			else if(guardRoute[position] == 'd')
				this.y++;


			direction = rand.nextBoolean();

			if(position == (guardRoute.length - 1)) {
				position = 0;
				direction = true;
				return true;
			}
			else 
				position++;
		}
		else {

			if(guardRoute[position-1] == 's')
				this.x--;
			else if(guardRoute[position-1] == 'w')
				this.x++;
			else if(guardRoute[position-1] == 'd')
				this.y--;
			else if(guardRoute[position-1] == 'a')
				this.y++;

			direction = true;
			position--;
		}

		return false;
	}

}
