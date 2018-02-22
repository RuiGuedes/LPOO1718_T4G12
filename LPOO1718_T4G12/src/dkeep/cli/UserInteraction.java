package dkeep.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInteraction {

	public static void main(String[] args) {
		
//		if(firstDungeon() == 0)
//			secondDungeon();
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
