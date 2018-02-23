package dkeep.logic;

public class GameMap {
	
	
	public GameMap() {
		
	}
	
	public char[][] getMap() {
		if(Game.LEVEL == 1) {
			return new char[][] { 
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
		else 
			return new char[][] { 
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

	public static void print(char[][] map) {

		for(int i = 0; i < map.length; i++)
		{
			for(int k = 0; k < map[i].length; k++)
				System.out.print(map[i][k] + " ");

			System.out.println();
		}
	}


}
