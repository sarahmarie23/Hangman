import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class hangman{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
	}
	public static void play() throws FileNotFoundException, IOException {
		Scanner console = new Scanner(System.in);
		ArrayList<String> guessedLetters = new ArrayList<String>();
	
		String word = getWord();
	    
		String[] arr = new String[word.length()];
		Arrays.fill(arr, "__");
		checkForSpaces(word, arr);
		
		int strikes = 0;
		
		do {
			printStickFigure(strikes);
			printWord(arr);
			System.out.println("Guess a letter");
			
			String letter = console.next().toUpperCase();
			
			int length = letter.length();
			if(letter.matches("[a-zA-Z]") && length <= 1) {
				guessedLetters.add(letter);
				boolean isFound = word.contains(letter);
				if(isFound == false) {
					strikes++;
					System.out.println("Sorry, no " + letter + "'s. Strike count is " + strikes); 
				}
				checkGuess(letter, word, arr);
				printGuessedLetters(guessedLetters);
				if(!contains("__", arr)) {
					printStickFigure(strikes);
					printWord(arr);
					System.out.println("Congrats! You win.");
					break;
		       }
			} else {
				System.err.println("INVALID INPUT");
				printWord(arr);
			}
		} while(strikes < 8);
		if(strikes >= 8) {
			printStickFigure(strikes);
			System.out.println("SORRY you lose.");
			System.out.println("The answer was " + word);
		}
	}
	
	private static void checkForSpaces(String word, String[] arr) {
		for(int index = 0; index < word.length(); index++) {
			if(word.substring(index, index+1).contains(" ")){
				arr[index] = " ";
		    }
		}
	}
	
	private static String getWord() throws FileNotFoundException, IOException {
		String words;
		File textFile = new File("wordbank.txt");
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(textFile));
		ArrayList<String> wordList = new ArrayList<String>();
		
		while ((words = reader.readLine()) != null) {
	        wordList.add(words.toUpperCase());
		}
		
		Random random = new Random();
		String word = wordList.get(random.nextInt(wordList.size()));
		
		return word;
	}
	
	private static void printStickFigure(int strikes) {
		switch(strikes) {
		    case 0:
			    System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("       |");
			    System.out.println("       |");
			    System.out.println("       |");
			    System.out.println("       |");
			    System.out.println("_______|__");
			    break;
		    case 1:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("       |");
			    System.out.println("       |");
			    System.out.println("       |");
			    System.out.println("_______|__");
			    break;
		    case 2:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("   |   |");
			    System.out.println("   |   |");
			    System.out.println("       |");
			    System.out.println("_______|__");
			    break;
		    case 3:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("  \\|   |");
			    System.out.println("   |   |");
			    System.out.println("       |");
			    System.out.println("_______|__");
			    break;
		    case 4:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("  \\|/  |");
			    System.out.println("   |   |");
			    System.out.println("       |");
			    System.out.println("_______|__");
			    break;
		    case 5:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("  \\|/  |");
			    System.out.println("   |   |");
			    System.out.println("  /    |");
			    System.out.println("_______|__");
			    break;
		    case 6:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("  \\|/  |");
			    System.out.println("   |   |");
			    System.out.println("  / \\  |");
			    System.out.println("_______|__");
			    break;
		    case 7:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("  \\|/  |");
			    System.out.println("   |   |");
			    System.out.println(" _/ \\  |");
			    System.out.println("_______|__");
			    break;
		    case 8:
		    	System.out.println("  ______");
			    System.out.println("   |   |");
			    System.out.println("   O   |");
			    System.out.println("  \\|/  |");
			    System.out.println("   |   |");
			    System.out.println(" _/ \\_ |");
			    System.out.println("_______|__");
			    break;
		}
		
	}
	
	private static void printGuessedLetters(ArrayList<String> guessedLetters) {
		System.out.print("Guessed letters: ");
		for(String l : guessedLetters) {
			System.out.print(l + " ");
		}
	    System.out.println("");	
	}
	
	private static boolean contains(String string, String[] arr) {
		for(String l : arr) {
			if (l == string) {
				return true;
			}
		}
		
		return false;
	}
	
	private static void printWord(String[] arr) {
		StringBuilder builder = new StringBuilder();
		for (String value : arr) {
		    builder.append(value);
		}
		String joined = String.join(" ", arr);
		System.out.println(joined);
	}
	
	private static String[] checkGuess(String letter, String word, String[] arr) {
		for(int index = 0; index < word.length(); index++) {
			if(letter.equals(word.substring(index, index+1))){
					arr[index] = letter;
			}
		}
		
		return arr;
	}
}