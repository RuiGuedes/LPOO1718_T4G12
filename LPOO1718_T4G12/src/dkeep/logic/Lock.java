package dkeep.logic;

public class Lock extends Elements {

	public static char lockState = 'k';
	
	public static boolean lockType;						//True = Key 		False = Lever
	public static boolean lockStatus = false; 			//True = Active 	False = Deactivates
	
	public Lock(int x, int y, boolean lockType) {
		this.x = x;
		this.y = y;
		Lock.lockType = lockType;
	}

}
