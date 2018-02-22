package dkeep.logic;

public class GameMap {

	public char map[][];

	public GameMap() {

		if(Game.LEVEL == 1) {
			map = new char[][] { 
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'}, 
				{'X',' ',' ',' ',' ',' ','X',' ',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X','X'} };
		}
		else {
			map = new char[][] { 
				{'X','X','X','X','X','X','X','X','X'},
				{' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X'} };
		}
	}


	public void printGameMap(Hero hero, Guard guard, Ogre ogre, Lever lever, Door[] doors) {

		for(int i = 0; i < map.length; i++)
		{
			for(int k = 0; k < map[i].length; k++) {

				if(hero.equals(i,k))
					System.out.print("H ");
				else if(lever.equals(i,k))
					System.out.print(lever.state + " ");
				else if((guard.equals(i,k)) && (Game.LEVEL == 1))
					System.out.print("G ");
				else if((ogre.equals(i,k)) && (Game.LEVEL == 2))
					System.out.print(ogre.state + " ");
				else if((lever.equals(i,k)) && (Game.LEVEL == 1))
					System.out.print(lever.state + " ");

				
				for(int j = 0; j < doors.length; j++) {
					if(doors[j].equals(i,k))
						System.out.print(doors[j].state + " ");
				}
				
				if()
				System.out.print(map[i][k] + " ");
			}


			System.out.println();
		}
	}



}
