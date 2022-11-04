public class impleStrSte28 {
    /*
     * Given two strings needle and haystack, 
     * return the index of the first occurrence of needle in haystack, 
     * or -1 if needle is not part of haystack.
     */

    /*
    Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.
    
    Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.*/

    public int strStr(String mom, String kid) { // O(m*n). KMP
        int momLen = mom.length(), kidLen = kid.length();
        for(int i = 0; i <= momLen - kidLen; i++) {
            int momStart = i, kidStart = 0;
            while (kidStart < kidLen && mom.charAt(momStart) == kid.charAt(kidStart)) {
                momStart++;
                kidStart++;
            }
            if (kidStart == kidLen) return i;
        }
        return -1;
    }
    // https://programmercarl.com/ 实现字符串--> strStr
}
