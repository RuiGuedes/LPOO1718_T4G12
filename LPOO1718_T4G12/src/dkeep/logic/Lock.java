package dkeep.logic;

/**
 * Lock.java - class to the lock representation in the game 
 * @author Rui Guedes and César Pinho
 * @see Elements
 */
public class Lock extends Elements {

	/**
	 * Representation mark
	 */
	public static char lockState;
	
	/**
	 * Type of lock. If true, the lock is a key, otherwise, the lock is a lever.
	 */
	public static boolean lockType;
	
	/**
	 * Enable or disable lock
	 */
	public static boolean lockStatus;
	
	/**
	 * Class constructor specifying coordinates and type of lock
	 * @param x map x coordinate
	 * @param y map y coordinate
	 * @param lockType type of lock, true if it's a key, false if it's a lever
	 */
	public Lock(Elements lock, boolean lockType) {
		super(lock);
		Lock.lockType = lockType;
		Lock.lockState = 'k';
		Lock.lockStatus = false;
	}
}
