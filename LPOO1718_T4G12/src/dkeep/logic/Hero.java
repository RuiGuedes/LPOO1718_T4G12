package dkeep.logic;

public class Hero extends Elements {

	public Hero() {

		if(Game.LEVEL == 1) {
			this.x = 1;
			this.y = 1;
		}
		else {
			this.x = 7;
			this.y = 1;
		}
	}

	public void heroMovement(char input, char[][] tmpMap) {

		//Hero Movement
		switch(input)
		{
		case 'w':
			if(Game.LEVEL == 1) 
				if(tmpMap[x-1][y] == ' ')
					x--;
			else {
				System.out.println("ENTORU");
				if((tmpMap[x-1][y] == ' ') || (tmpMap[x-1][y] == 'K'))
					x--;
				else if(tmpMap[x][y+1] == 'k') {
					x--;
					Lever.leverState = 'K';
				}
			}
			break;
		case 's':
			if(tmpMap[x+1][y] == ' ') 
				x++;
			break;
		case 'a':
			if(Game.LEVEL == 1) {
				if(tmpMap[x][y-1] == 'k') {
					y--;
					Lever.leverState = 'K';
				}
				else if((tmpMap[x][y-1] == ' ') || (tmpMap[x][y-1] == 'K'))
					y--;
				else if(tmpMap[x][y-1] == 'S') {
					Game.LEVEL = 2;
				}
			}
			else {
				if(tmpMap[x][y-1] == ' ')
					y--;
				else if(tmpMap[x][y-1] == 'I') {
					if(Lever.leverState == 'K') {
						//Ative door
					}
				}
				else if(tmpMap[x][y-1] == 'S')
					Game.gameState = Game.GameState.VICTORY;
			}
			break;
		case 'd':
			if(Game.LEVEL == 1) 
				if(tmpMap[x][y+1] == ' ')
					y++;
			else {
				if((tmpMap[x][y+1] == ' ') || (tmpMap[x][y+1] == 'K'))
					y++;
				else if(tmpMap[x][y+1] == 'k') {
					y++;
					Lever.leverState = 'K';
				}
			}
			break;
		default:
			break;
		}
	}

}
