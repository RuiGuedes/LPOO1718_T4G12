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
			if((tmpMap[x-1][y] == ' ') || (tmpMap[x-1][y] == 'k') || (tmpMap[x-1][y] == 'K'))
				x--;
			break;
		case 's':
			if(tmpMap[x+1][y] == ' ') {
				if(Game.LEVEL == 2) 
					if((x == 1) && (y == 7)) 
						Lever.leverState = 'K';
				x++;
			}
			break;
		case 'a':
			if(tmpMap[x][y-1] == ' ') {
				if(Game.LEVEL == 2) {
					if((x == 1) && (y == 7))
						Lever.leverState = 'K';
				}
				else {
					if(tmpMap[x][y-1] == 'k')
						Lever.leverState = 'K';
				}
				y--;
			}
			else if(tmpMap[x][y-1] == 'I') {
				if(Game.LEVEL == 2) {
					if(Lever.leverState == 'K')
						tmpMap[x][y-1] = 'S';
				}
				else {
					
				}
				
			}
			else if(tmpMap[x][y-1] == 'S') {
				if(Game.LEVEL == 1)
					Game.LEVEL = 2;
				else
					Game.gameState = Game.GameState.VICTORY;
			}
			break;
		case 'd':
			
			if((tmpMap[x][y+1] == ' ') || (tmpMap[x][y+1] == 'K') || (tmpMap[x][y+1] == 'k'))
				y++;
			break;
		default:
			break;
		}
	}

}
