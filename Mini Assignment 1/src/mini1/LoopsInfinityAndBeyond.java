package mini1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Utility class with a bunch of static methods for loop practice.
 * 
 * @author
 */
public class LoopsInfinityAndBeyond {
// disable instantiation
//private LoopsInfinityAndBeyond() { }
	/**
	 * Returns a new string in which every character in the given string that is not
	 * already repeated consecutively is doubled.
	 * <p>
	 * For example:
	 * 
	 * <pre>
	 * {@code
	 * "attribute1" -> "aattrriibbuuttee11"
	 * "AAA Bonds" -> "AAA  BBoonnddss"
	 * }
	 * </pre>
	 * 
	 * @param text given starting string
	 * @return string with characters doubled
	 */
	public static String doubleChars(String text) {
		String accum = "";
		if (text.length() == 1) {
			accum += text;
			accum += text;
			return accum;
		}
		if (text.length() <= 0) {
			return "";
		}
		for (int i = 0; i < text.length() - 1; i++) {
			char current = text.charAt(i);
			char nextChar = text.charAt(i + 1);
			if (current == nextChar) {
				accum += current;
				accum += current;
				i++;
			} else {
				if (i != 0 && text.charAt(i - 1) == current) {
					accum += current;
				} else {
					accum += current;
					accum += current;
				}
			}

		}
		char lastIndex = text.charAt(text.length() - 1);
		accum += lastIndex;
		if (lastIndex != text.charAt(text.length() - 2)) {
			accum += lastIndex;
		}

		return accum;
	}

	/**
	 * Returns a year with the highest value, given a string containing pairs of
	 * years and values (doubles). If there are no pairs, the method returns -1. In
	 * the case of a tie, the first year with the highest value is returned. Assumes
	 * the given string follows the correct format.
	 * <p>
	 * For example, given the following string, the year 1995 is returned.
	 * 
	 * <pre>
	 * 1990 75.6 1991 110.6 1995 143.6 1997 62.3
	 * </pre>
	 * 
	 * @param data given string containing year-value pairs
	 * @return first year associated with the highest value, or -1 if no pair given
	 */
	public static int maxYear(String data) {
		int yearValue = 0;
		double doubleValue = 0;
		int currentVal = 0;
		String oddList[] = data.split(" ");
//int lengthOfSpace=data.replaceAll(" ","").length()-1;
		int oddListLength = oddList.length;
		if (oddListLength % 2 == 0) {

			for (int i = 1; i < oddListLength; i += 2) {
				if (Double.parseDouble(oddList[i]) > doubleValue) {
					doubleValue = Double.parseDouble(oddList[i]);
					yearValue = Integer.parseInt(oddList[i - 1]);
				}

			}

			{

			}
		}

		else {
			return -1;
		}
		return yearValue;
	}

	/**
	 * Returns the number of iterations required until <code>n</code> is equal to 1,
	 * where each iteration updates <code>n</code> in the following way:
	 * 
	 * <pre>
	 *     if n is even
	 *         divide it in half
	 *     else
	 *         multiply n by three and add 1
	 * </pre>
	 * 
	 * For example, given <code>n == 6</code>, the successive values of
	 * <code>n</code> would be 3, 10, 5, 16, 8, 4, 2, 1, so the method returns 8. If
	 * <code>n</code> is less than 1, the method returns -1.
	 * <p>
	 * <em>(Remark:</em> How do we know this won't be an infinite loop for some
	 * values of <code>n</code>? In general, we don't: in fact this is a well- known
	 * open problem in mathematics. However, it has been checked for numbers up to
	 * 10 billion, which covers the range of possible values of the Java
	 * <code>int</code> type.)
	 * 
	 * @param n given starting number
	 * @return number of iterations required to reach <code>n == 1</code>, or -1 if
	 *         <code>n</code> is not positive
	 */
	public static int collatzCounter(int n) {
		int numDivisible = 0;
		while (n > 1) {
			if (n % 2 == 0) {
				n = n / 2;
				numDivisible += 1;
			} else {
				n = n * 3 + 1;
				numDivisible += 1;
			}
		}
		if (n < 1) {
			return -1;
		}

		return numDivisible;
	}

	/**
	 * Returns a new string in which every word in the given string is doubled. A
	 * word is defined as a contiguous group of non-space (i.e., ' ') characters
	 * that starts with an alphabetic letter and are surrounded by spaces and/or the
	 * start or end of the given string. Assumes the given string does not contain
	 * more than one consecutive white-space.
	 * <p>
	 * For example:
	 * 
	 * <pre>
	 * {@code
	 * "the time time" -> "the the time time time time"
	 * "The answer is 42." -> "The The answer answer is is 42."
	 * "A. runtime = 10ms" -> "A. A. runtime runtime = 10ms"
	 * }
	 * </pre>
	 * 
	 * @param text given starting string
	 * @return new string in which words are doubled
	 */
	public static String doubleWords(String text) {
		String accum = "";
		String list[] = text.split(" ");
		boolean digitCheck = false;
		boolean equalCheck = false;
		int wordListLength = list.length;
		if (text.length() <= 0) {
			return text;
		}
		for (int i = 0; i < wordListLength; i++) {
			String word = list[i];
			if (Character.isAlphabetic(word.charAt(0))) {
				accum += word + " ";
				accum += word + " ";

			} else {
				accum = accum.trim();
				accum += " " + word;

			}

		}

		accum = accum.trim();
		return accum;
	}

	/**
	 * Returns true if string t can be obtained from string s by removing exactly
	 * one vowel character. The vowels are the letters 'a', 'e', 'i', 'o' and 'u'.
	 * Vowels can be matched in either upper or lower case, for example, 'A' is
	 * treated the same as 'a'. If s contains no vowels the method returns false.
	 * <p>
	 * For example:
	 * 
	 * <pre>
	 * {@code
	 * "banana" and "banna" returns true
	 * "Apple" and "ppl" returns false
	 * "Apple" and "pple" returns true
	 * }
	 * </pre>
	 * 
	 * @param s longer string
	 * @param t shorter string
	 * @return true if removing one vowel character from s produces the string t
	 */
	public static boolean oneVowelRemoved(String s, String t) {
		String newString = "";
		String sL = s.toLowerCase();
		String tL = t.toLowerCase();
		int stringLength = s.length();

		if (sL.contains("a") || sL.contains("e") || sL.contains("i") || sL.contains("o") || sL.contains("u")) {
			for (int i = 0; i < stringLength; i++) {
				// String letter=list[i];
				char currentLetter = sL.charAt(i);
				if (currentLetter == 'a' || currentLetter == 'e' || currentLetter == 'i' || currentLetter == 'o'
						|| currentLetter == 'u') {
					{
						newString = sL.substring(0, i) + sL.substring(i + 1, stringLength);
						if (newString.equals(tL)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns a new string in which a UFO pattern in the given string is shifted
	 * one character to the right. The UFO pattern starts with a {@code '<'}, has
	 * one or more {@code '='} and ends with a {@code '>'}. The pattern may wrap
	 * around from the end to the beginning of the string, for example
	 * {@code ">  <="}. Any other characters from the given string remain in place.
	 * If the UFO moves over top of another character, that character is removed. If
	 * there are multiple UFO patterns, only the one that starts farthest to the
	 * left is moved.
	 * <p>
	 * For example:
	 * 
	 * <pre>
	 * {@code
	 * " <=>  *   . <=> " ->
	 * "  <=> *   . <=> "
	 * 
	 * "   <=>*   .     " ->
	 * "    <=>   .     "
	 * 
	 * ">.   x     .  <=" ->
	 * "=>   x     .   <"
	 * 
	 * " <= <===>   .   " ->
	 * " <=  <===>  .   "
	 * }
	 * </pre>
	 * 
	 * @param space given string
	 * @return a new string with a UFO pattern moved one to the right
	 */
	public static String ufo(String space) {
		
		String answer = "";
		ArrayList<Character> spaceChar = new ArrayList<Character>();
		int spaceLength=space.length();
		for (int i = 0; i < space.length(); i++) {
			spaceChar.add(i, space.charAt(i));
		}

		for (int i = 0; i < spaceChar.size(); i++) {
			if(spaceChar.get(i) == '<') {
				int startIndex = i;
				if(spaceChar.get(i+1) == '=') {
					i++;
					while(spaceChar.get(i) =='=') {
						
						i++;

					}
					if(spaceChar.get(i) == '>') {
						int endIndex = i+1;
						for(int z = 0 ; z < spaceChar.size(); z++) {
							
							if(z == startIndex-1) {
								answer += spaceChar.get(z);
								answer += " ";
							}
							else if(z == endIndex + 1) {
								
							}
							else{
								answer += spaceChar.get(z);
								}
						}
						i = spaceChar.size();
						
					}
				}
			}
		}
		
		return answer;
	}

	/**
	 * Prints a pattern of <code>2*n</code> rows containing slashes, dashes and
	 * backslashes as illustrated below.
	 * <p>
	 * When {@code n <= 0 }, prints nothing.
	 * <p>
	 * <code>n = 1</code>
	 * 
	 * <pre>
	 * \/
	 * /\
	 * </pre>
	 * <p>
	 * <code>n = 2</code>
	 * 
	 * <pre>
	 * \--/
	 * -\/
	 * -/\
	 * /--\
	 * </pre>
	 * <p>
	 * <code>n = 3</code>
	 * 
	 * <pre>
	 * 
	 * \----/
	 * -\--/
	 * --\/
	 * --/\
	 * -/--\
	 * /----\
	 * </pre>
	 * 
	 * <code>n = 4</code>
	 * 
	 * <pre>
	 * \------/
	 * -\----/
	 * --\--/
	 * ---\/
	 * ---/\
	 * --/--\
	 * -/----\
	 * /------\
	 * </pre>
	 * 
	 * @param n number of rows in the output
	 */
	public static void printX(int n) {

		// upper upside down triangle
		for (int row = 0; row < n; row++) {// iterates through rows
			int d = row; // count for num of dashes before \
			for (int i = d; i > 0; i--) { // prints dashes before \
				System.out.print("-");
			}
			System.out.print("\\");
			for (int i = ((n - row) * 2) - 2; i > 0; i--) { // prints inner dashes
				System.out.print("-");
			}
			System.out.print("/");
			System.out.println();
		}

		// lower right side up triangle
		for (int row = n; row > 0; row--) { // iterates through rows
			int d = row - 1; // count for num of dashes before /
			for (int i = d; i > 0; i--) { // prints dashes before /
				System.out.print("-");
			}
			System.out.print("/");
			for (int i = 0; i < ((n - row) * 2); i++) { // prints inner dashes
				System.out.print("-");
			}

			System.out.print("\\");
			System.out.println();
		}
	}

}
