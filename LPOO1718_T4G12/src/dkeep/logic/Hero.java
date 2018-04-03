package dkeep.logic;

/**
 * Hero.java - class to the hero representation in the game
 * @author Rui Guedes and César Pinho
 * @see Elements
 */
public class Hero extends Elements {

	/**
	 * Representation mark
	 */
	public char state;

	/**
	 * Class constructor specifying coordinates and mark on the game map.
	 * @param x map x coordinate
	 * @param y map y coordinate
	 * @param state representation mark on the game map
	 */
	public Hero(Elements e, char state) {
		super(e);
		this.state = state;
	} 

	/**
	 * Check if the next position is free to move 
	 * @param newP next position
	 * @param tmpMap game map
	 * @return next position free to move or null if the position is occupied
	 */
	public Elements checkNextPosition(Elements newP, char[][] tmpMap) {

		if((tmpMap[newP.x][newP.y] == 'I') && Lock.lockStatus) {
			Door tmp = new Door(Game.door.get(0), 'S',Game.door.get(0).type);
			Game.door.set(0,tmp);
		}
		else if((tmpMap[newP.x][newP.y] == 'k') && (!Lock.lockType)) {
			Lock.lockState = 'K';
			Door.openDoors();
			Lock.lockStatus = true;
		}
		else if(tmpMap[newP.x][newP.y] == 'S') {
			Game.gameState = Game.GameState.VICTORY;
			return newP;
		}
		else if(tmpMap[newP.x][newP.y] == 'Z') {
			int deltaX = newP.x - x, deltaY = newP.y - y;
			newP.x += deltaX;
			newP.y += deltaY;
			return newP;
		}
		else if((tmpMap[newP.x][newP.y] == 'k') && (Lock.lockType)) {
			Lock.lockState = ' ';
			Lock.lockStatus = true;
			return newP;
		}
		else if(tmpMap[newP.x][newP.y] == ' ')
			return newP;

		return null;
	}

	/**
	 * Change the hero coordinates to the new position whether is a free position
	 * @param position new position of the hero
	 */
	public void changeCoordinates(Elements position) {
		if (position != null) {
			this.x = position.x;
			this.y = position.y;
		}
	}

	/**
	 * Analyze whether the cell to which the user wishes to move the hero is empty, and enables movement.
	 * If the hero enable the lever the doors are open. If the hero picks up the key and collides with the door, 
	 * the door unlocks.
	 * @param input movement direction of the hero
	 * @param tmpMap game map to detect collisions with objects
	 */
	public void heroMovement(char input, char[][] tmpMap) {
		Elements position;

		switch(input)
		{
		case 'w':
			position = new Elements(x-1, y);
			changeCoordinates(checkNextPosition(position, tmpMap));
			
			break;
		case 's': 
			position = new Elements(x+1, y);
			changeCoordinates(checkNextPosition(position, tmpMap));
			
			break;
		case 'a':
			position = new Elements(x, y-1);
			changeCoordinates(checkNextPosition(position, tmpMap));
			
			break;
		case 'd':
			position = new Elements(x, y+1);
			changeCoordinates(checkNextPosition(position, tmpMap));
			
			break;
		default:
			break;
		}
	}
}
