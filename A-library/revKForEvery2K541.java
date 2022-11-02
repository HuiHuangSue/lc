public class revKForEvery2K541 {
    /*
     * Example 1:
        Input: s = "abcdefg", k = 2
        Output: "bacdfeg"
        Example 2:
        Input: s = "abcd", k = 2
        Output: "bacd"
     */
    class Solution {
        public String reverseStr(String s, int k) {
            char[] sarr = s.toCharArray();
            int start = 0;
            // 每隔2k个反转前k个
            while (start + k - 1 < s.length()){
                rev(sarr, start, start + k -1);
                start += k * 2;
            }
            // abcdefg, k=8  尾数不够k个时候全部反转
            if (start + k - 1 >= s.length()) { // length inclusive
                rev(sarr, start, s.length() -1);
            }
            return String.valueOf(sarr);
        }
        public void rev(char[] s, int i, int j) {
            while(i <= j) {
                char tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
                i++;
                j--;
            }
        }
    }
}
