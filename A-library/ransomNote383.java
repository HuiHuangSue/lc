public class ransomNote383 {
    /*
    Given two strings ransomNote and magazine, 
    return true if ransomNote can be constructed by using the letters from magazine 
    and false otherwise.
    Each letter in magazine can only be used once in ransomNote.

    Example 1:
    Input: ransomNote = "a", magazine = "b"
    Output: false
    Example 2:

    Input: ransomNote = "aa", magazine = "ab"
    Output: false
    Example 3:

    Input: ransomNote = "aa", magazine = "aab"
    Output: true
*/
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] map = new int[26];
        // magazine 的++, ransomNote的--，这样<0补不全的就不行
        // 否则，ransom的++, magazine的总有--的
        for(char c : magazine.toCharArray()){
            map[c - 'a']++;
        }
        for(char c : ransomNote.toCharArray()){
            map[c - 'a']--;
        }
        for (int i : map) {
            if (i < 0) return false;
        }
        return true;

        
        Map<Character, Integer> ransomMap = new HashMap<>();
        Map<Character, Integer> magMap = new HashMap<>();
        for(int i = 0; i < ransomNote.length(); i++) {
            ransomMap.put(ransomNote.charAt(i), ransomMap.getOrDefault(ransomNote.charAt(i), 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            magMap.put(magazine.charAt(i), magMap.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        for (Character ransomChar : ransomMap.keySet()) {
            if (ransomMap.get(ransomChar) > magMap.getOrDefault(ransomChar, 0)) { 
                // get Or default because magzine may not contain all chars needed, ow exception
                return false;
            }
        }
        return true;
    }

}
