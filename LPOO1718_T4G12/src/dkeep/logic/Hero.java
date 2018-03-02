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
					Lever.leverState = 'K';
				}
			break;
		case 's':
			if(tmpMap[x+1][y] == ' ') 
				x++;
			break;
		case 'a':
				if((tmpMap[x][y-1] == ' ') || (tmpMap[x][y-1] == 'K'))
					y--;
				else if(tmpMap[x][y-1] == 'I') {
					if(Lever.leverState == 'K') {
						Door tmp = new Door(Game.door.get(0).x, Game.door.get(0).y, 'S');
						Game.door.set(0,tmp);
					}
				}
				else if(tmpMap[x][y-1] == 'k') {
					y--;
					Lever.leverState = 'K';
					Door.openDoors();
				}
				else if(tmpMap[x][y-1] == 'S' && Game.LEVEL == 1)
					Game.LEVEL = 2;
				else if(tmpMap[x][y-1] == 'S' && Game.LEVEL == 2)
					Game.gameState = Game.GameState.VICTORY;
			break;
		case 'd':
				if((tmpMap[x][y+1] == ' ') || (tmpMap[x][y+1] == 'K'))
					y++;
				else if(tmpMap[x][y+1] == 'k') {
					y++;
					Lever.leverState = 'K';
				}
		break;
	default:
		break;
	}
}
	
	public boolean isHeroAtPosition(int x, int y) {
		return ((this.x == x) && (this.y == y));
	}
}
