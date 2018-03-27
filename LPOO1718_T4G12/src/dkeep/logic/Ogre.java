package dkeep.logic;

import java.util.Random;

public class Ogre extends Elements {

	public char state = 'O';
	public char club = '*';
	public int clubX = 2;
	public int clubY = 4;
	public int stop = 0;

	public Ogre(int x, int y, int clubX, int clubY) {
		this.x = x;
		this.y = y;
		this.clubX = clubX;
		this.clubY = clubY;
	}

	public void ogreMovement(int heroX, int heroY, char[][] tmpMap) {

		if( ((x == heroX) && (y == (heroY + 1))) || ((x == heroX) && (y == (heroY - 1))) || 
				((y == heroY) && (x == (heroX + 1))) || ((y == heroY) && (x == (heroX - 1))))
		{
			if(state != '8') { 
				state = '8';
				stop = 2;
				return;
			}
		}

		if(stop != 0) {
			stop--;
			if(stop == 0)
				state = 'O';
		}
		else {
			Random rand = new Random();
			int move;

			do	{
				move = rand.nextInt(4) + 1;
			}while(((move == 1) && ((tmpMap[x-1][y] == 'X') || (tmpMap[x-1][y] == 'I'))) || 
					((move == 2) && ((tmpMap[x+1][y] == 'X') || (tmpMap[x+1][y] == 'I'))) || 
					((move == 3) && ((tmpMap[x][y-1] == 'X') || (tmpMap[x][y-1] == 'I'))) || 
					((move == 4) && ((tmpMap[x][y+1] == 'X') || (tmpMap[x][y+1] == 'I'))));

			switch(move)
			{
			case 1:	//Moves up
				if(tmpMap[x-1][y] != 'X') {	
					x--;
					if((x == 1) && (y == 7))
						state = '$';
					else
						state = 'O';
				}
				break;
			case 2:	//Moves down
				if(tmpMap[x+1][y] != 'X') {	
					if((x == 1) && (y == 7))
						state = 'O';

					x++;
				}
				break;
			case 3:	//Moves left
				if((tmpMap[x][y-1] != 'X') && (tmpMap[x][y-1] != 'I') && (tmpMap[x][y-1] != 'S'))  {	
					if((x == 1) && (y == 7))
						state = 'O';

					y--;		
				}
				break;
			case 4:	//Moves right
				if(tmpMap[x][y+1] != 'X') {
					y++;
					if((x == 1) && (y == 7))
						state = '$';
					else
						state = 'O';
				}
				break;
			}

			if(stop != 2) {
				club = '*';
				clubMovement(tmpMap);
			}
		}

	}

	public void clubMovement(char[][] tmpMap) {
		Random rand = new Random();
		int move;

		do	{
			move = rand.nextInt(4) + 1;
		}while(((move == 1) && ((tmpMap[x-1][y] == 'X') || (tmpMap[x-1][y] == 'I'))) || 
				((move == 2) && ((tmpMap[x+1][y] == 'X') || (tmpMap[x+1][y] == 'I'))) || 
				((move == 3) && ((tmpMap[x][y-1] == 'X') || (tmpMap[x][y-1] == 'I'))) || 
				((move == 4) && ((tmpMap[x][y+1] == 'X') || (tmpMap[x][y+1] == 'I'))));

		switch(move)
		{
		case 1:	//Lands on top
			if((tmpMap[x-1][y] == 'k') || (tmpMap[x-1][y] == 'K'))
				club = '$';
			
			clubX = x - 1;
			clubY = y;
			break;
		case 2:	//Lands on bottom
			clubX = x + 1;
			clubY = y;
			break;
		case 3:	//Lands on left
			clubY = y - 1;
			clubX = x;
			break;
		case 4:	//Lands on right
			if((tmpMap[x][y+1] == 'k') || (tmpMap[x][y+1] == 'K'))
				club = '$';
			
			clubY = y + 1;
			clubX = x;
			break;
		}
	}

}
