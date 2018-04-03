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
	public Ogre(Elements ogre, Elements club) {
		super(ogre);
		this.clubX = club.x;
		this.clubY = club.y;
	}

	public boolean checkAround(int move, char[][] tmpMap) {
		int xCopy=x , yCopy = y;
		
		switch(move)
		{
		case 1:	//Moves up
			xCopy--;
			break;
		case 2:	//Moves down
			xCopy++;
			break;
		case 3:	//Moves left
			yCopy--;		
			break;
		case 4:	//Moves right
			yCopy++;
			break;
		}
		
		return ((tmpMap[xCopy][yCopy] == 'X') || (tmpMap[xCopy][yCopy] == 'I') || (tmpMap[xCopy][yCopy] == 'S'));
	}
	
	public Elements generateMove(Elements position, char[][] tmpMap) {
		Random rand = new Random();
		int move;

		do	{
			move = rand.nextInt(4) + 1;
		}while (checkAround(move, tmpMap));

		switch(move)
		{
		case 1:	//Moves up
			position.x--;
			break;
		case 2:	//Moves down
			position.x++;
			break;
		case 3:	//Moves left
			position.y--;		
			break;
		case 4:	//Moves right
			position.y++;
			break;
		}
		
		return position;
	}
	
	/**
	 * Check if the hero stunned the ogre (if he is near him) and stop the ogre in affirmative case.
	 * Otherwise, generate a random valid move for the ogre and call a function to move the club.
	 * if the ogre is stunned, it decreases the number of stopped turns.
	 * @param heroX x coordinate of the hero
	 * @param heroY y coordinate of the hero
	 * @param tmpMap game map to detect collisions with objects
	 */
	public void ogreMovement(Hero hero, char[][] tmpMap) {

		if((this.checkProximity(hero)) && (state != '8')) {
			state = '8';
			stop = 2;
			return;
		}

		if(stop != 0)
			stop--;				
		else {
			generateMove(this, tmpMap);

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

		Elements clubPosition = new Elements(this);
		
		clubPosition = generateMove(clubPosition, tmpMap);
		
			clubX = clubPosition.x;
			clubY = clubPosition.y;
		
		if (tmpMap[clubX][clubY] == 'k')
			club = '$';
	}

}
