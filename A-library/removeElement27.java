public class removeElement27 {
    /*
    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. 
    The relative order of the elements may be changed.

    Since it is impossible to change the length of the array in some languages, 
    you must instead have the result be placed in the first part of the array nums.
     More formally, if there are k elements after removing the duplicates, 
     then the first k elements of nums should hold the final result. 
     It does not matter what you leave beyond the first k elements.

    Return k after placing the final result in the first k slots of nums.

    Do not allocate extra space for another array. 
    You must do this by modifying the input array in-place with O(1) extra memory.*/

    /*
     * Input: nums = [3,2,2,3], val = 3, Output: 2, nums = [2,2,_,_], return 2
        It does not matter what you leave beyond the returned k (hence they are underscores).

        Input: nums = [0,1,2,2,3,0,4,2], val = 2  Output: 5, nums = [0,1,4,0,3,_,_,_] return k = 5, 
     */

     // slow and fast pointers. 
     public int removeElement27FastSlowPointers(int[] nums, int val) {
        // [0,1,2,2,3,0,4,2]
        int fast = 0, slow = 0;
        // fast一直往前走，只有当fast不是val的时候，值assign给slow，slow再往前移
        while (fast < nums.length) {
            if(nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;//slow停留在最后valid值前一位，刚好index0开始抵消，slow=几个值
    }

     public int removeElement27相向双指针(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) { //l=r valid, can swap themselves
            // 找到左边第一个等于val的，所以不是val的一直往前继续找
            while(left <= right && nums[left] != val) { 
                left++;
            }
            // 找打右边第一个不是val的，如果是val的倒退skip
            while(left <= right && nums[right] == val) {
                right--;
            }
            // 查越界 （相等没意义，不算进来)
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return left; //最后return left指针
    }
}
