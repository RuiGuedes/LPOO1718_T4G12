package dkeep.logic;

public class Hero extends Elements {
	
	public Hero() {
		
		if(Game.LEVEL == 1) {
			this.x = 1;
			this.y = 1;
		}
		else {
			this.x = 7;
			this.y = 1;
		}
	}

}
