import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;


public class BasicGameLogic {

	public static void main(String[] args) {

		//if(firstDungeon() == 0)
		secondDungeon();

	}

	public static int firstDungeon() {

		//Game map
		char[][] map = { 
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'}, 
				{'X',' ','I',' ','I',' ','X','K',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X','X'} };

		//Guard route
		char[][] guardRoute = { {1,7}, {2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},
				{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8},{1,8}};

		//Hero position
		int xH = 1;
		int yH = 1;

		//Guard position
		int xG = 1;
		int yG = 8;

		//Variables
		int guardIterator = 0;
		char input = ' ';

		while(input != 'e')
		{	
			printGameMap(map);

			input = readInput();

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
					System.out.println("\n  VICTORY !!!");
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
				System.out.println("\n  GAME OVER :(");
				return 1;
			}	
		}

		return 0;
	}

	public static int secondDungeon() {

		//Game map
		char[][] map = { 
				{'X','X','X','X','X','X','X','X','X'},
				{'I',' ',' ',' ','O',' ',' ','k','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','H',' ',' ',' ',' ',' ',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X'} };

		//Hero position
		int xH = 7;
		int yH = 1;

		//Ogre position
		int xO = 1;
		int yO = 4;

		//Variables
		char input = ' ';
		boolean keyStatus = false; 

		while(input != 'e')
		{	
			printGameMap(map);

			input = readInput();

			//Hero Movement
			switch(input)
			{
			case 'w':
				if((map[xH-1][yH] == ' ') || (map[xH-1][yH] == 'k') || (map[xH-1][yH] == 'K')){
					map[xH][yH] = ' ';
					xH--;
					map[xH][yH] = 'H';
				}
				break;
			case 's':
				if(map[xH+1][yH] == ' '){
					if((xH == 1) && (yH == 7))
					{
						map[xH][yH] = 'K';
						keyStatus = true;
					}
					else
						map[xH][yH] = ' ';
					xH++;
					map[xH][yH] = 'H';
				}
				break;
			case 'a':
				if(map[xH][yH-1] == ' '){
					if((xH == 1) && (yH == 7))
					{
						map[xH][yH] = 'K';
						keyStatus = true;
					}
					else
						map[xH][yH] = ' ';
					yH--;
					map[xH][yH] = 'H';
				}
				else if(map[xH][yH-1] == 'I')
				{
					if(keyStatus)
						map[xH][yH-1] = 'S';
				}
				else if(map[xH][yH-1] == 'S')
				{
					System.out.println("\n  VICTORY !!!");
					input = 'e';
				}
				break;
			case 'd':
				if((map[xH][yH+1] == ' ') || (map[xH][yH+1] == 'K') || (map[xH][yH+1] == 'k')){
					map[xH][yH] = ' ';
					yH++;
					map[xH][yH] = 'H';
				}
				break;
			default:
				break;
			}

			//Ogre Movement
			Random rand = new Random();
			int move = rand.nextInt(4) + 1;

			switch(move)
			{
			case 1:	//Moves up
				if(map[xO-1][yO] != 'X') {	
					map[xO][yO] = ' ';
					xO--;
					if((xO == 1) && (yO == 7))
						map[xO][yO] = '$';
					else
						map[xO][yO] = 'O';
				}
				break;
			case 2:	//Moves down
				if(map[xO+1][yO] != 'X') {	
					if((xO == 1) && (yO == 7) && keyStatus)
						map[xO][yO] = 'K';
					else if((xO == 1) && (yO == 7))
						map[xO][yO] = 'k';
					else
						map[xO][yO] = ' ';

					xO++;
					map[xO][yO] = 'O';		
				}
				break;
			case 3:	//Moves left
				if((map[xO][yO-1] != 'X') && (map[xO][yO-1] != 'I') && (map[xO][yO-1] != 'S'))  {	
					if((xO == 1) && (yO == 7) && keyStatus)
						map[xO][yO] = 'K';
					else if((xO == 1) && (yO == 7))
						map[xO][yO] = 'k';
					else
						map[xO][yO] = ' ';

					yO--;
					map[xO][yO] = 'O';		
				}
				break;
			case 4:	//Moves right
				if(map[xO][yO+1] != 'X') {
					map[xO][yO] = ' ';
					yO++;
					if((xO == 1) && (yO == 7))
						map[xO][yO] = '$';
					else
						map[xO][yO] = 'O';
				}
				break;
			}

			if((map[xH+1][yH] == 'O') || (map[xH-1][yH] == 'O') || (map[xH][yH+1] == 'O') || (map[xH][yH-1] == 'O'))
			{
				printGameMap(map);
				System.out.println("\n  GAME OVER :(");
				return 1;
			}	
		}

		return 0;
	}


	public static char readInput() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nCommands: Up(w) - Down(s) - Left(a) - Right(d) - Exit(e)\n\nCommand: ");
		char input = ' ';
		try {
			input = (reader.readLine()).charAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
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



