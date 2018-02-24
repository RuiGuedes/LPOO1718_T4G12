package dkeep.logic;

public class Guard extends Elements {
	
	public int position = 0;
	
	public char[][] guardRoute = { {1,8}, {1,7}, {2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},
			{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8}};
	
	public Guard() {
		this.x = guardRoute[position][0];
		this.y = guardRoute[position][1];
	}
	
	public void guardMovement() {
		
		if(position == 23)
			position = 0;
		else
			position++;
		
		this.x = guardRoute[position][0];
		this.y = guardRoute[position][1];
	}

}
