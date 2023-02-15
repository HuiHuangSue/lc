public class twoSum1_167InputSorted_lessThanK1099 {
    public int[] twoSum1(int[] nums, int target) {
        // input was sorted? return ans in any order? only 1 valid answer exist? dup nums in input?
        // [2,7,11,15], t=9; {(2,0)}
        //[3,2,1,3,4], t=6; {(3,0),(2,1),(1,2),(found)} -->invalid test case as not unique.
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public int[] twoSum167InputSorted(int[] nums, int target) {
        int i = 0, j = nums.length - 1, sum = 0;
        while(i < j) {
            sum = nums[i] + nums[j];
            if(sum == target) {
                return new int[]{i + 1,j + 1}; // requested for 1 indexed result
            } else if(sum < target){
                i++;
            } else{
                j--;
            }
        }
        return null;
    }

    public int twoSumLessThanK1099(int[] nums, int k) {
        // indexes i<j means cannot use same number twice, but can sort.
        if(nums == null || nums.length < 2) return -1;
        Arrays.sort(nums);
        int res = -1, sum = 0;
        for(int i = 0, j = nums.length - 1; i < j; ){ // i<j, cannot be same number
            sum = nums[i] + nums[j];
            if(sum >= k) {
                j--;
            } else { // sum<k
                res = Math.max(sum, res); // 符合要求，取更大值
                i++;
            }
        }
        return res;
    }

    class TwoSum170 {
        // ask which function is used more frequently, add or find
        // address corner case [3,4,5], t=8, then not sure if there's ;4,4. Use map not set.
        // Add O(1), find O(n)
        Map<Integer, Integer> map;
        public TwoSum() {
            map = new HashMap<>();
        }
        public void add(int number) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        public boolean find(int value) {
            for(int key : map.keySet()) { // {3,1;4,1;5,1}
                int target = value - key;
                int wantedCount = (target == key) ? 2 : 1;
                if (map.getOrDefault(target, 0) >= wantedCount) {
                    //use getOrDefault o.w error if target not exist in key
                    return true;
                }
            }
            return false;
        }

        /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class TwoSum653_Input_BST {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        List<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (list.size() > 0) {
            TreeNode current = list.remove(0);
            if (set.contains(current.val)) {
                return true;
            } else {
                set.add(k - current.val);
            }
            if (current.left != null) {
                list.add(current.left);
            }
            if (current.right != null) {
                list.add(current.right);
            }
        }
        return false;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean twoSumBSTs1214(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null || root2 == null) return false;
        Set<Integer> set = new HashSet<>();
        List<TreeNode> list1 = new LinkedList<>();
        list1.add(root1);
        while(list1.size() > 0) {
            TreeNode current = list1.remove(0);
            set.add(target - current.val);
            if(current.left != null) {
                list1.add(current.left);
            }
            if(current.right != null) {
                list1.add(current.right);
            }
        }
        List<TreeNode> list2 = new LinkedList<>();
        list2.add(root2);
        while(list2.size() > 0) {
            TreeNode current = list2.remove(0);
            if(!set.add(current.val)){
                return true;
            }
            if(current.left != null) {
                list2.add(current.left);
            }
            if(current.right != null) {
                list2.add(current.right);
            }
        }
        return false;
    }
}
    }
