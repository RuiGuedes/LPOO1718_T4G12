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

		//Hero position
		int x = 1;
		int y = 1;

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
				if(map[x-1][y] == ' '){
					map[x][y] = ' ';
					x--;
					map[x][y] = 'H';
				}
				break;
			case 's':
				if(map[x+1][y] == ' '){
					map[x][y] = ' ';
					x++;
					map[x][y] = 'H';
				}
				break;
			case 'a':
				if(map[x][y-1] == ' '){
					map[x][y] = ' ';
					y--;
					map[x][y] = 'H';
				}
				else if(map[x][y-1] == 'K')
				{
					map[x][y] = ' ';
					y--;
					map[x][y] = 'H';

					//Open doors
					map[5][0] = 'S';
					map[6][0] = 'S';
				}
				else if(map[x][y-1] == 'S')
				{
					System.out.println("\n        VICTORY !!!");
					input = 'e';
				}
				break;
			case 'd':
				if(map[x][y+1] == ' '){
					map[x][y] = ' ';
					y++;
					map[x][y] = 'H';
				}
				break;
			default:
				break;
			}
			
			if((map[x+1][y] == 'G') || (map[x-1][y] == 'G') || (map[x][y+1] == 'G') || (map[x][y-1] == 'G'))
			{
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

