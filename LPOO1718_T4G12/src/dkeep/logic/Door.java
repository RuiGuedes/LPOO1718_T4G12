package dkeep.logic;

public class Door extends Elements {

	public char state;

	public Door(int x, int y, char state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public static void openDoors() {
		for(int i = 0; i < Game.door.size(); i++) {

			if((i == 3) || (i == 4)) {
				Door tmp = new Door(Game.door.get(i).x, Game.door.get(i).y, 'S');
				Game.door.set(i,tmp);
			}
			else {
				Door tmp = new Door(Game.door.get(i).x, Game.door.get(i).y, ' ');
				Game.door.set(i,tmp);
			}
		}
	}
}
