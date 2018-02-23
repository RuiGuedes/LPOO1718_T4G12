package dkeep.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dkeep.logic.*;
import dkeep.logic.Game.GameState;

public class UserInteraction {

	public static void main(String[] args) {

		if(firstDungeon())
			secondDungeon();

	}


	public static boolean firstDungeon() {

		//Variables
		Game firsGame = new Game();
		char input = ' ';

		while((input != 'e') && (Game.gameState == GameState.PLAYING) && (Game.LEVEL != 2)) 
		{

			GameMap.print(firsGame.updateMap(firsGame.map.getMap()));

			input = readInput();

			firsGame.hero.heroMovement(input, firsGame.updateMap(firsGame.map.getMap()));

		}
		
		if((input != 'e') && (Game.gameState == GameState.PLAYING))
			return true;
		else 
			return false;
	}
	
	public static void secondDungeon() {

		//Variables
		Game secondGame = new Game();
		char input = ' ';
		
		//Reset Variables
		Lever.leverState = 'k';
			
		while((input != 'e') && (Game.gameState == GameState.PLAYING)) 
		{
			
			GameMap.print(secondGame.updateMap(secondGame.map.getMap()));
			
			input = readInput();

			secondGame.hero.heroMovement(input, secondGame.updateMap(secondGame.map.getMap()));

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
		return input;
	}

}
