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
	public Hero(int x, int y, char state) {
		super(x, y);
		this.state = state;
	} 
 
	/**
	 * Analyze whether the cell to which the user wishes to move the hero is empty, and enables movement.
	 * If the hero enable the lever the doors are open. If the hero picks up the key and collides with the door, 
	 * the door unlocks.
	 * @param input movement direction of the hero
	 * @param tmpMap game map to detect collisions with objects
	 */
	public void heroMovement(char input, char[][] tmpMap) {
		switch(input)
		{
		case 'w':
			if((tmpMap[x-1][y] == ' ') || (tmpMap[x-1][y] == 'K'))
				x--;
			else if(tmpMap[x-1][y] == 'I') {
				if(Lock.lockStatus) {
					Door tmp = new Door(Game.door.get(0).x, Game.door.get(0).y, 'S',Game.door.get(0).type);
					Game.door.set(0,tmp);
				}
			}
			else if((tmpMap[x-1][y] == 'k') && (Lock.lockType)) {
				x--;
				Lock.lockState = ' ';
				Lock.lockStatus = true;
			}
			else if((tmpMap[x-1][y] == 'k') && (!Lock.lockType)) {
				Lock.lockState = 'K';
				Door.openDoors();
				Lock.lockStatus = true;
			}
			else if(tmpMap[x-1][y] == 'S')
				Game.gameState = Game.GameState.VICTORY;
			else if(tmpMap[x-1][y] == 'Z')
				x -= 2;
			break;
		case 's': 
			if(tmpMap[x+1][y] == ' ') 
				x++;
			else if(tmpMap[x+1][y] == 'I') {
				if(Lock.lockStatus) {
					Door tmp = new Door(Game.door.get(0).x, Game.door.get(0).y, 'S',Game.door.get(0).type);
					Game.door.set(0,tmp);
				}
			}
			else if((tmpMap[x+1][y] == 'k') && (Lock.lockType)) {
				x++;				
				Lock.lockState = ' ';
				Lock.lockStatus = true;
			}
			else if((tmpMap[x+1][y] == 'k') && (!Lock.lockType)) {
				Lock.lockState = 'K';
				Door.openDoors();
				Lock.lockStatus = true;
			}
			else if(tmpMap[x+1][y] == 'S')
				Game.gameState = Game.GameState.VICTORY;
			else if(tmpMap[x+1][y] == 'Z')
				x += 2;
			break;
		case 'a':
			if(tmpMap[x][y-1] == ' ')
				y--;
			else if(tmpMap[x][y-1] == 'I') {
				if(Lock.lockStatus) {
					Door tmp = new Door(Game.door.get(0).x, Game.door.get(0).y, 'S',Game.door.get(0).type);
					Game.door.set(0,tmp);
				}
			}
			else if((tmpMap[x][y-1] == 'k') && (Lock.lockType)){
				y--;
				Lock.lockState = ' ';
				Lock.lockStatus = true;
			}
			else if((tmpMap[x][y-1] == 'k') && (!Lock.lockType)){
				Lock.lockState = 'K';
				Door.openDoors();
				Lock.lockStatus = true;

			}
			else if(tmpMap[x][y-1] == 'S')
				Game.gameState = Game.GameState.VICTORY;
			else if(tmpMap[x][y-1] == 'Z')
					y -= 2;
			break;
		case 'd':
			if((tmpMap[x][y+1] == ' ') || (tmpMap[x][y+1] == 'K'))
				y++;
			else if(tmpMap[x][y+1] == 'I') {
				if(Lock.lockStatus) {
					Door tmp = new Door(Game.door.get(0).x, Game.door.get(0).y, 'S',Game.door.get(0).type);
					Game.door.set(0,tmp);
				}
			}
			else if((tmpMap[x][y+1] == 'k') && (Lock.lockType)){
				y++;
				Lock.lockState = ' ';
				Lock.lockStatus = true;
			}
			else if((tmpMap[x][y+1] == 'k') && (!Lock.lockType)){
				Lock.lockState = 'K';
				Door.openDoors();
				Lock.lockStatus = true;
			}
			else if(tmpMap[x][y+1] == 'S')
				Game.gameState = Game.GameState.VICTORY;
			else if(tmpMap[x][y+1] == 'Z')
				y += 2;
			break;
		default:
			break;
		}
	}
}
