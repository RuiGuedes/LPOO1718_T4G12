package dkeep.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dkeep.logic.*;
import dkeep.logic.Game.GameState;

public class UserInteraction {

	public static void main(String[] args) {
		//if(firstDungeon())
			secondDungeon();
	}

	public static boolean firstDungeon() {

		//Variables
		Game firsGame = new Game();
		char input = ' ';

		while((input != 'e') && (Game.gameState == GameState.PLAYING) && (Game.LEVEL != 2)) 
		{
			
			//Print the updated map
			GameMap.print(firsGame.updateMap(firsGame.map.getMap()));
			
			//Read user input
			input = readInput();
			
			//Moves the hero in the respective direction
			firsGame.hero.heroMovement(input, firsGame.updateMap(firsGame.map.getMap()));
			
			//Executes guard route
			firsGame.guard.guardMovement();
			
			//Check the status game in order to continue playing or not
			firsGame.checkGameStatus();
			if(Game.gameState == GameState.GAMEOVER) {
				GameMap.print(firsGame.updateMap(firsGame.map.getMap()));
				System.out.println("\nGame Over !");
			}
		}
		
		//Returns true if first dungeon is completed, false if game over
		if((input != 'e') && (Game.gameState == GameState.PLAYING))
			return true;
		else 
			return false;
	}
	
	public static void secondDungeon() {

		//Variables
		Game secondGame = new Game();
		Lever.leverState = 'k';
		char input = ' ';
			
		while((input != 'e') && (Game.gameState == GameState.PLAYING)) 
		{
			//Print the updated map
			GameMap.print(secondGame.updateMap(secondGame.map.getMap()));
			
			//Read user input
			input = readInput();

			//Moves the hero in the respective direction
			secondGame.hero.heroMovement(input, secondGame.updateMap(secondGame.map.getMap()));
			
			secondGame.ogre.ogreMovement(secondGame.updateMap(secondGame.map.getMap()));
		}

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
