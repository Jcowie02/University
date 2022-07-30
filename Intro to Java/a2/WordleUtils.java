package a2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Methods useful for implementing the game Wordle. None of the methods
 * assume that the guess or answer words have exactly 5 letters.
 */
public class WordleUtils {

	/**
	 * Returns a new string formed by replacing one character in the string
	 * {@code s} with the replacement character {@code c}. The position of the
	 * replaced character is given by {@code index}.
	 * 
	 * @param s     a string to replace one character in
	 * @param index the index of the replaced character
	 * @param c     the character to use as the replacement character
	 * @return a new string formed by replacing one character in s with the
	 *         replacement character c
	 * @throws IndexOutOfBoundsException if index is not a valid index for the
	 *                                   string s
	 */
	public static String replace(String s, int index, char c) {
		
		if (index < 0 || index >= s.length()) {
			throw new IndexOutOfBoundsException("invalid index");
		}
		
		String tmp = new String();
		tmp = s.substring(0,index) + c + s.substring(s.length()); //creates a new string where the character at the specified index is replaced by char c
		return tmp;
		
//		if (index < 0 || index >= s.length()) {
//			throw new IndexOutOfBoundsException();
//		}
//		return s.substring(0, index) + c + s.substring(index + 1);
//	
	
	}

	/**
	 * Returns a list indicating which letters in the {@code guess} string match
	 * letters in the {@code answer} string. Letters match if they are equal and
	 * have the same location in both strings.
	 * 
	 * <p>
	 * The returned list contains the value {@code true} at index {@code i} if the
	 * characters at index {@code i} of the {@code guess} and {@code answer} strings
	 * are equal, otherwise the the value at index {@code i} is {@code false}.
	 * 
	 * @param guess  a string to compare to the answer
	 * @param answer the answer string
	 * @return a list of boolean values indicating whether characters in the guess
	 *         and answer strings match
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static ArrayList<Boolean> isGreen(String guess, String answer) {
		
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("wrong length");
		}
		
		ArrayList<Boolean> tmp = new ArrayList<>();
		
		// for loop checks if the guess at index i is equal to the answer at index i
		for(int i = 0; i < guess.length(); i++) {
			if (guess.charAt(i) == answer.charAt(i)) {
				tmp.add(true);
			}
			else tmp.add(false);
		}
		
		return tmp;
	}

	/**
	 * Returns a list indicating which letters in the {@code guess} string also
	 * appear in the {@code answer} string taking into account how many times a
	 * letter appears; see the assignment document for some examples.
	 * 
	 * <p>
	 * The returned list contains the value {@code true} at index {@code i} if the
	 * character at index {@code i} of the {@code guess} appears in the
	 * {@code answer} string (again, accounting for the number of times the letter
	 * appears), otherwise the the value at index {@code i} is {@code false}.
	 * 
	 * @param guess  a string to compare to the answer
	 * @param answer the answer string
	 * @return a list of boolean values indicating whether characters in the guess
	 *         string appear in the answer string accounting for the number of times
	 *         that the character appears
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static ArrayList<Boolean> isYellow(String guess, String answer) {
		
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("wrong length");
		}
		
		ArrayList<Boolean> greens = new ArrayList<>();
		greens = WordleUtils.isGreen(guess, answer);
		
		HashMap<Character, Integer> duplicates = new HashMap<>();
		char[] strArr = answer.toCharArray(); // turn the answer into an array of characters
		for (char c : strArr) {
			if (duplicates.containsKey(c)) {
				duplicates.put(c,  duplicates.get(c) + 1); // if a letter shows up twice in an answer, one is added to what duplicates maps to
			}
			else {
				duplicates.put(c, 1); // otherwise, each letter will only have a value of 1
			}
		}
		
		ArrayList<Boolean> yellows = new ArrayList<>();
		
		for (int i = 0; i < guess.length(); i++) {
			
			if  (greens.get(i) == true) {
				duplicates.put(guess.charAt(i), duplicates.get(guess.charAt(i)) - 1); // reduce the count for a correctly guessed character in a string
				// to ensure that the correct number of boxes are yellow
			}
		}
		
		for (int i = 0; i < guess.length(); i++) {
			
			if ((answer.indexOf(guess.charAt(i)) != -1) && (greens.get(i) != true) && (duplicates.get(guess.charAt(i)) >= 1)) {  //checks if the answer contains 
				// the guess at index i, ensures that the index is not already coloured green, and that yellow slots have not been exhausted, respectively
				yellows.add(true);
				duplicates.put(guess.charAt(i), duplicates.get(guess.charAt(i)) - 1);
			}
			else yellows.add(false);
		}
		return yellows;
	}
	
	/**
	 * Returns a list of the colorings for the given guess and answer words. See the
	 * assignment document for details on how letters of the guess word are colored
	 * in the game Wordle.
	 * 
	 * @param guess  a guess word
	 * @param answer the answer word
	 * @return a list of the colorings for the given guess and answer words
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static ArrayList<WordleColor> getColors(String guess, String answer) {
		
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("wrong length");
		}
		
		ArrayList<Boolean> greens = new ArrayList<>();
		greens = WordleUtils.isGreen(guess, answer);
		
		ArrayList<Boolean> yellows = new ArrayList<>();
		yellows = WordleUtils.isYellow(guess, answer);
		
		ArrayList<WordleColor> colours = new ArrayList<>();
		
		// for loop adds the appropriate colours to the colours list and then returns it
		for (int i = 0; i < answer.length(); i++) {
			if (greens.get(i) == true) colours.add(WordleColor.GREEN);
			else if (yellows.get(i) == true) colours.add(WordleColor.YELLOW);
			else colours.add(WordleColor.GRAY);
		}
		return colours;	
	}

	/**
	 * Update the sets of different letter categories in a game of Wordle given the
	 * guess and answer words. This method is meant to be called after the player
	 * makes a new guess. The first time that the method is called for a new game of
	 * Wordle, the sets {@code included} and {@code excluded} should be empty, and
	 * the set {@code possible} should contain the letters {@code 'A'} through
	 * {@code 'Z'}.
	 * 
	 * <p>
	 * The set {@code included} is the set of letters that the player has determined
	 * must be in the answer word.
	 * 
	 * <p>
	 * The set {@code excluded} is the set of letters that the player has determined
	 * must not be in the answer word.
	 * 
	 * <p>
	 * The set {@possible} is the set of letters that have not been used yet by the
	 * player. If {@code guess.equals(answer)} is {@code true} then the set
	 * {@possible} will be empty.
	 * 
	 * @param guess    the most recent guess word
	 * @param answer   the answer word
	 * @param included the set of letters that the player has determined are in the
	 *                 answer word
	 * @param excluded the set of letters that the player has determined are not in
	 *                 the answer word
	 * @param possible the set of letters not in included or excluded
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static void updateLetters(String guess, String answer, 
			TreeSet<Character> included,
			TreeSet<Character> excluded, 
			TreeSet<Character> possible) {
		
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("wrong length");
		}
		
		if (answer == guess) possible.clear(); // clears the list of possible letters if the guess is correct
		
		for (int i = 0; i < answer.length(); i++) {
			if (answer.indexOf(guess.charAt(i)) != -1) {
				included.add(guess.charAt(i)); 
			}
			else excluded.add(guess.charAt(i));
			
			possible.remove(guess.charAt(i));
		}
	}

	public static void main(String[] args) {
		// simulates one game of Wordle
		
		// set up the sets of letters
		TreeSet<Character> in = new TreeSet<>();
		TreeSet<Character> notIn = new TreeSet<>();
		TreeSet<Character> mightBeIn = new TreeSet<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			mightBeIn.add(c);
		}
		
//		// the answer
//		String ans = "HELLO";
//		// the guesses
//		final String[] GUESSES = { "LLLLL", "LLLXX", "LLXLX", "LLXXL", "LXLLX",  "LXLXL", "XXLLL"};
		
		String ans = "CORAL";
		// the guesses
		final String[] GUESSES = { "VEGAN", "HULAS", "BLOAT", "LOYAL", "FOCAL",  "CORAL"};

		int guessNum = 0;
		for (String g : GUESSES) {
			guessNum++;
			System.out.println("answer  : " + ans);
			System.out.println("guess " + guessNum + " : " + g);
			System.out.println("isGreen : " + WordleUtils.isGreen(g, ans));
			System.out.println("isYellow: " + WordleUtils.isYellow(g, ans));
			System.out.println("colors  : " + WordleUtils.getColors(g, ans));
			WordleUtils.updateLetters(g, ans, in, notIn, mightBeIn);
			System.out.println("in      : " + in);
			System.out.println("not in  : " + notIn);
			System.out.println("maybe?  : " + mightBeIn);
			System.out.println();
		}

	}

}
