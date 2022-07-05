public class Backtrack {
    // https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

/*Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]*/
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    backtrack(nums, res, curList, 0);
    return res;
}
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curList, int start) {
    res.add(new ArrayList<>(curList));
    for (int i = start; i < nums.length; i++) {
       curList.add(nums[i]);
       backtrack(nums, res, curList, i + 1); // i + 1 to avoid reusing
       curList.remove(curList.size() - 1);
    }
}
/* Input: nums = [1,2,2]
    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
    */
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    Arrays.sort(nums); // needs to sort
    backtrack(nums, res, curList, 0);
    return res;
}
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curList, int start) {
    res.add(new ArrayList<>(curList));
    for (int i = start; i < nums.length; i++) {
       if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
        curList.add(nums[i]);
       backtrack(nums, res, curList, i + 1); // i + 1 to avoid reusing
       curList.remove(curList.size() - 1);
    }
}


https://leetcode.com/problems/combination-sum/discuss/1777569/FULL-EXPLANATION-WITH-STATE-SPACE-TREE-oror-Recursion-and-Backtracking-oror-Well-Explained-oror-C%2B%2B
// nums = [2,3,6,7], target = 7  -->  [[2,2,3],[7]]
// nums = [2,3,5], target = 8 --> [[2,2,2,2],[2,3,3],[3,5]]
public List<List<Integer>> combinationSum(int[] nums, int target) { //N^(k) or N^(k/max_soln_len)
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    // Arrays.sort(nums); // can stop when next num is > target
    backtrack(nums, res, curList, target, 0);
    return res;
}
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curList, int target, int start) {
    if (target < 0) {
        return;
    } else if (target== 0) {
        res.add(new ArrayList<>(curList));
        return;
    } else {
        // for (int i = start; i < nums.length && nums[i] <=target; i++) { // should sort first
        for (int i = start; i < nums.length; i++) {
            curList.add(nums[i]);
            backtrack(nums, res, curList, target - nums[i], i);
            curList.remove(curList.size() - 1);
        }
    }
} 

// [10,1,2,7,6,1,5], target = 8 --> [[1,1,6],[1,2,5],[1,7],[2,6]]
// [2,5,2,1,2], target = 5 -->  [[1,2,2],[5]]
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    Arrays.sort(nums); 
    backtrack(nums, res, curList, target, 0);
    return res;
}
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curList, int target, int start) {
    if (target < 0) {
        return;
    } else if (target== 0) {
        res.add(new ArrayList<>(curList));
        return;
    } else {
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            curList.add(nums[i]);
            backtrack(nums, res, curList, target - nums[i], i + 1); // starting the next one to use only once
            curList.remove(curList.size() - 1);
        }
    }
}

// k = 3, n = 7 --> [[1,2,4]]; k = 3, n = 9 --> [[1,2,6],[1,3,5],[2,3,4]]
public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    backtrack(res, curList, n, 1, k);
    return res;
}
private void backtrack(List<List<Integer>> res, List<Integer> curList, int n, int start, int k) {
    if (n < 0 || curList.size() > k) { // avoid useless additions
        return;
    } else if (n == 0 && curList.size() == k) {
        res.add(new ArrayList<>(curList));
        return;
    } else {
        for (int i = start; i <= 9; i++) { // include 9
            curList.add(i);
            backtrack( res, curList, n - i, i + 1, k); // starting the next one to use only once
            curList.remove(curList.size() - 1);
        }
    }
}

/* Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]] */
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    backtrack(nums, res, curList);
    return res;
}
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curList) {
   if (curList.size() == nums.length) {
       res.add(new ArrayList<>(curList));
       return;
   } else {
       for (int i = 0; i < nums.length; i++) { // everytime start w/ 0
           if (curList.contains(nums[i])) continue; // skip already included element
           curList.add(nums[i]);
           backtrack(nums, res, curList);
           curList.remove(curList.size() - 1);
       }
    }
}

// nums = [1,1,2] --> [[1,1,2],[1,2,1],[2,1,1]]
// nums = [1,2,3] --> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    Arrays.sort(nums);// must sort
    boolean[] visited = new boolean[nums.length];
    backtrack(nums, res, curList, visited);
    return res;
}
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curList, boolean[] visited ) {
   if (curList.size() == nums.length) {
       res.add(new ArrayList<>(curList));
       return;
   } else {
       for (int i = 0; i < nums.length; i++) {
           if (visited[i] ||  i > 0 && nums[i] == nums[i-1] && !visited[i - 1] ) continue; // skip already included element
           visited[i] = true;
           curList.add(nums[i]);
           backtrack(nums, res, curList, visited );
           visited[i] = false;
           curList.remove(curList.size() - 1);
           
       }
    }
}
}

