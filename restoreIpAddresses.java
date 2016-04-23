// restoreIpAddresses.java
// https://leetcode.com/problems/restore-ip-addresses/

// Given a string containing only digits, restore it by returning 
// all possible valid IP address combinations.
// For example:
// Given "25525511135",

// return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

// The format of an IP address is a 32-bit numeric address 
// written as four numbers separated by periods. 
// Each number can be zero to 255.


// Question 
// s.charAt(0) == '0'	// works 
// s.substring(0, 1) == '0' // doesn't work
// What is the difference?

public class Solution {
    public List<String> restoreIpAddresses(String s) {
    	ArrayList<String> resultList = new ArrayList<String>();
    	restoreIpAddressesHelper(s, 1, "", resultList);
    	return resultList;
    }

    private void restoreIpAddressesHelper(String s,
    	int byteCount, String result, ArrayList<String> resultList) {

    	if (byteCount == 4 && !s.isEmpty()) {
    		Boolean zeroStart = (s.charAt(0) == '0') && (s.length() > 1);
    		// have to include s.length() < 4 to avoid overflow in parsing
    		if ( !zeroStart && s.length() < 4 && Integer.parseInt(s) <= 255) {
    			resultList.add(result + "." + s);
    		}
    	} else {
			if (!s.isEmpty()) {
				String dot = ".";
				if (byteCount == 1) { dot = ""; }  // First byte doesn't need "."
				byteCount++;

				// size of new byte can be 1, 2, 3 since anything with  
				// larger number of digits is clearly greater than 255.
		    	for (int i = 1; i <= 3; i++) {
		    		if (s.length() < i) {
		    			break;
		    		}	
					// If new byte start with "0", then new byte must be "0" with size 1
					if (s.charAt(0) == '0') {
						restoreIpAddressesHelper(s.substring(1), byteCount, result + dot + "0", resultList);
						break;
					} 

					if (i == 3 && Integer.parseInt(s.substring(0, i)) > 255) { break; }
		    		String newS = s.substring(i);
		    		String newResult = result + dot + s.substring(0, i);
		    		restoreIpAddressesHelper(newS, byteCount, newResult, resultList);
			    	
		    	}					
				
			}
    	}
    }
}