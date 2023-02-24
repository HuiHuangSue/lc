public class Rotated_1752_189 {
    // Check if array is sorted and rotated
    public boolean checkIfArrSortedRotated_1752(int[] nums) {
        // allow once only for not ascending scenario
        // use (index%n) to connect head and tail
        int limit = 0, len = nums.length;
        for(int i = 0; i < len; i++) {
            if(nums[i] > nums[(i+1)% len]) {
                limit++;
            }
            if(limit > 1) return false;
        }
        return true;
    }
}

class rotateArraybyK189 {
    // M1 BF: rotate k%len times, O(n*k)
    // M2: Extra Array arr[(i+k)%len)]=nums[i], copy over arr
    // M3: reverse all, then reverse(0,k-1), then reverse(k,len-1)
    //     1,2,3,4,5,6,7 --> 7,6,5,4,3,2,1 --> 5,6,7,4,3,2,1 --> 5,6,7,1,2,3,4
    // M4: Cyclic replacements
    public void rotate189_M4(int[] nums, int k){
        // M4: Cyclic replacements
        k %= nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
    public void rotate189_M1_BF(int[] nums, int k) {
        // M1 BF: rotate k%len times, O(n*k)
        // speed up the rotation
        k %= nums.length;
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                System.out.println(Arrays.toString(nums));
                temp = nums[j]; //
                nums[j] = previous; //
                previous = temp;//
            }
            /* previous: 7
                tmp = 1, nums[0]=7, previous=1
                tmp = 2, nums[1]=1, previous=2
                tmp = 3, nums[2]=3, previous=3
            */
            System.out.println("k--");
            System.out.println(Arrays.toString(nums));
        }
    }
    public void rotate189_M2_ExtraArray(int[] nums, int k) {
        // M1 BF: rotate k%len times, O(n*k)
        // M2: Extra Array arr[(i+k)%len)]=nums[i], copy over arr
        k %= nums.length;
        int[] oldArr = nums.clone();
        for(int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = oldArr[i]; // i+k还是可能越界，要%n; 最后结果要存nums里
        }
    }
    public void rotate189_M3_reverse(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
        }
    }
}

/*
nums = [4,5,6,7,0,1,2], target = 0; Output: 4
    Formula: If a sorted array is shifted, if you take the middle, always one side will be sorted.
    Take the recursion according to that rule.

    2a- if [left] < target < [middle] then do recursion with left, middle - 1 (right)
    2b- left side is sorted, but target not in here, search on right side middle + 1 (left), right
    3- if middle is less than right side, it means right is sorted
    3a- if [middle] < target < [right] then do recursion with middle + 1 (left), right
    3b- right side is sorted, but target not in here, search on left side left, middle -1 (right)
*/
public int search33_inSortedArray(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while(left <= right) { // left=right is legit
        int mid = left + (right - left) / 2;
        if(nums[mid] == target) {
            return mid;
        } else if (nums[mid] >= nums[left]) {  // left is sorted; >=, [3,1],1 will->-1
            // [left] <= target < [middle] do recursion with left, middle - 1 (right)
            if (target >= nums[left] && target < nums[mid]) right = mid - 1;
            // left side is sorted, but target not in, search on right side middle + 1 (left)
            else left = mid + 1;
        }
        else { // middle is less than right side, it means right is sorted
            // [middle] < target <= [right] then do recursion with right
            if (target <= nums[right] && target > nums[mid]) left = mid + 1;
            // right side is sorted, but target not in, search on left side
            else right = mid - 1;
        }
    }
    return -1;
}

public boolean searchIjRotatedArray81_withDuplicates(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while(left <= right) { // left=right is legit
        while(left < right && nums[left] == nums[left+1]) left++;
        while(left < right && nums[right] == nums[right-1]) right--;
        int mid = left + (right - left) / 2;
        if(nums[mid] == target) {
            return true;
        } else if (nums[mid] >= nums[left]) {  // left is sorted; >=, [3,1],1 will->-1
            // [left] <= target < [middle] do recursion with left, middle - 1 (right)
            if (target >= nums[left] && target < nums[mid]) right = mid - 1;
            // left side is sorted, but target not in, search on right side middle + 1 (left)
            else left = mid + 1;
        }
        else { // middle is less than right side, it means right is sorted
            // [middle] < target <= [right] then do recursion with right
            if (target <= nums[right] && target > nums[mid]) left = mid + 1;
            // right side is sorted, but target not in, search on left side
            else right = mid - 1;
        }
    }
    return false;
}

public int findMin153(int[] nums) {
    int left = 0, right = nums.length - 1;
    while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] > nums[right]) { // min肯定在右边
            left = mid + 1;
        } else { // [6,7,0,1,2,3,4], min在左边, mid<right也有可能是min，要保留
            right = mid;
        }
    }
    return nums[left]; //return 左边的
}
// [2,2,2,2,2,0,1,2,2]
// [2,2,3,4,2,2,2,2,2]
public int findMin154_withDup(int[] nums) {
    int left = 0, right = nums.length - 1;
    while(left < right) {
        while(left < right && nums[left] == nums[left + 1]) left++;
        while(left < right && nums[right] == nums[right - 1]) right--;
        int mid = left + (right - left) / 2;
        if(nums[mid] > nums[right]) { // min肯定在右边
            left = mid + 1;
        } else { //没有重复的数所以不会相等 [6,7,0,1,2,3,4], min在左边, mid<right也有可能是min，要保留
            right = mid;
        }
    }
    return nums[left]; //return 左边的
}
public int findMin154_wDup(int[] nums) {
    // [2,2,2,2,2,0,1,2,2]
    // [2,2,3,4,2,2,2,2,2]
    int left = 0, right = nums.length - 1;
    while(left < right) {
        // while(left < right && nums[left] == nums[left + 1]) left++;
        // while(left < right && nums[right] == nums[right - 1]) right--;
        int mid = left + (right - left) / 2;
        if(nums[mid] > nums[right]) { // min肯定在右边
            left = mid + 1;
        } else if(nums[mid] < nums[right]) { // [6,7,0,1,2,3,4], min在左边, mid<right也有可能是min，要保留
            right = mid;
        } else {  // mid和right相等，就把right--，因为丢掉也不影响
            right--;
        }
    }
    return nums[left]; //return 左边的
}
