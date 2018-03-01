package dkeep.logic;

public class Door extends Elements {

	public char state = 'I';

	public Door(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static void openDoors() {
		for(int i = 0; i < Game.door.size(); i++) {

//			if((i == 3) || (i == 4))
//				Game.door.set(i,'S');
//			else
//				Game.door[i].state = ' ';
		}
	}
}
