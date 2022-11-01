public class validAnagram242 {
    /* 
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.

        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
        typically using all the original letters exactly once.

        s = "anagram", t = "nagaram" ---> true
        s = "rat", t = "car" ---> false
    */
    public boolean isAnagram242(String s, String t) {//s = "anagram", t = "nagaram" true
        if (s.length() != t.length()) { return false; }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) { table[s.charAt(i) - 'a']++; }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {// check early
                return false;
            }
        }
        return true;


    public boolean isAnagram(String s, String t) {
        // sort then compare
        if (s.trim().length() != t.trim().length()) return false;
        char[] sarr = s.toCharArray(), tarr = t.toCharArray(); 
        // must change to char[] first; Arrays.sort(s.toCharArray()) will not apply to s it self; 
        Arrays.sort(sarr);
        Arrays.sort(tarr);
        for(int i = 0; i < s.length(); i++) {
            if (sarr[i] != tarr[i]) return false;
        } 
        return true;
    }
}
