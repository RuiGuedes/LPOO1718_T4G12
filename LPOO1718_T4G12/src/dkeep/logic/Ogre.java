package dkeep.logic;

import java.util.Random;

public class Ogre extends Elements {

	public char state = 'O';
	public char club = '*';

	public int clubX = 2;
	public int clubY = 4;

	public int stop = 0;

	public Ogre() {
		this.x = 1;
		this.y = 4;
	}

	public void ogreMovement(int heroX, int heroY, char[][] tmpMap) {

		if( ((x == heroX) && (y == (heroY + 1))) || ((x == heroX) && (y == (heroY - 1))) || 
				((y == heroY) && (x == (heroX + 1))) || ((y == heroY) && (x == (heroX - 1))))
		{
			if(state != '8') {
				state = '8';
				stop = 2;
			}
		}
		else
		{	
			if(stop != 0) {
				stop--;
				if(stop == 0)
					state = 'O';
			}
			else
			{
				//Variables
				Random rand = new Random();
				int move;

				//Generates club new position
				do	{
					move = rand.nextInt(4) + 1;
				}while(((move == 1) && (x == 1)) || ((move == 2) && (x == 7)) || ((move == 3) && (y == 1)) || ((move == 4) && (y == 7)));

				//Ogre Movement
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
			}
		}
		
		if(stop != 2) {
			club = '*';
			clubMovement(tmpMap);
		}

	}

	public void clubMovement(char[][] tmpMap) {

		//Variables
		Random rand = new Random();
		int move;

		//Generates club new position
		do	{
			move = rand.nextInt(4) + 1;
		}while(((move == 1) && (x == 1)) || ((move == 2) && (x == 7)) || ((move == 3) && (y == 1)) || ((move == 4) && (y == 7)));

		//Club new position
		switch(move)
		{
		case 1:	//Lands on top
			if((tmpMap[x-1][y] == 'k') || (tmpMap[x-1][y] == 'K'))
				club = '$';
			else if(tmpMap[x-1][y] == 'H') {
				Game.gameState = Game.GameState.GAMEOVER;
			}
			clubX = x - 1;
			clubY = y;
			break;
		case 2:	//Lands on bottom
			if(tmpMap[x+1][y] == 'H') {
				Game.gameState = Game.GameState.GAMEOVER;
			}

			clubX = x + 1;
			clubY = y;
			break;
		case 3:	//Lands on left
			if(tmpMap[x][y-1] == 'H') {
				Game.gameState = Game.GameState.GAMEOVER;
			}
			clubY = y - 1;
			clubX = x;
			break;
		case 4:	//Lands on right
			if((tmpMap[x][y+1] == 'k') || (tmpMap[x][y+1] == 'K'))
				club = '$';
			else if(tmpMap[x][y+1] == 'H') {
				Game.gameState = Game.GameState.GAMEOVER;	
			}
			clubY = y + 1;
			clubX = x;
			break;
		}
	}

}
