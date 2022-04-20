/*
Sliding window (90%)
Requires O(n) (80%)
Cannot allocate new memory/space with swap only (80%)
With keywords such as subarray/substring, palindrome (50%)
*/

public class TwoPointersTemplate {
    // 相向双指针(patition in quicksort)
    public void partition(int[] A, int start, int end) {
        if (start >= end) return;
        int left = start, right = end;
        int pivot = A[(start + end) / 2]; // pivot is the value, not index
        while (left <= right) { // always include when 2 pointers meet
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && pivot < A[right]) {
                right--;
            }
            if (left <= right) { // does not hurt to swap when meet
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
                // update index after swapping
                left++;
                right--;
            }
        }
    }

    // 背向双指针 (e.g, longest palindrome)
        int left = position, right = position + 1;
        while (left >= 0 && right < length) {
            if (可以停下来了) break;
            left--;
            right++;
        }

    // 同向双指针 (e.g remove consecutive duplicates)
    int j = 0;
    for(int i=0; i<n; i++){
        // 不满足则循环到满足搭配为止
        while(j<n && i到j之间不满足条件){
            j+=1;
        }
        if (i 到 j 之间满足条件) {
            处理 i，j 这次搭配
        }
    }
    // 合并双指针
    ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        // 需要 new 一个新的 list，而不是在 list1 或者 list2 上直接改动
        ArrayList<Integer> newList = new ArrayList<Integer>();
        inti=0,j=0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                newList.add(list1.get(i));
                i++;
            } else {
                newList.add(list2.get(j));
                j++;
            }
        }
        // 合并上下的数到 newList 里
        // 无需用 if (i < list1.size())，直接 while 即可
        while (i < list1.size()) {
            newList.add(list1.get(i));
            i++;
        }
        while (j < list2.size()) {
            newList.add(list2.get(j);
            j++;
        }
        return newList;
    }
}

/*
A: 对撞型 
  A1: 2-Sum type
        3sumclosest
        4sum
        3sum
        two sum II
        triangle count
        trapping rain water
        container with most water
  A2: Partition:
        partition-array
        sort-colors
        partition array by odd and even
        sort letters by case
        valid palindrome

B: 前向型 
  B1: Window type

  https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/49708/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
        Remove nth node from end of list
        minimum size subarray sum
        minimum window substring
        longest substring with at most k distinct char
        longest substring without repeating char (forward pointer, hash/set record last visit)
  B2: fast-slow type
        find the middle of linked list
        linkedlist cycle i, ii

C: 2 array, 2 pointers 并行
        the smallest difference N^2 --> nlogn
        Merge 2 sorted lists



*/
// Two Sum template
    if (A[i] + A[j] > sum) {
        j--;
        do something
    }  else if (A[i] + A[j] < sum) {
        i++;
        do something
    } else {
        do something;
        i++ or j--
    }
// 灌水类
if (A[i] > A[j]) {
    j--;
} else if (A[i] < A[j]) {
    i++
} else {
    i++
}

// 窗口型
while (i < n-1) {
    while (j < n-1) {
        if (满足条件) {
            j++
            更新状态
        } else {
            break；
        }
    }
    i++
    更新状态
}


https://www.youtube.com/watch?v=_cVu4AYoeTw&t=22s&ab_channel=%E5%B1%B1%E6%99%AF%E5%9F%8E%E4%B8%80%E5%A7%90
// nums = [0,1,2,2,3,0,4,2], val = 2 --> 5, nums = [0,1,4,0,3,_,_,_]
public int removeElement(int[] nums, int val) {
    int reserved = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[reserved] = nums[j];
            reserved++;
        }
    }
    return reserved;
}
// nums = [0,1,0,3,12] --> [1,3,12,0,0]
public void moveZeroes(int[] nums) {
    int reserved = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != 0) {
            int tmp = nums[reserved];
            nums[reserved] = nums[j];
            nums[j] = tmp;
            reserved++;
        }
    }
}
// nums = [0,0,1,1,1,2,2,3,3,4] -->  5, nums = [0,1,2,3,4,_,_,_,_,_]
public int removeDuplicatesFromSortedArray(int[] nums) {
    int reserved = 1;//first element always unique by itself
    for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[j-1]) {
            nums[reserved] = nums[j];
            reserved++;
        }
    }
    return reserved;
}
// nums = [0,0,1,1,1,1,2,3,3] --> 7, nums = [0,0,1,1,2,3,3,_,_]
public int removeDuplicates(int[] nums) {
    int reserved = 1;
    boolean twice = false;
    for (int j = 1; j < nums.length; j++) {
        if (!twice && nums[j] == nums[j - 1]) {
            nums[reserved] = nums[j];
            twice = true;
            reserved++;
        } else if (nums[j] != nums[j - 1]) {
            nums[reserved] = nums[j];
            reserved++;
            twice = false;
        } // other cases just skip. 
    }
}
// wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice" --> 3
// wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding" --> 1
public int shortestDistance243_245(String[] wordsDict, String word1, String word2) {
    int index = -1, min = wordsDict.length; //need 1 index only as means found first match
    for (int i = 0; i < wordsDict.length; i++) {
        if ((wordsDict[i].equals(word1) || wordsDict[i].equals(word2))) {
            if (index != -1 && !wordsDict[i].equals(wordsDict[index])) {//dedup from previous
            if (index != -1 && (word1.equals(word2) || !wordsDict[i].equals(wordsDict[index]))) {//allow same word1 & word2
                min = Math.min(min, Math.abs(index - i)); }
            index = i;
        }
    }
    
    int w1 = -1, w2 = -1, min = wordsDict.length; //2 pointer ituitive
    for (int i = 0; i < wordsDict.length; i++) {//use .equals() to check string equal or fail
        if (wordsDict[i].equals(word1)) { w1 = i; } 
        else if (wordsDict[i].equals(word2)) { w2 = i; }
        if (w1 != -1 && w2 != -1) { min = Math.min(min, Math.abs(w1 - w2));}
    }
    return min;
}
class WordDistance244 {
    Map<String, ArrayList<Integer>> map;
    public WordDistance(String[] dict) {
        this.map = new HashMap<>();
        for(int i = 0; i < dict.length; i++) {
            if (map.containsKey(dict[i])) {
                this.map.get(dict[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                this.map.put(dict[i], list);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = this.map.get(word1), l2 = this.map.get(word2);
        int min = Integer.MAX_VALUE, p1 = 0, p2 = 0;//minDiff of 2 sorted lists
        while (p1 < l1.size() && p2 < l2.size()) { //2 pointers
            min = Math.min(min, Math.abs(l1.get(p1) - l2.get(p2)));
            if (l1.get(p1) < l2.get(p2)) { //move smaller pointer forward
                p1++;
            } else {
                p2++;
            }
        } //no need when 1 list left alone. all elements after that will be bigger diff
        return min;
    }
}
