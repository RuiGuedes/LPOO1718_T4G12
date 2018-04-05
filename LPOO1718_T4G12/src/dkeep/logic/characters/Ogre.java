package dkeep.logic.characters;

import java.util.Random;

import dkeep.logic.elements.Elements;

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
	public int clubX;

	/**
	 * y coordinate of the club on the map
	 */
	public int clubY;

	/**
	 * Number of stunned turns
	 */
	public int stop = 0;

	/**
	 * Class constructor specifying the ogre coordinates and the club coordinates on the game map.
	 * @param ogre coordinates of the ogre
	 * @param club coordinates of the club
	 */
	public Ogre(Elements ogre, Elements club) {
		super(ogre.x,ogre.y);
		this.clubX = club.x;
		this.clubY = club.y;
	}

	/**
	 * Increment or decrement one of the coordinates depending of the direction of the movement.<p>
	 * Up 	 (w) - increment x coordinate;<p>
	 * Right (d) - increment y coordinate;<p>
	 * Down	 (s) - decrement x coordinate;<p>
	 * Left  (a) - decrement y coordinate. 
	 * @param position coordinates of the element
	 * @param move movement of the element
	 * @return The coordinates of the new position.
	 */
	public Elements changeCoords(Elements position, int move) {
		switch(move) {
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
			break; }
		
		return position; 
	}
	
	/**
	 * Check if the position after the movement is occupied or not.
	 * @param move movement of the element
	 * @param tmpMap game map to detect collisions with objects
	 * @return True if the position is free, false otherwise.
	 */
	public boolean checkAround(int move, char[][] tmpMap) {
		Elements position = new Elements(this.x,this.y);
		
		position = changeCoords(position, move);
		
		return ((tmpMap[position.x][position.y] == 'X') || 
				(tmpMap[position.x][position.y] == 'I') || 
				(tmpMap[position.x][position.x] == 'S'));
	}
	
	/**
	 * Generate a random move, checking if is a valid movement by the checkAround function, 
	 * and call the changeCoords function to effect the move.
	 * @param position position of the element
	 * @param tmpMap game map to detect collisions with objects
	 * @return Return the result of the changeCoords function.
	 */
	public Elements generateMove(Elements position, char[][] tmpMap) {
		Random rand = new Random();
		int move;

		do{ move = rand.nextInt(4) + 1;
		} while (checkAround(move, tmpMap));

		return changeCoords(position, move);
	}
	
	/**
	 * Check if the hero stunned the ogre (if he is near him) and stop the ogre in affirmative case.
	 * if the ogre is stunned, it decreases the number of stopped turns.
	 * Otherwise, call the generateMove function to move the ogre and call the clubMovement to move the club.
	 * @param hero coordinates of the hero
	 * @param tmpMap game map to detect collisions with objects
	 */
	public void ogreMovement(Hero hero, char[][] tmpMap) {

		if((this.checkProximity(hero)) && (state != '8')) {
			state = '8';
			stop = 3;
		}

		if(stop != 0)
			stop--;				
		else {
			generateMove(this, tmpMap);

			state = 'O';
			
			if (tmpMap[x][y] == 'k')
				state = '$';

			clubMovement(tmpMap);
		}
	}

	/**
	 * Call the generateMove function and change the club coordinates by the function.
	 * @param tmpMap game map to detect collisions with objects
	 */
	public void clubMovement(char[][] tmpMap) {

		Elements clubPosition = new Elements(this.x, this.y);

		clubPosition = generateMove(clubPosition, tmpMap);

		clubX = clubPosition.x;
		clubY = clubPosition.y;
		club = '*';

		if (tmpMap[clubX][clubY] == 'k')
			club = '$';
	}
}
