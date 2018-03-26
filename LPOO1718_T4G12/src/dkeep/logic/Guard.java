package dkeep.logic;

public class Guard extends Elements {

	public int position = 0;

	public char state = 'G';			
	public int stop = 0;					//Number of plays that the Drunken guard stays still
	public boolean direction = true; 		//False for reverse direction 

	public char[] guardRoute;

	public Guard(int x, int y, char[] guardRoute) {
		this.x = x;
		this.y = y;
		this.guardRoute = guardRoute;
	}
	
	public boolean guardMovement() {
		return false;
	}
	
	public void changeGuardRoute(char[] guardRoute) {
		this.guardRoute = guardRoute;
	}
	
}
