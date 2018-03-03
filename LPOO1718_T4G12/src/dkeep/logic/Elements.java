package dkeep.logic;

public class Elements {
	
	public int x;
	public int y;
	
	public Elements() {
		
	}
	
	public boolean equals(int x, int y) {
		if((this.x == x) && (this.y == y))
			return true;
		else
			return false;
	}
}
