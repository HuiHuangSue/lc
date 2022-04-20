public class slidingWindow {
    // https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/49708/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
    public List<Integer> slidingWindowTemplate(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result; // input validation
        
        //Hashmap to save the Characters of the target substring.(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){ map.put(c, map.getOrDefault(c, 0) + 1);}
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
        
        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int begin = 0, end = 0;
        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE; 
        
        //loop at the begining of the source string
        while(end < s.length()){
            char c = s.charAt(end);//get rightmost character
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);// plus or minus one,离目标进一步
                if(map.get(c) == 0) counter--;//modify the counter(different condition).
            }
            end++;// 右移end pointer
            
            //increase begin pointer to make it invalid/valid again
            while(counter == 0 /* counter condition. at most k, counter > k */){
                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if(map.get(tempc) > 0) counter++;//modify the counter.
                }
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                begin++;
            }
        }
        return result;
    }

// 76 https://leetcode.com/problems/minimum-window-substring/
    public String minWindowSubstring76(String s, String t) {
        if (s.length() < t.length()) return ""; // try "AAADBOBECODEBABNC"
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int begin = 0, end = 0, head = 0, len = Integer.MAX_VALUE;
        int count = map.size(); // not t's length
        while(end < s.length()) {
            // expanding end pointer when not meet map
            char endc = s.charAt(end);
            if(map.containsKey(endc)) {
                map.put(endc, map.get(endc) - 1);//离目标更近,可为负数AAA
                if (map.get(endc) == 0) count--;//之后可继续为负
            }
            end++;//处理结束，下一个
            
            while(count == 0) {//此时substring里够map的char
                // 开始shrink最左边的char，以得到最短满足条件的
                char leftc = s.charAt(begin);
                if (map.containsKey(leftc)) {//最左边的时t里的一员
                    map.put(leftc, map.get(leftc) + 1);//要去掉了，所以map里需要匹配的char+1;
                    if(map.get(leftc) > 0) {//如果之前AAA有充足的在substring里则够,否则要匹配的+1
                        count++;//之后跳出while,继续右边expand找新的有没有满足确实的char
                    }
                }
                if(end - begin < len) {
                    len = end - begin;//end已经右移过，不需要e-b+1
                    head = begin;//head记录当时最短点的左边，之后可以head+minLen
                }
                begin++;//shrink的处理结束，begin左移
            }
        }
        return (len == Integer.MAX_VALUE) ? "" : s.substring(head, head+len);
    }

    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring3(String s) { // input validation
        // abbbcabade
        int res = 0, begin = 0, end = 0;
        int[] map = new int[256]; //不需要记录位置，int[]也可记录count
        while(end < s.length()) {
            char c = s.charAt(end);
            map[c]++;
            while (map[c] > 1) {// while而不是if,因为只有减到重复的char，才能重新开始 abcaade left得从ade开始，而不是ab的b  
                map[s.charAt(begin)]--;//因为一直在删左边的char，所以要update 
                begin++;
            } 
            res = Math.max(res, end - begin + 1);
            end++;
        }
        return res;
    }

    // https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
    public int lengthOfLongestSubstringTwoDistinct159(String s) {
        int len = Integer.MIN_VALUE;
        int begin = 0, end = 0, count = 0;//需要用count，而不是map.size(),否则需要delete key
        int[] map = new int[256];//需要map知道什么时候减干净,以及确定size
        while (end < s.length()) {
            char c = s.charAt(end);
            map[c]++;
            if(map[c] == 1) count++;//新鲜+1，多了个新的char
            while (count > 2) {
                map[s.charAt(begin)]--;//update左边begin对应的
                if (map[s.charAt(begin)] == 0) { //最左边的如果减到0，就少了个char
                    count--;
                }
                begin++; 
            }
            len = Math.max(len, end - begin + 1);
            end++;
        }
        return len;
    }

    // https://leetcode.com/problems/find-all-anagrams-in-a-string/

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public List<Integer> findSubstring(String S, String[] L) {
    List<Integer> res = new LinkedList<>();
    if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
    int N = S.length();
    int M = L.length; // *** length
    int wl = L[0].length();
    Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
    for (String s : L) {
        if (map.containsKey(s))   map.put(s, map.get(s) + 1);
        else                      map.put(s, 1);
    }
    String str = null, tmp = null;
    for (int i = 0; i < wl; i++) {
        int count = 0;  // remark: reset count 
        int start = i;
        for (int r = i; r + wl <= N; r += wl) {
            str = S.substring(r, r + wl);
            if (map.containsKey(str)) {
                if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
                else                           curMap.put(str, 1);
                
                if (curMap.get(str) <= map.get(str))    count++;
                while (curMap.get(str) > map.get(str)) {
                    tmp = S.substring(start, start + wl);
                    curMap.put(tmp, curMap.get(tmp) - 1);
                    start += wl;
                    
                    //the same as https://leetcode.com/problems/longest-substring-without-repeating-characters/
                    if (curMap.get(tmp) < map.get(tmp)) count--;
                    
                }
                if (count == M) {
                    res.add(start);
                    tmp = S.substring(start, start + wl);
                    curMap.put(tmp, curMap.get(tmp) - 1);
                    start += wl;
                    count--;
                }
            }else {
                curMap.clear();
                count = 0;
                start = r + wl;//not contain, so move the start
            }
        }
        curMap.clear();
    }
    return res;
}
}

// s = "ababcbacadefegdehijhklij" --> [9,7,8]
public List<Integer> partitionLabels763(String s) {
    List<Integer> res = new ArrayList<>();
    int[] map = new int[256];
    for(int i = 0; i < s.length(); i++) {
        map[s.charAt(i) - 'a'] = i;//assign to furthest index
    }
    int start = 0, last = 0;
    for(int i = 0; i < s.length(); i++) { // expand range until meet
        last = Math.max(last, map[s.charAt(i) - 'a']);
        if (last == i) {
            res.add(last - start + 1);
            start = last + 1;
        }
    }
    return res;
}
public int containerWMostWater11(int[] height) {
    int i = 0, j = height.length - 1, area = 0;
    while  (i < j) {
        area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
        if (height[i] < height[j]) { // when equal, both diredctions still < than previous area
            i++;
        } else {
            j--;
        }
    }
    return area;
}
public int trapRainWater42(int[] height) {
    int i = 0, j = height.length - 1, leftMax = height[i], rightMax = height[j], water = 0;
    while (i < j) {
        if (leftMax <= rightMax) {
            i++;
            if (height[i] < leftMax) {
                water += (leftMax - height[i]);
            } else {
                leftMax = height[i];
            }
        } else {
            j--;
            if (height[j] < rightMax) {
                water += (rightMax -  height[j]);
            } else {
                rightMax = height[j];
            }
        }
    }
    return water;
}

// s = "cbaebabacd", p = "abc" --> [0,6]
public List<Integer> findAllAnagramsInStr438(String s, String p) {
    HashMap<Character, Integer> map = new HashMap<>();
    List<Integer> res = new ArrayList<>();
    for(char c : p.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }
    int begin = 0, end = 0, count = map.size();
    while(end < s.length()) {
        char c = s.charAt(end);
        map.put(c, map.getOrDefault(c, 0) - 1);
        if (map.get(c) == 0) { count--; }
        end++;// expanding处理完毕，记得end右移
        
        while(count == 0){//只用count作为标准判断
            char leftc = s.charAt(begin);
            if(map.containsKey(leftc)) {
                map.put(leftc, map.get(leftc) + 1);
                if (map.get(leftc) > 0) {
                    count++;
                }
            }
            if (end - begin == p.length()) {//防止abzza，条件判断
                res.add(begin);
            }
            begin++;
        }
    }
    return res;
}
public boolean isAnagram242(String s, String t) {//s = "anagram", t = "nagaram" true
    // M11: sort then compare
    if (s.length() != t.length()) { return false; }
    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) { table[s.charAt(i) - 'a']++; }
    for (int i = 0; i < t.length(); i++) {
        table[t.charAt(i) - 'a']--;
        if (table[t.charAt(i) - 'a'] < 0) {//check early
            return false;
        }
    }
    return true;
}
//["eat","tea","tan","ate","nat","bat"]--> [["bat"],["nat","tan"],["ate","eat","tea"]]
public List<List<String>> groupAnagrams49(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) { //key can be acceee #1#0#2#0#3#0#0...
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String sortedstr = String.valueOf(arr);
        if (!map.containsKey(sortedstr)) {
            map.put(sortedstr, new ArrayList<>());
        } 
        map.get(sortedstr).add(s);
    }
    return new ArrayList<>(map.values());// convert Collection.List
}
public List<List<String>> groupStrings249(String[] strings) {
    //["abc","bcd","acef","xyz","az","ba","a","z"]--> [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strings) {
        int shift = s.charAt(0) - 'a';// build base battern
        char[] ca = s.toCharArray();
        StringBuilder key = new StringBuilder();
        for (char c : ca) {// for za, or sifts more than a cycle, + 26
            char tmp = (char)(c - shift); // cast to char
            if (tmp < 'a') { // use <'a' to check
                tmp += 26; // move forward 26 chars
            }
            key.append(tmp);
        }
        String keyStr = String.valueOf(key);
        if (!map.containsKey(keyStr)) {
            map.put(keyStr, new ArrayList<>());
        }
        map.get(keyStr).add(s);
    }
    return new ArrayList<>(map.values());
}