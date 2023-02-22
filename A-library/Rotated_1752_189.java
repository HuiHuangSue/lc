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
