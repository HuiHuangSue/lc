public String findLongestWord524(String s, List<String> dict) {
    String res = "";
    for(String word : dict) {
        // skip if current word is shorter than current result, or is latter lexi order than ans
        if (word.length() < res.length() || 
            (word.length() == res.length() && word.compareTo(res) > 0)) {
            continue;
        }
        int si = 0, wi = 0;
        while(si < s.length() && wi < word.length() 
              && s.length() - si >= word.length() - wi) {
            // continue only if remaining s chars longer than remaining word char and within boundary,
            // since the word need to be contained in the s 
            if (s.charAt(si) == word.charAt(wi)) {
                wi++; // if char matches, word index moves forward
            }
            si++; // s index always moves forward as only need to match some
        }
        if (wi == word.length()) { // wi has been incremented when finish, so not word.length-1
            res = word;
        }
    }
    return res;
}