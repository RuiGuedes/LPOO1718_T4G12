package dkeep.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dkeep.logic.*;
import dkeep.logic.Game.GameState;

public class UserInteraction {
	
	static char[][] dungeon = { 
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'}, 
			{'X',' ','I',' ','I',' ','X','k',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'} };
	
	static char[][] keep = { 
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ','O',' ',' ','k','X'},
			{'X',' ',' ',' ','*',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','A',' ',' ',' ',' ',' ',' ','X'},  
			{'X','X','X','X','X','X','X','X','X'} };
	
	public static void main(String[] args) {
		if(firstDungeon())
			secondDungeon();
	}

	public static boolean firstDungeon() {
		String guardType = "Rookie";
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		Game firstGame = new Game(gameMap,guardType,1);
		char input = ' ';

		while((input != 'e') && (Game.gameState == GameState.PLAYING)) {
			//Print the updated map
			GameMap.print(firstGame.updateMap(firstGame.map.getMap()));
			
			//Read user input
			input = readInput();
			
			//Moves the hero in the respective direction
			firstGame.hero.heroMovement(input, firstGame.updateMap(firstGame.map.getMap()));
			
			//Check if level is complete
			if(Game.gameState == Game.GameState.VICTORY)
				break;
			
			//Executes guard route
			if(firstGame.guard[firstGame.guardRouting].guardMovement()) {
				if(firstGame.guardRouting == 2)
					firstGame.guardRouting = 0;
				else
					firstGame.guardRouting++;
			}
			
			//Check the status game in order to continue playing or not
			firstGame.checkGameStatus();
			if(Game.gameState == GameState.GAMEOVER) {
				GameMap.print(firstGame.updateMap(firstGame.map.getMap()));
				System.out.println("\nGame Over !");
			}
		}
		
		//Returns true if first dungeon is completed, false if game over
		if((input != 'e') && (Game.gameState == GameState.VICTORY))
			return true;
		else 
			return false;
	}
	
	public static void secondDungeon() {
		int numberOfOgres = 2;
		Game.LEVEL = 2;
		Game.gameState = GameState.PLAYING;
		GameMap gameMap = new GameMap(keep);
		Game secondGame = new Game(gameMap,"Rookie",numberOfOgres);
		Lock.lockState = 'k';
		char input = ' ';

		
		while((input != 'e') && (Game.gameState == GameState.PLAYING)) 
		{
			//Print the updated map
			GameMap.print(secondGame.updateMap(secondGame.map.getMap()));
			
			//Read user input
			input = readInput();

			//Moves the hero in the respective direction
			secondGame.hero.heroMovement(input, secondGame.updateMap(secondGame.map.getMap()));
			
			//Moves the ogre and club in a randoom direction
			for(int i = 0; i < secondGame.horde; i++)
				secondGame.ogre.get(i).ogreMovement(secondGame.hero.x, secondGame.hero.y, secondGame.updateMap(secondGame.map.getMap()));
			
			//Check the status game in order to continue playing or not
			secondGame.checkGameStatus();
			if(Game.gameState == GameState.GAMEOVER) {
				GameMap.print(secondGame.updateMap(secondGame.map.getMap()));
				System.out.println("\nGame Over !");
			}
		}
		
		if(Game.gameState == GameState.VICTORY)
			System.out.println("Victory !");

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
		
		System.out.println();
				
		return input;
	}

}
