package dkeep.logic;

import java.util.Random;

public class Suspicious extends Guard {

	public Suspicious(int x, int y, char[] guardRoute) {
		super(x, y, guardRoute);
	}

	@Override
	public boolean guardMovement() {
		Random rand = new Random();
		
		if(stop == 0) {

			if(guardRoute[position] == 'w')
				this.x--;
			else if(guardRoute[position] == 's')
				this.x++;
			else if(guardRoute[position] == 'a')
				this.y--;
			else if(guardRoute[position] == 'd')
				this.y++;


			stop = rand.nextInt(2);

			if(position == (guardRoute.length - 1)) {
				position = 0;
				stop = 0;
				return true;
			}
			else 
				position++;
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

			stop = 0;
			position--;
		}

		return false;
	}

}
