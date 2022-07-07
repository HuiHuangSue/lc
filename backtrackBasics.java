public class backtracking {
    public ReturnType/void backtrackTemplate (params) {
        if (终止条件) {
            存放结果;
            return;
        }
        for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小,所有拆解的可能性）) {
            修改参数;
            backtracking(路径，选择列表); // 递归
            回溯，撤销处理结果
        }
        return ifneeded/void;
    }
    // C(4,3), then C(100,50) need 50 loops
    for(int i = 1; i <= n; i++) {
        for (int j = i + 1; j <= n; j++) {
            for (int k = j + 1, k <= n; k++) {
                ...
            }
        }
    }
    
    /*
     * 每次res.add(new ArrayList<>(curList));
     * C(n,k) n选固定k个元素, startIndex可以剪枝优化
     * 允许用多次的，startIndex不+1，用大小控制出口条件。所以一定记得排序！！for loop里可early termination优化
     * skip duplicate check i > start && cur与prev相等
     */

    // https://leetcode.com/problems/combinations/ C(n,k)
    public List<List<Integer>> combine77(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        backtrack(n, k, res, curList, 1);//pass by reference, or else declare global
        return res;
    }
    private void combineHelper(int n, int k, List<List<Integer>> res, List<Integer> curList, int startIndex) {
        if (curList.size() == k) {
            res.add(new ArrayList<>(curList));
            return;
        }
        // for (int i = startIndex; i <= n; i++) {
        for (int i = startIndex; i <= (n - (k - curList.size()) + 1); i++) {//startIndex至多从x开始
            //n=4,k=3,curList.size=0,need (k-curList.size)=3 more; 至多从4-3+1=2开始，[2,3,4],这里不是index，是n=2
            curList.add(i); // add each i
            combineHelper(n, k, res, curList, i+1); // next
            curList.remove(curList.size() - 1); // remove the last to backtrack
        }
    }

    // candidates = [2,3,6,7], target = 7 --> [[2,2,3],[7]]
    public List<List<Integer>> combinationSum39(int[] nums, int t) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // SORT!!
        combSumHelper(res, new ArrayList<>(), nums, t, 0, 0);
        return res;
    }
    private void combSumHelper(List<List<Integer>> res, List<Integer> list, int[] nums, int t, int start, int sum) {
        if (sum == t) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < nums.length && (nums[i] + sum <= t); i++) {
        // for(int i = start; i < nums.length; i++) {
            // if(sum + nums[i] > t) break; // early termination
            list.add(nums[i]);
            combSumHelper(res, list, nums, t, i, sum + nums[i]);//i can take repetitive nums so no increment
            list.remove(list.size()-1);
        }
    }
    public List<List<Integer>> combinationSum39NoSumVar(int[] nums, int t) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        combSumHelper(res, new ArrayList<>(), nums, t, 0);
        return res;
    }
    private void combSumHelper(List<List<Integer>> res, List<Integer> list, int[] nums, int t, int start) {
        if (t == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < nums.length && (t - nums[i]) >= 0; i++) {
            list.add(nums[i]);
            combSumHelper(res, list, nums, t - nums[i], i);//i can take repetitive nums so no increment
            list.remove(list.size()-1);
        }
    }
    private void combSumHelperII(List<List<Integer>> res, List<Integer> list, int[] nums, int t, int start) {
        if (t == 0) {
            res.add(new ArrayList<>(list));
            return;
        } else if (t < 0) {
            return;
        }
        for(int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            combSumHelper(res, list, nums, t - nums[i], i);//i can take repetitive nums so no increment
            list.remove(list.size()-1);
        }
    }

    // allow take only once, allow duplicates in nums. [10,1,2,7,6,1,5], target = 8 --> [[1,1,6],[1,2,5],[1,7],[2,6]]
    public List<List<Integer>> combinationSum40(int[] nums, int t) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        combSumHelper(res, new ArrayList<>(), nums, t, 0, 0);
        return res;
    }
    private void combSumHelper(List<List<Integer>> res, List<Integer> list, int[] nums, int t, int start, int sum) {
        if (sum == t) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < nums.length && (sum + nums[i]) <= t; i++) {
            // if ((sum + nums[i]) > t) break;
            if(i > start && nums[i] == nums[i - 1]) continue; // skip duplicate
            list.add(nums[i]);
            combSumHelper(res, list, nums, t, i+1, sum + nums[i]);
            list.remove(list.size()-1);
        }
    }
    
   // k = 3, n = 9 --> only nums 1-9 used. at most once [[1,2,6],[1,3,5],[2,3,4]]
   public List<List<Integer>> combinationSum216(int k, int n) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curList = new ArrayList<>();
    combineHelper216(n, k, res, curList, 1, 0);//pass by reference, or else declare global
    return res;
}
private void combineHelper216(int n, int k, List<List<Integer>> res, List<Integer> curList, int startIndex, int sum) {
    if (curList.size() == k && sum == n) {
        res.add(new ArrayList<>(curList));
        return;
    } else if (sum > n || curList.size() > k) {
        return;
    }
    // for (int i = startIndex; i <= n && i <= 9; i++) {
    for (int i = startIndex; i <= 9 && i <= (n - (k - curList.size()) + 1); i++) {//startIndex至多从x开始
        curList.add(i); // add each i
        combineHelper216(n, k, res, curList, i+1, sum + i); 
        curList.remove(curList.size() - 1); // remove the last to backtrack
    }
}

    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    // Build library
    Map<Character, List<Character>> digit2Char = new HashMap<>();
    public List<String> letterCombinations17(String digits) {
        List<String> res = new ArrayList<>();
        
        // input validation, 1 # *
        if (digits == null || digits.length() == 0) {
            return res;
        }
        // Arrays.asList('a','b');写法
        digit2Char.put('2', Arrays.asList('a', 'b', 'c'));
        digit2Char.put('3', Arrays.asList('d', 'e', 'f'));
        digit2Char.put('4', Arrays.asList('g', 'h', 'i'));
        digit2Char.put('5', Arrays.asList('j', 'k', 'l'));
        digit2Char.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        digit2Char.put('6', Arrays.asList('m', 'n', 'o'));
        digit2Char.put('7', Arrays.asList('p', 'q', 'r', 's'));
        digit2Char.put('8', Arrays.asList('t', 'u', 'v'));
        
        StringBuilder sb = new StringBuilder();
        letterCombHelper(res, digits, sb, 0);
        return res;
    }
    
    private void letterCombHelper(List<String> res, String digits, StringBuilder sb, int startIndex) {
        if (sb.length() == digits.length()) {
            res.add(new String(sb));
            return;
        }
        if (sb.length() > digits.length()) {
            return;
        }
        for (int i = startIndex; i < digits.length(); i++) {
            char key = digits.charAt(i);
            for (char c : digit2Char.get(key)) { // for loop
                sb.append(c);
                letterCombHelper(res, digits, sb, i + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    // https://leetcode.com/problems/subsets/submissions/
    public List<List<Integer>> subsets78(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetHelper78(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    private void subsetHelper78(List<List<Integer>> res, List<Integer> list, int[] nums, int startIndex) {
        res.add(new ArrayList<Integer>(list));
        for(int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            subsetHelper78(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // https://leetcode.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup90(int[] nums) {
        Arrays.sort(nums); // must sort!!!!!!
        List<List<Integer>> res = new ArrayList<>();
        subsetHelper90(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    private void subsetHelper90(List<List<Integer>> res, List<Integer> list, int[] nums, int startIndex) {
        res.add(new ArrayList<Integer>(list));
        for(int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) continue; // skip dup
            list.add(nums[i]);
            subsetHelper90(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute46(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper46(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    private void permuteHelper46(List<List<Integer>> res, List<Integer> list, int[] nums, int startIndex) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return; // remember to return
        }
        for(int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue; // skip already added elements
            list.add(nums[i]);
            permuteHelper46(res, list, nums, i);
            list.remove(list.size() - 1);
        }
    }

    // https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> permuteUnique47(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        permuteHelper47(res, new ArrayList<Integer>(), nums, visited);
        return res;
    }
    private void permuteHelper47(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return; // remember to return
        }
        for(int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) continue; // 1,1,2, last one unvisited yet
            visited[i] = true;
            list.add(nums[i]);
            permuteHelper47(res, list, nums, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
            
        }
    }
}

