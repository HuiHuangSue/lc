// sorted, unique
public class BinarySearchBasics {
    // [704 Binary Search](https://leetcode.com/problems/binary-search/)
    public int search704I(int[] nums, int t) { //[Left,Right]
        // [-1,0,3,5,9,12]
        int left = 0, right = nums.length - 1;
        while (left <= right) { // [left, right]
            int mid = left + (right - left) / 2;
            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] < t) { // t = 9, search right interval
                left = mid + 1; // mid is not answer, skip
            } else { // t = 0, search left interval
                right = mid - 1;
            }
        }
        return -1;
    }
    public int search704II(int[] nums, int t) { //[Left, right)
        int left = 0, right = nums.length; // right), so not include right
        while(left < right) { //[1,1) invalid, left=right is invalid range.
            int mid = left + (right - left) / 2;
            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] < t) { // updating left bound
                left = mid + 1; // mid cannot be answer, so can skip
            } else { // updating right bound, right not included, can be next answer
                right = mid;
            }
        }
        return -1;
    }

    // This template exist the while loop when left>right. left lands the latter pos.
    // find the position of the first number >= target; arr distinct ascending
    public int searchInsert35(int[] nums, int target) { 
        /* return index to insert if not found. [1,3,5,6]
        t=0 -> 0; t=4 -> 2; t=5 -> 2; t=8 -> 4 
        1 front; 2 after end; 3 in between
        if any numbers >= target, insert
        */
        int left = 0, right = nums.length - 1;
        while(left <= right) { // eventually left != right, exit when left>right, taking the right pos
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // front: l = 0, r = -1; back: l = nums.length, r = nums.length-1;
        // in between: [1,2,3,5,7,9,18]t=15,l = 6,r = 5
        // return right + 1;
        return left;
        
        /* O(n)
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;*/
    }

    public char nextGreatestLetter744(char[] letters, char t) {
        // the first > t
        int left = 0, right = letters.length - 1, n = letters.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= t) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // M2
        int l = 0, N = letters.length, r = N;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if (letters[mid] > t) { // find >t, right could be the smallest. keep and moving left
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return letters[left % n]; //[a,b], t=z --> return a 
        
        /* for(int i = 0; i < letters.length; i++) {
            if (letters[i] > t) {
                return letters[i];
            }
        }
        return letters[0]; // otherwise return the first one  */
    }

    // when right/left include mid, while should exclude when left/right meets.
    public int firstBadVersion278(int n) {
        // [1,n] g g b b b 
        int left = 1, right = n;
        while(left < right) {//right not moving out of mid, left < right
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)) {//it could be the answer g b b b b or g g b b b
                right = mid; // keep search left
            } else { // g g g g b b search right, and mid can be excluded
                left = mid + 1;
            }
        }
        return left; //last iteration did not go mid, so need left/right to land to answer
        // return right;
    }
    

    public int mySqrt69(int x) {
        // find rightmost i * i <= x
        int left = 1, right = x / 2 + 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            long product = (long)mid * mid;
            if (product == x) {
                return mid;
            } else if (product < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;//return 最后落脚偏左的index 
    }
    public boolean isPerfectSquare367(int num) {
        int left = 1, right = num / 2 + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long)mid * mid;
            if (square == num) return true;
            else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public int[] searchRange34I(int[] nums, int target) {
        // sorted non decreasing, duplicates allowed
        // find index by binary search
        // 1: no exist: -1, -1
        // 2: go left and right until found boundary
        int index = binarySearch(nums, target);
        if (index == -1) return new int[]{-1, -1};
        int left = index, right = index;
        while(left -1 >= 0 && left < nums.length && nums[left] == nums[left - 1]) {
            left--;
        }
        while(right + 1 < nums.length && right >= 0 && nums[right] == nums[right + 1]) {
            right++;
        }
        return new int[]{left, right};
    }
    
    public int[] searchRange34II(int[] nums, int target) {
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);
        System.out.println(leftBorder + " " + rightBorder); //// [5,7,7,8,8,10] t=6 -->[1,0]
        //[5,7,7,8,10], 8 --> 2,4
        // 情况一
        if (leftBorder == -200 || rightBorder == -1000) return new int[]{-1, -1}; //【2，2】，3
        // 情况三
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二
        return new int[]{-1, -1};
    }
    
    int getRightBorder(int[] nums, int target) { // leftmost > t
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -1000; // 记录一下rightBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] > target) {
                right = middle - 1;
            } else { // <, or =, still look for right side. 
                left = middle + 1;
                // rightBorder = left;
            }
        }
        // return rightBorder;
        return left; //break when left>right, so returning post-position
    }

    int getLeftBorder(int[] nums, int target) { // rightnmost < t
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -200; // 记录一下leftBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] >= target) { // 寻找左边界，nums[middle] == target的时候更新right
                right = middle - 1;
                // leftBorder = right;
            } else {
                left = middle + 1;
            }
        }
        // return leftBorder;
        return right;//break when left>right, return pre-position
    }

    public int search702(ArrayReader reader, int target) {
        int index = 1;
        while(reader.get(index) < target) {
            index *= 2;
        }
        int left = 0, right = index;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public int guessNumber374(int n) {
        // from [1,n]
        int left = 1, right = n;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) { // guess < pick, search right interval
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
