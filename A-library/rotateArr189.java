public class rotateArr189 {
    /*
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
        Example 1:
        Input: nums = [1,2,3,4,5,6,7], k = 3
        Output: [5,6,7,1,2,3,4]
        Explanation:
        rotate 1 steps to the right: [7,1,2,3,4,5,6]
        rotate 2 steps to the right: [6,7,1,2,3,4,5]
        rotate 3 steps to the right: [5,6,7,1,2,3,4]
        Example 2:

        Input: nums = [-1,-100,3,99], k = 2
        Output: [3,99,-1,-100]
        Explanation: 
        rotate 1 steps to the right: [99,-1,-100,3]
        rotate 2 steps to the right: [3,99,-1,-100]
     */

    public void rotate189(int[] nums, int k) {
        // reverse all; reverse 0, k; reverse k, len-1
        k %= nums.length;// if k is too big
        rev(nums, 0, nums.length - 1);
        rev(nums, 0, k - 1);
        rev(nums, k , nums.length - 1);
        
    }

    public void rev(int[] s, int i, int j) {
        while(i < j) {
            int tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }


    public void rotate189ExtrSpaceN(int[] nums, int k) {
        k %= nums.length;
        int[] res = new int[nums.length];
        int len = nums.length, index = 0;
        for (int i = 0; i < nums.length; i++) {
            res[(i + k) % nums.length] = nums[i];
        }
        // for(int i = len - k; i < len; i++) {
        //     res[index] = nums[i];
        //     index++;
        // }
        // for (int i = 0; i < len - k; i++) {
        //     res[index] = nums[i];
        //     index++;
        // }
        for (int i = 0; i < len; i++) {
            nums[i] = res[i];
        }
    }

     // rotateStr
     public String reverseLeftWords(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        // rev(c, 0, s.length()-1);
        // rev(c, 0, n-1);
        // rev(c, n,s.length() - 1);
        rev(chars, 0, len - 1);
        rev(chars, 0, len - 1 - k); // 因为reverse过了，所以前k个在n-1-k那， index所以用的n-1
        rev(chars, len - k, len - 1);
        
        return new String(chars);
    }
    public void rev(char[] s, int i, int j) {
        while(i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
