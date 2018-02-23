package dkeep.logic;

public class Door extends Elements {

	public char state = 'I';

	public Door(int[] coordinates) {
		this.x = coordinates[0];
		this.y = coordinates[1];
	}

	public static void openDoors() {
		for(int i = 0; i < Game.door.length; i++) {

			if((i == 3) || (i == 4))
				Game.door[i].state = 'S';
			else
				Game.door[i].state = ' ';
		}
	}
}
