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

		if( (((x == heroX) && ((y == (heroY + 1)) || (y == (heroY - 1)) )) || 
				((y == heroY) && ((x == (heroX + 1)) || (x == (heroX - 1))))) && (state != '8'))
		{
			state = '8';
			stop = 2;
			return;
		}


		if(stop != 0) {
			stop--;				
		}
		else {
			state = 'O';

			Random rand = new Random();
			int move;

			do	{
				move = rand.nextInt(4) + 1;
			}while (((move == 1) && ((tmpMap[x-1][y] == 'X') || (tmpMap[x-1][y] == 'I') || (tmpMap[x-1][y] == 'S'))) || 
					((move == 2) && ((tmpMap[x+1][y] == 'X') || (tmpMap[x+1][y] == 'I') || (tmpMap[x+1][y] == 'S'))) || 
					((move == 3) && ((tmpMap[x][y-1] == 'X') || (tmpMap[x][y-1] == 'I') || (tmpMap[x][y-1] == 'S'))) || 
					((move == 4) && ((tmpMap[x][y+1] == 'X') || (tmpMap[x][y+1] == 'I') || (tmpMap[x][y+1] == 'S'))));

			switch(move)
			{
			case 1:	//Moves up
				x--;
				break;
			case 2:	//Moves down
				x++;
				break;
			case 3:	//Moves left
				y--;		
				break;
			case 4:	//Moves right
				y++;
				break;
			}

			if((x == 1) && (y == 7))
				state = '$';
			else
				state = 'O';

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
		}while( ((move == 1) && ((tmpMap[x-1][y] == 'X') || (tmpMap[x-1][y] == 'I'))) || 
				((move == 2) && ((tmpMap[x+1][y] == 'X') || (tmpMap[x+1][y] == 'I'))) || 
				((move == 3) && ((tmpMap[x][y-1] == 'X') || (tmpMap[x][y-1] == 'I'))) || 
				((move == 4) && ((tmpMap[x][y+1] == 'X') || (tmpMap[x][y+1] == 'I'))));

		switch(move)
		{
		case 1:	//Lands on top
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
			clubY = y + 1;
			clubX = x;
			break;
		}
		
		if (tmpMap[clubX][clubY] == 'k')
			club = '$';
	}

}
