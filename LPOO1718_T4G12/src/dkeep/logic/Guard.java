package dkeep.logic;

import java.util.Random;

public class Guard extends Elements {

	public int position = 0;

	public char state = 'G';
	public String guardType;				
	public int stop = 0;					//Number of plays that the Drunken guard stays still
	public boolean direction = true; 		//False for reverse direction 

	public char[] guardRoute;

	public Guard(int x, int y, String guardType, char[] guardRoute) {
		this.x = x;
		this.y = y;
		this.guardType = guardType;
		this.guardRoute = guardRoute;
	}

	public boolean guardMovement() {

		//Variables
		Random rand = new Random();

		if(guardType == "Rookie") {
			
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

		}
		else if(guardType == "Drunken") {

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
		}

		return false;
	}
	
	public void changeGuardRoute(char[] guardRoute) {
		this.guardRoute = guardRoute;
	}
	
}
