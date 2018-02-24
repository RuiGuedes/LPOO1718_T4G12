package dkeep.logic;

import java.util.Random;

public class Guard extends Elements {

	public int position = 0;

	public char state = 'G';
	public String guardType;				
	public int stop = 0;					//Number of plays that the Drunken guard stays still
	public boolean direction = true; 		//False for reverse direction 

	public char[][] guardRoute = { {1,8}, {1,7}, {2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},
			{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8}};

	public Guard(String guardType) {
		this.guardType = guardType;
		this.x = guardRoute[position][0];
		this.y = guardRoute[position][1];
	}

	public boolean guardMovement() {

		//Variables
		Random rand = new Random();
		
		if(guardType == "Rookie") {

			if(position == 23)
				position = 0;
			else
				position++;

			this.x = guardRoute[position][0];
			this.y = guardRoute[position][1];

			if(position == 0)
				return true;

		}
		else if(guardType == "Drunken") {

			int tmp = rand.nextInt(10) + 1;

			if(stop == 0) {
				if(tmp > 7) { 
					state = 'g';
					stop = rand.nextInt(4) + 1;
				}
				else {
					if((position == 23) && direction) {
						position = 0;
						return true;
					}
					else if((position == 0) && (direction == false))
						position = 23;
					else if(direction)
						position++;
					else
						position--;

					this.x = guardRoute[position][0];
					this.y = guardRoute[position][1];

				}
			}
			else {
				stop--;

				if(stop == 0) {

					state = 'G';
					tmp = rand.nextInt(2);

					if(tmp == 1)
						direction = false;
					else
						direction = true;
				}
			}
		}
		else if(guardType == "Suspicious") {

			if(stop == 0) {
				
				if(position == 23)
					position = 0;
				else
					position++;
				
				this.x = guardRoute[position][0];
				this.y = guardRoute[position][1];

				stop = rand.nextInt(2);

				if(position == 23) {
					position = 0;
					stop = 0;
					return true;
				}

			}
			else {
				stop--;

				if(position == 0)
					position = 23;
				else
					position--;

				this.x = guardRoute[position][0];
				this.y = guardRoute[position][1];

			}
		}

		return false;
	}

}
