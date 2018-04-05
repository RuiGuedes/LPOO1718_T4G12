package dkeep.logic.elements;

import dkeep.logic.Game;
import dkeep.logic.Game.GameState;

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
	 * @param hero coordinates of the hero
	 * @param state representation mark on the game map
	 */
	public Hero(Elements hero, char state) {
		super(hero.x,hero.y);
		this.state = state;
	} 

	/**
	 * Check if the next position is free to move. 
	 * @param newP new position
	 * @param tmpMap game map
	 * @return next position free to move or null if the position is occupied
	 */
	public Elements checkNextPosition(Elements newP, char[][] tmpMap) {
		Elements position;
		
		if(tmpMap[newP.x][newP.y] == ' ')
			return newP;
		
		position=checkLock(newP, tmpMap);
		if(position != null)
			return position;
		
		position=checkDoors(newP, tmpMap);
		if(position != null)
			return position;

		return null;
	}
	
	/**
	 * Check if exist a lock in one position and change the map take in account the type of lock.
	 * @param newP position to check
	 * @param tmpMap game map to access to the position
	 * @return Null if the lock is a lever and can't move to the position, and the position if the lock is a key.
	 */
	public Elements checkLock(Elements newP, char[][] tmpMap) {
		
		if((tmpMap[newP.x][newP.y] == 'k') && (!Lock.lockType)) {
			Lock.lockState = 'K';
			Door.openDoors();
			Lock.lockStatus = true;
		}
		else  if((tmpMap[newP.x][newP.y] == 'k') && (Lock.lockType)) {
			Lock.lockState = ' ';
			Lock.lockStatus = true;
			return newP;
		}
		return null;
	}
	
	/**
	 * Check if exist a door in the newP position. 
	 * If is a exit door and the lock is enable, the door is open.
	 * If is a open exit door, the game state change to Victory.
	 * If is a normal door, the hero can move to the other side of the door.
	 * @param newP position to check
	 * @param tmpMap game map to access to the position
	 * @return Null if the door was locked, and the new position otherwise.
	 */
	public Elements checkDoors(Elements newP, char[][] tmpMap) {
		if((tmpMap[newP.x][newP.y] == 'I') && Lock.lockStatus) {
			Door tmp = new Door(Game.door.get(0), 'S',Game.door.get(0).type);
			Game.door.set(0,tmp);
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
	 * Analyze whether the cell to which the user wishes to move the hero is empty, 
	 * calling the checkNextPosition function, and enables movement calling the changeCoordinates function.
	 * @param input movement direction of the hero
	 * @param tmpMap game map to detect collisions with objects
	 */
	public void heroMovement(char input, char[][] tmpMap) {

		switch(input) {
		case 'w': changeCoordinates(checkNextPosition(new Elements(x-1, y), tmpMap));
			
			break;
		case 's': changeCoordinates(checkNextPosition(new Elements(x+1, y), tmpMap));
			
			break;
		case 'a': changeCoordinates(checkNextPosition(new Elements(x, y-1), tmpMap));
			
			break;
		case 'd': changeCoordinates(checkNextPosition(new Elements(x, y+1), tmpMap));
			
			break;
		}
	}
}
