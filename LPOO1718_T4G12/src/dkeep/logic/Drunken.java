package dkeep.logic;

import java.util.Random;

public class Drunken extends Guard {

	public Drunken(int x, int y, char[] guardRoute) {
		super(x, y, guardRoute);
	}
	
	@Override
	public boolean guardMovement() {

		Random rand = new Random();
		int tmp = rand.nextInt(10) + 1;

		if(stop == 0) {
			if(tmp > 8) { 
				state = 'g';
				stop = rand.nextInt(3) + 1;
			}
			else {

				if((position == 0) && !direction)
					direction = true;

				if(direction) {
					if(guardRoute[position] == 'w')
						this.x--;
					else if(guardRoute[position] == 's')
						this.x++;
					else if(guardRoute[position] == 'a')
						this.y--;
					else if(guardRoute[position] == 'd')
						this.y++;
				}
				else {
					if(guardRoute[position-1] == 's')
						this.x--;
					else if(guardRoute[position-1] == 'w')
						this.x++;
					else if(guardRoute[position-1] == 'd')
						this.y--;
					else if(guardRoute[position-1] == 'a')
						this.y++;
				}

				if((position == (guardRoute.length - 1)) && direction) {
					position = 0;
					return true;
				}
				else if(direction)
					position++;
				else
					position--;
			}
		}
		return false;
	}
}
