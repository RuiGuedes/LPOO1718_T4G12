import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;


public class BasicGameLogic {

//	public static void main(String[] args) {
//
//		if(firstDungeon() == 0)
//			secondDungeon();
//	}

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
		boolean gameOver = false;

		while(input != 'e')
		{	
			printGameMap(map);

			input = readInput();

			//Hero Movement
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
				else if(map[xH][yH-1] == 'K') {
					map[xH][yH] = ' ';
					yH--;
					map[xH][yH] = 'H';

					//Open doors
					map[5][0] = 'S';
					map[6][0] = 'S';
				}
				else if(map[xH][yH-1] == 'S') {
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

			//Guard Movement
			if(map[xG][yG] != 'H')
				map[xG][yG] = ' ';
			xG = guardRoute[guardIterator][0];
			yG = guardRoute[guardIterator][1];
			if(map[xG][yG] == 'H')
				gameOver = true;
			map[xG][yG] = 'G';
			guardIterator++;
			if(guardIterator == guardRoute.length)
				guardIterator = 0;


			if((map[xH+1][yH] == 'G') || (map[xH-1][yH] == 'G') || (map[xH][yH+1] == 'G') || (map[xH][yH-1] == 'G') || gameOver)
			{
				printGameMap(map);
				System.out.println("\nGAME OVER :(");
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
				{'X',' ',' ',' ','*',' ',' ',' ','X'}, 
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

		//Club position
		int xC = 2;
		int yC = 4;

		//Variables
		char input = ' ';
		boolean keyStatus = false;
		boolean gameOver = false;

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
					System.out.println("\nVICTORY !!!");
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

			//Ogre/Club variables
			Random rand = new Random();
			int club;
			int move;

			//Generates club new position
			do	{
				move = rand.nextInt(4) + 1;
			}while(((move == 1) && (xO == 1)) || ((move == 2) && (xO == 7)) || ((move == 3) && (yO == 1)) || ((move == 4) && (yO == 7)));


			//Ogre Movement
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

			//Generates club new position
			do	{
				club = rand.nextInt(4) + 1;
			}while(((club == 1) && (xO == 1)) || ((club == 2) && (xO == 7)) || ((club == 3) && (yO == 1)) || ((club == 4) && (yO == 7)));

			//Erases club last position
			if((xC == 1) && (yC == 7) && keyStatus && (map[xC][yC] != 'O') && (map[xC][yC] != 'H'))
				map[xC][yC] = 'K';
			else if((xC == 1) && (yC == 7) && (map[xC][yC] != 'O') && (map[xC][yC] != 'H'))
				map[xC][yC] = 'k';
			else if((map[xC][yC] != 'H') && (map[xC][yC] != 'X') && (map[xC][yC] != 'O') && (map[xC][yC] != 'I'))
				map[xC][yC] = ' ';

			//Club new position
			switch(club)
			{
			case 1:	//Lands on top
				if(map[xO-1][yO] == ' ')
					map[xO-1][yO] = '*';
				else if((map[xO-1][yO] == 'k') || (map[xO-1][yO] == 'K'))
					map[xO-1][yO] = '$';
				else if(map[xO-1][yO] == 'H') {
					map[xO-1][yO] = '*';	
					gameOver = true;
				}
				xC = xO - 1;
				yC = yO;
				break;
			case 2:	//Lands on bottom
				if(map[xO+1][yO] == ' ')
					map[xO+1][yO] = '*';
				else if(map[xO+1][yO] == 'H') {
					map[xO+1][yO] = '*';	
					gameOver = true;
				}
				xC = xO + 1;
				yC = yO;
				break;
			case 3:	//Lands on left
				if(map[xO][yO-1] == ' ')
					map[xO][yO-1] = '*';
				else if(map[xO][yO-1] == 'H') {
					map[xO][yO-1] = '*';	
					gameOver = true;
				}
				yC = yO - 1;
				xC = xO;
				break;
			case 4:	//Lands on right
				if(map[xO][yO+1] == ' ')
					map[xO][yO+1] = '*';
				else if((map[xO][yO+1] == 'k') || (map[xO][yO+1] == 'K'))
					map[xO][yO+1] = '$';
				else if(map[xO][yO+1] == 'H') {
					map[xO][yO+1] = '*';	
					gameOver = true;
				}
				yC = yO + 1;
				xC = xO;
				break;
			}

			//Checks game status
			if((map[xH+1][yH] == 'O') || (map[xH-1][yH] == 'O') || (map[xH][yH+1] == 'O') || (map[xH][yH-1] == 'O') || gameOver)
			{
				printGameMap(map);
				System.out.println("\nGAME OVER :(");
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



