import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class BasicGameLogic {

	public static void main(String[] args) {

		//Game map
		char[][] map = { {'X','X','X','X','X','X','X','X','X','X'}, {'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'}, {'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, {'X','X','X',' ','X','X','X','X',' ','X'}, 
				{'X',' ','I',' ','I',' ','X','K',' ','X'}, {'X','X','X','X','X','X','X','X','X','X'} };

		//Guard route
		char[][] guardRoute = { {1,7}, {2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},
				{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8},{1,8}};

		//Hero position
		int xH = 1;
		int yH = 1;

		//Hero position
		int xG = 1;
		int yG = 8;
		int guardIterator = 0;

		char input = ' ';

		while(input != 'e')
		{	
			printGameMap(map);

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Up(w)\nDown(s)\nLeft(a)\nRight(d)\nExit(e)\n\nCommand: ");
			try {
				input = (reader.readLine()).charAt(0);
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch(input)
			{
			case 'w':
				if(map[xH-1][yH] == ' '){
					map[xH][yH] = ' ';
					xH--;
					map[xH][yH] = 'H';
				}
				break;
			case 's':
				if(map[xH+1][yH] == ' '){
					map[xH][yH] = ' ';
					xH++;
					map[xH][yH] = 'H';
				}
				break;
			case 'a':
				if(map[xH][yH-1] == ' '){
					map[xH][yH] = ' ';
					yH--;
					map[xH][yH] = 'H';
				}
				else if(map[xH][yH-1] == 'K')
				{
					map[xH][yH] = ' ';
					yH--;
					map[xH][yH] = 'H';

					//Open doors
					map[5][0] = 'S';
					map[6][0] = 'S';
				}
				else if(map[xH][yH-1] == 'S')
				{
					System.out.println("\n        VICTORY !!!");
					input = 'e';
				}
				break;
			case 'd':
				if(map[xH][yH+1] == ' '){
					map[xH][yH] = ' ';
					yH++;
					map[xH][yH] = 'H';
				}
				break;
			default:
				break;
			}

			map[xG][yG] = ' ';
			xG = guardRoute[guardIterator][0];
			yG = guardRoute[guardIterator][1];
			map[xG][yG] = 'G';
			guardIterator++;
			if(guardIterator == guardRoute.length)
				guardIterator = 0;


			if((map[xH+1][yH] == 'G') || (map[xH-1][yH] == 'G') || (map[xH][yH+1] == 'G') || (map[xH][yH-1] == 'G'))
			{
				printGameMap(map);
				System.out.println("\n        GAME OVER :(");
				input = 'e';
			}

		}

	}

	public static void printGameMap(char[][] map) {

		for(int i = 0; i < map.length; i++)
		{
			for(int k = 0; k < map[i].length; k++)
				System.out.print(map[i][k] + " ");

			System.out.println();
		}
	}

}



