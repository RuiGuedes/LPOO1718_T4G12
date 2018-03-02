package dkeep.logic;

public class Hero extends Elements {

	public char state;

	public Hero(int x, int y, char state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public void heroMovement(char input, char[][] tmpMap) {

		//Hero Movement
		switch(input)
		{
		case 'w':
			if((tmpMap[x-1][y] == ' ') || (tmpMap[x-1][y] == 'K'))
				x--;
			else if(tmpMap[x-1][y] == 'k') {
				x--;

				if(Lock.lockType) 
					Lock.lockState = ' ';
				else 
					Lock.lockState = 'K';

				Lock.lockStatus = true;
			}
			break;
		case 's':
			if(tmpMap[x+1][y] == ' ') 
				x++;
			else if(tmpMap[x+1][y] == 'k') {
				x++;				
				if(Lock.lockType) 
					Lock.lockState = ' ';
				else {
					Lock.lockState = 'K';
					Door.openDoors();
				}

				Lock.lockStatus = true;
			}
			break;
		case 'a':
			if((tmpMap[x][y-1] == ' ') || (tmpMap[x][y-1] == 'K'))
				y--;
			else if(tmpMap[x][y-1] == 'I') {
				if(Lock.lockStatus) {
					Door tmp = new Door(Game.door.get(0).x, Game.door.get(0).y, 'S');
					Game.door.set(0,tmp);
				}
			}
			else if(tmpMap[x][y-1] == 'k') {
				y--;

				if(Lock.lockType) 
					Lock.lockState = ' ';
				else {
					Lock.lockState = 'K';
					Door.openDoors();
				}

				Lock.lockStatus = true;

			}
			else if(tmpMap[x][y-1] == 'S')
				Game.gameState = Game.GameState.VICTORY;
			break;
		case 'd':
			if((tmpMap[x][y+1] == ' ') || (tmpMap[x][y+1] == 'K'))
				y++;
			else if(tmpMap[x][y+1] == 'k') {
				y++;

				if(Lock.lockType) 
					Lock.lockState = ' ';
				else 
					Lock.lockState = 'K';

				Lock.lockStatus = true;
			}
			break;
		default:
			break;
		}
	}

}
