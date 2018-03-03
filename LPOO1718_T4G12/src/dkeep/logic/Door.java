package dkeep.logic;

public class Door extends Elements {

	public char state;
	public boolean type;

	public Door(int x, int y, char state, boolean type) {
		this.x = x;
		this.y = y;
		this.state = state;
		this.type = type;
	}

	public static void openDoors() {
		for(int i = 0; i < Game.door.size(); i++) {
			Door tmp;

			if(Game.door.get(i).type)
				tmp = new Door(Game.door.get(i).x, Game.door.get(i).y, 'S', Game.door.get(i).type);
			else 
				tmp = new Door(Game.door.get(i).x, Game.door.get(i).y, ' ', Game.door.get(i).type);
			
			Game.door.set(i,tmp);
		}
	}
}
