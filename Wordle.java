/*Sample Run:
Please make your first guess:
IGLOO
Letter I exists but is in the wrong place.
Letter O exists but is in the wrong place.
You have found 2 letters.
_ _ _ _ _
Please make another guess:
VOICE
Letter I exists but is in the wrong place.
You have found 2 letters.
_ O _ _ _
Please make another guess: 
TOXIC
Congrats! You have found today’s Wordle!*/

import java.util.Scanner;


public class Wordle {

	// Declare Wordle with keyword static so it can be used in main and other method
	static String wordle = "TOXIC";

	public static void main(String[] args) {
		
		
		// Ask user to input a 5-letter word	
		
		Scanner input = new Scanner (System.in);
		boolean guessedCorrectly = false; // to track whether user guesses the word correcly
		System.out.println("Please enter a 5-letter word: ");
		
	
		
		
		// Use if statement to validate input and for loop to limit the number of tries (6 times)
		for(int i = 1; i <= 6; i++) {
			String textFromUser = input.nextLine().toUpperCase(); // Transform input to upper case to prevent case sensitive scenario 
			
			if(textFromUser.length() != 5) { // Validate input to make sure it's a 5-letter word			
				System.out.println("Invalid input. Please make sure your word is 5 letters");
			continue;
			
			} else {
				
				if (textFromUser.equals(wordle)) {
                System.out.println("Congrats! You have found today’s Wordle!");
                guessedCorrectly = true;
                break;
                
                } else { // Call myCharCheck() to check each word and provide feedback 
                	myCharCheck(textFromUser);
                	if(i < 6) {
                		System.out.println("Please make another guess: ");
                	}
		}
				
	}
			
		}
		
		
		// If user cannot guess the Wordle after 6 tries, the game ends
		if(!guessedCorrectly) {
		System.out.println("Sorry! You lose the game. Today's Wordle is: TOXIC.");
		}
			
		input.close();
		
		
			
		

	}
	
	// Create myCharCheck() method to check letters in user's input
	public static void myCharCheck(String text) {
		int countChar = 0; //to count how many letters in the word that user input match the wordle
		StringBuilder result = new StringBuilder(); // to concatenate the letter after every check
		boolean[] matched = new boolean[wordle.length()]; // to track exact matches
		
		// 1st check: Check for exact matches 
		 for (int x = 0; x < wordle.length(); x++) {
		        if (text.charAt(x) == wordle.charAt(x)) { // if x-th letter from the input matches with x-th letter in the Wordle
		            countChar++;
		            result.append(text.charAt(x));
		            matched[x] = true; // mark as matched so it will not be duplicated
		        } else {
		            result.append("_");
		        }
		        result.append(" "); // to make "_" visible on the console
		        
		    }         
		       
		 
		 
		            
		
		// 2nd check: Check for misplaced letters without counting them
			for(int y = 0; y < wordle.length(); y++) {
				char textChar = text.charAt(y);
				
				if (wordle.indexOf(textChar) >= 0 && !matched[y] && result.charAt(y*2) == '_') {
					
					// Check if the letter is not already marked as matched in the exact place
		            if (!matched[wordle.indexOf(textChar)]) {
		                System.out.println("Letter " + textChar + " exists but is in the wrong place.");
		                countChar++;
		                matched[wordle.indexOf(textChar)] = true; // Prevents duplicate "wrong place" messages
		            }
		            
				}
				
			}
			
			
				System.out.println("You have found " + countChar + " letters.");
				System.out.println(result.toString().trim()); // to remove any unnecessary white space
				
				
	}		
			
		            
}
