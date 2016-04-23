// lengthOfLongestSubstring.java
// https://leetcode.com/problems/longest-substring-without-repeating-characters/

// Given a string, find the length of the longest substring without repeating characters.
// Examples:
// Given "abcbbcbb", the answer is "abc", which the length is 3.
// Given "bbbbb", the answer is "b", with the length of 1.
// Given "pwwkew", the answer is "wke", with the length of 3. 
// Note that the answer must be a substring, "pwke" is a subsequence 
// and not a substring.

public class Solution {
    private static final int NOT_FOUND = -1;
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) { return n; }      
    	int[] occurrence = new int[256];

        // cannot use 0 because will conflict with index 0
    	Arrays.fill(occurrence, NOT_FOUND);
    	
    	int startIndex = 0;
    	int endIndex = 0;
    	int max = 0;

        Boolean largerResultPossible = (n - startIndex - 1 >= max);
    	while ((endIndex < n) && largerResultPossible) {
    		int nextChar =  (int)s.charAt(endIndex);
	    	while (occurrence[nextChar] == -1) {  // keep searching new occurrences
				occurrence[nextChar] = endIndex;
				if (endIndex == n - 1) {  // end of string reached
					return Math.max(max, endIndex - startIndex + 1);
				}
				max = Math.max(max, endIndex - startIndex + 1);
				endIndex++; 
				nextChar =  (int)s.charAt(endIndex);
	    	}

			int previousOccurence = occurrence[nextChar] + 1;
			while (startIndex < n && startIndex != previousOccurence) {
				//remove the discarded sequence
				occurrence[(int)s.charAt(startIndex)] = NOT_FOUND; 
				startIndex++;
			}
			occurrence[(int)s.charAt(endIndex)] = endIndex;
			endIndex++; 
			max = Math.max(max, endIndex - startIndex);

    	}
    	return max;
    }
}


