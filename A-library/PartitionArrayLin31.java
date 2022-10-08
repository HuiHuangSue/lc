public class PartitionArrayLin31 {
    // in quicksort, nums[right]>k, the pivot does not belong to left nor right
    // if set to 1 side, [1,1,1,1] to n^2

    // in quicksort, nums[right]>k, the pivot does not belong to left nor right
    // if set to 1 side, [1,1,1,1] to n^2

    // All left < k, right >=k 
    // return first position nums[i] >= k

    public int partitionArray(int[] nums, int k) {
        // if (nums.length == 0) return 0;
        // return nums.ToList().Count(x => x < k);
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while(left <= right) { // breaks when left>right, left landed at first index+1 pos
            while(left <= right && nums[left] < k) left++;
            while(right >= left && nums[right] >= k) right--; // >=. in quicksort,>

            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }

    public int partitionArrayII(int[] nums, int k) {
        // if (nums.length == 0) return 0;
        // return nums.ToList().Count(x => x < k);
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while(left < right) {
            while(left < right && nums[left] < k) left++;
            while(right > left && nums[right] >= k) right--; // >=. in quicksort,>

            if(left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return nums[left] < k ? left + 1 : left; //[3,2,1],2 and [7,7,9,8,6,6,8,7,9,8,6,6], 10
    }
}
