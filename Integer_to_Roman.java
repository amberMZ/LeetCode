// Integer_to_Roman.java
// https://leetcode.com/problems/integer-to-roman/
// Given an integer, convert it to a roman numeral.
// Input is guaranteed to be within the range from 1 to 3999.

public class Solution {
	private final char[] ROMAN_DIGIT_TABLE = 
		{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		//0    1    2    3    4    5    6     index
		//1    5    10   50   100  500  1000  value

    public String intToRoman(int num) {
    	return intToRomanHelper(num, "", 0);
    }

    private String intToRomanHelper(int num, String result, int place) {
    	int remainder = num/10;

    	if (remainder == 0) {
    		int currentDigit = num; // currentDigit is the most significant digit
    		String front = conversion(currentDigit, place);
    		return front + result;
    	} 

    	int currentDigit = num%10; 
    	String front = conversion(currentDigit, place);
    	return intToRomanHelper(remainder, front + result, ++place);

    }

    // Convert a single int digit to corresponding roman numerals
    private String conversion(int digit, int place) {
    	String result = "";
    	switch (place) {
    		case 0: // 1 to 9
    			result = singleDigit(digit, ROMAN_DIGIT_TABLE[0], 
    				ROMAN_DIGIT_TABLE[1], ROMAN_DIGIT_TABLE[2]); // I, V, X
    			break;
    		case 1: // 10 to 90
    			result = singleDigit(digit, ROMAN_DIGIT_TABLE[2], 
    				ROMAN_DIGIT_TABLE[3], ROMAN_DIGIT_TABLE[4]); // X, L, C
    			break;
    		case 2: // 100 to 900
    			result = singleDigit(digit, ROMAN_DIGIT_TABLE[4], 
    				ROMAN_DIGIT_TABLE[5], ROMAN_DIGIT_TABLE[6]); // C, D, M
    			break;
    		case 3: // 1000 to 3000 
				for (int i = 0; i < digit; i++){
					result += ROMAN_DIGIT_TABLE[6];
				}
    			break;
    		default: break;
    	}
    	return result;
    }

    // Convert a single int digit to corresponding roman numerals 
    // chacraters based on their place.
    private String singleDigit(int digit, char one, char five, char ten){
    	String romanDigit = "";
    	// 0 is implicitly handled
		if (digit <= 3) {  // 1, 2, 3
			for (int i = 0; i < digit; i++){
				romanDigit += one;
			}
		} else if (digit == 4) {  // 4
			romanDigit = romanDigit + one + five;
		} else if (digit <= 8) {  // 5, 6, 7, 8
			romanDigit += five;
			for (int i = 0; i < digit - 5; i++){
				romanDigit += one;
			}
		} else if (digit == 9){  // 9
			romanDigit += romanDigit + one + ten;
		} 
		return romanDigit;
    }

}