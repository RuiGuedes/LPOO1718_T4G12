package dkeep.logic;

/**
 * Door.java - class to the doors representation in the game
 * @author Rui Guedes and César Pinho
 * @see Elements
 */
public class Door extends Elements {
	
	/**
	 * Representation mark:
	 * 'I' - close door
	 * 'S' - exit door open
	 * 'Z' - dead end door open
	 */
	public char state;
	
	/**
	 * if exit door: type=true; else: type=false
	 */
	public boolean type;

	/**
	 * Class constructor specifying coordinates, mark on the game map and the door type.
	 * The door type is true if it is an exit door, otherwise is false.
	 * @param door coordinates of the door
	 * @param state representation mark on the game map
	 * @param type if exit door? type=true : type=false
	 */
	public Door(Elements door, char state, boolean type) {
		super(door.x,door.y);
		this.state = state;
		this.type = type;
	}

	/**
	 * Open every existing door and distinguishes the exit doors from the rest, changing the representation marks.
	 * The open exit doors have the letter 'S' as a mark, and the remaining open doors have 'Z' as a mark.
	 */
	public static void openDoors() {
		for(int i = 0; i < Game.door.size(); i++) {
			Door tmp;

			if(Game.door.get(i).type)
				tmp = new Door(Game.door.get(i), 'S', Game.door.get(i).type);
			else 
				tmp = new Door(Game.door.get(i), 'Z', Game.door.get(i).type);
			
			Game.door.set(i,tmp);
		}
	}
}
