package dkeep.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dkeep.logic.*;
import dkeep.logic.Game.GameState;

public class UserInteraction {

	public static void main(String[] args) {

		//Variables
		Game game = new Game();
		
		char input = ' ';
		
		while((input != 'e') && (Game.gameState == GameState.PLAYING)) 
		{
			
			GameMap.print(game.updateMap(game.map.getMap()));
			
			input = readInput();
			
			game.hero.heroMovement(input, game.updateMap(game.map.getMap()));
			
			
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
