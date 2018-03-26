package dkeep.logic;

public class Rookie extends Guard {

	public Rookie(int x, int y, char[] guardRoute) {
		super(x, y, guardRoute);
	}

	@Override
	public boolean guardMovement() {

		if(guardRoute[position] == 'w')
			x--;
		else if(guardRoute[position] == 's')
			this.x++;
		else if(guardRoute[position] == 'a')
			y--;
		else if(guardRoute[position] == 'd')
			this.y++;

		if(position == (guardRoute.length - 1)) {
			position = 0;
			return true;
		}
		else
			position++;

		return false;
	}
}
