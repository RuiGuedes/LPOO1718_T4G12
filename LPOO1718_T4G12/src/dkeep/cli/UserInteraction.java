package dkeep.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dkeep.logic.*;
import dkeep.logic.Game.GameState;

public class UserInteraction {

	public static void main(String[] args) {

		if(firstDungeon() == 0)
			secondDungeon();

	}


	public static int firstDungeon() {

		//Variables
		Game.LEVEL = 1;
		Game firsGame = new Game();
		char input = ' ';

		while((input != 'e') && (Game.gameState == GameState.PLAYING) && (Game.LEVEL != 2)) 
		{

			GameMap.print(firsGame.updateMap(firsGame.map.getMap()));

			input = readInput();

			firsGame.hero.heroMovement(input, firsGame.updateMap(firsGame.map.getMap()));
			
			if(Lever.leverState == 'K')
				firsGame.door = firsGame.openDoors(firsGame.door);

		}
		
		if((input != 'e') && (Game.gameState == GameState.PLAYING))
			return 0;
	
		return 1;
	}
	
	public static int secondDungeon() {

		//Variables
		Game.LEVEL = 2;
		Game secondGame = new Game();
		char input = ' ';
		
		//Reset Variables
		Lever.leverState = 'k';
		
		System.out.println(Lever.leverState);
		
		
		while((input != 'e') && (Game.gameState == GameState.PLAYING)) 
		{
			
			if(Game.LEVEL == 2)
				System.out.println(Game.LEVEL);
			
			System.out.println(secondGame.hero.x + "  " + secondGame.hero.y);
			
			GameMap.print(secondGame.updateMap(secondGame.map.getMap()));
			
			input = readInput();

			secondGame.hero.heroMovement(input, secondGame.updateMap(secondGame.map.getMap()));

		}
	
		return 1;
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
