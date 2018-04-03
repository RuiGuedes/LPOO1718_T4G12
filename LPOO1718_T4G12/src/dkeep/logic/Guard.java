package dkeep.logic;

/**
 * Guard.java - class to the guard representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 */
public abstract class Guard extends Elements {

	/**
	 * Index of the route.
	 */
	public int position = 0;

	/**
	 * Representation mark
	 */
	public char state = 'G';	

	/**
	 * Number of plays that the Drunken guard stays sleeping
	 */
	public int stop = 0;
	
	/**
	 * Direction of the patrol route. If true travels the route in the normal direction 
	 * otherwise if false travels in the reverse direction.
	 */
	public boolean direction = true;

	/**
	 * Patrol route
	 */
	public char[] guardRoute;
	
	/**
	 * Class constructor specifying coordinates and patrol route
	 * @param x map x coordinate
	 * @param y map y coordinate
	 * @param guardRoute movements to navigate the patrol route
	 */
	public Guard(Elements guard, char[] guardRoute) {
		super(guard);
		this.guardRoute = guardRoute;
	}
	
	/**
	 * Change the guard position according with the patrol route.
	 * @return If the patrol route is complete return true, otherwise return false. 
	 * The patrol is complete when position back to the begin of route.
	 * When patrol is complete, change of guard (shift).
	 */
	public abstract boolean guardMovement();

	public void guardMove() {
		if(direction) {
			if(guardRoute[position] == 'w')
				this.x--;
			else if(guardRoute[position] == 's')
				this.x++;
			else if(guardRoute[position] == 'a')
				this.y--;
			else if(guardRoute[position] == 'd')
				this.y++;
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
		}
	}
	
	/**
	 * Set the existent patrol route by other.
	 * @param guardRoute new patrol route
	 */
	public void changeGuardRoute(char[] guardRoute) {
		this.guardRoute = guardRoute;
	}

}
