public class impleStrStr28 {
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

    // bad? aaaaaaaab, aaab, this is O(m*n)
}

// Rabin Karp, RK algorithom, use hash value to compare
/*
ex: abbcefgh, bce
Hash function M1: a=1,b=2,c=3... --> bce=2+3+5=10; however hash collision when bce, bec, cbe as order doesnt matter
Hash function M2:bce=2*26+3*26+5=1435; more computation or overflow, should do %.

For simplicity, we use M1 to demo. say Hash(bce)=10;
Mom every 3 char until match: abb=5; bbc=7; bce=10 --> return index 2
     When doing calculation, cur head and add tail.
     ex: abbcefg=26, when move to bbcefgd of abbcefgdeaqfw, 26-1+4=29.
Saved comparison, then m-n on adding/subtracting sum;
Hence overall becomes O(m+n)
*/

// KMP, complexity: O(n) + O(m+n); space O(n) for nextArr
public static int kmp(String mom, String kid) {
    // 预处理next数组，是利用longest matchable prefix, longest matched postfix
    int[] next = getNextsIndex(kid); // O(n)
    int j = 0;
    for(int i = 0; i < mom.length(); i++) {
        while(j > 0 && mom.charAt(i) != kid.charAt(j)) {
            // 遇到坏字符，查询nexts数组，以改变查询的起点
            j = nexts[i];
        }
        if(mom.charAt(i) == kid.charAt(j)) {
            j++; // 之后看有j个没，没有的话就i++继续一起往后移动比较
        }
        if(j == kid.length()) return i - kid.length() + 1; // 当时已经比较到最后一个char了
    }
    return -1;
}
// GTGTGAGCTGGTGTGTGCFAA
// GTGTGC
// --> A is bad char; longest matable prefix GTG; longest matable postfix: GTG
// next round, 对其longest matchable prefix and longest matchable postfix （example里前进移动了2个char), next[0,0,0,1,2,3,0]

