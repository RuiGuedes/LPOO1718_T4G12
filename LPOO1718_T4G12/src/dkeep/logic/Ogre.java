package dkeep.logic;

import java.util.Random;

/**
 * Ogre.java - class to the ogre representation in the game
 * @author Rui Guedes and César Pinho
 * @see Elements
 */
public class Ogre extends Elements {
	
	/**
	 * Ogre representation mark
	 */
	public char state = 'O';
	
	/**
	 * Club representation mark
	 */
	public char club = '*';
	
	/**
	 * x coordinate of the club on the map
	 */
	public int clubX = 2;
	
	/**
	 * y coordinate of the club on the map
	 */
	public int clubY = 4;
	
	/**
	 * Number of stunned turns
	 */
	public int stop = 0;

	/**
	 * Class constructor specifying the ogre coordinates and the club coordinates on the game map.
	 * @param x x coordinate of the ogre
	 * @param y y coordinate of the ogre
	 * @param clubX x coordinate of the club
	 * @param clubY y coordinate of the club
	 */
	public Ogre(int x, int y, int clubX, int clubY) {
		super(x, y);
		this.clubX = clubX;
		this.clubY = clubY;
	}

	/**
	 * Check if the hero stunned the ogre (if he is near him) and stop the ogre in affirmative case.
	 * Otherwise, generate a random valid move for the ogre and call a function to move the club.
	 * if the ogre is stunned, it decreases the number of stopped turns.
	 * @param heroX x coordinate of the hero
	 * @param heroY y coordinate of the hero
	 * @param tmpMap game map to detect collisions with objects
	 */
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

	/**
	 * Generate a random valid position around the ogre for the club.
	 * @param tmpMap game map to detect collisions with objects
	 */
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
			if((tmpMap[x+1][y] == 'k') || (tmpMap[x+1][y] == 'K'))
				club = '$';

			clubX = x + 1;
			clubY = y;
			break;
		case 3:	//Lands on left
			if((tmpMap[x][y-1] == 'k') || (tmpMap[x][y-1] == 'K'))
				club = '$';

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
