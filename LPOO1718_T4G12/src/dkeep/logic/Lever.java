package dkeep.logic;

public class Lever extends Elements {
	
	public char state = 'k';
	
	public Lever() {

		if(Game.LEVEL == 1) {
			this.x = 8;
			this.y = 7;
		}
		else {
			this.x = 1;
			this.y = 7;
		}
	}

}
