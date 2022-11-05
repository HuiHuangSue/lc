public class repeatedStrCanConstruct459 {
    /*
    Given a string s, check if it can be constructed by taking a substring of it 
    and appending multiple copies of the substring together.

    Example 1:
    Input: s = "abab"
    Output: true
    Explanation: It is the substring "ab" twice.
    Example 2:
    Input: s = "aba"
    Output: false
    Example 3:
    Input: s = "abcabcabcabc"
    Output: true
    Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
*/
    public boolean repeatedSubstringPattern(String s) {
        // 既然前面有相同的子串，后面有相同的子串，用 s + s，
        // 这样组成的字符串中，后面的子串做前串，前后的子串做后串，就一定还能组成一个s
        // 暴力解法是m * n，一般库函数实现为 O(m + n)
        // String doubleStr = s + s;
        // return doubleStr.substring(1, doubleStr.length()-1).contains(s);

        int len = s.length();
        for (int i = len / 2; i >= 1; i--) { // start from middle, from back to front, get substr windows
            if (len % i == 0) { // if can be divisible, eg: 10 chars, cannot break into 3 substrs
                String curSubStr = s.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < len / i; j++) {
                    sb.append(curSubStr); // append i times until length
                }
                if (sb.toString().equals(s)) return true; // stringEquals
            }
        }
        return false; 
    }

}
