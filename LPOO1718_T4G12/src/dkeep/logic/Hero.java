package dkeep.logic;

public class Hero extends Elements {

	public char state;

	public Hero(int x, int y, char state) {
		this.x = x;
		this.y = y;
		this.state = state;
	} 
 
	public void heroMovement(char input, char[][] tmpMap) {
		switch(input)
		{
		case 'w':
			if((tmpMap[x-1][y] == ' ') || (tmpMap[x-1][y] == 'K'))
				x--;
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
			else if(tmpMap[x-1][y] == 'Z')
				x -= 2;
			break;
		case 's': 
			if(tmpMap[x+1][y] == ' ') 
				x++;
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
			else if(tmpMap[x][y+1] == 'Z')
				y += 2;
			break;
		default:
			break;
		}
	}
}
