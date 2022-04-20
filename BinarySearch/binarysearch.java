/*
Usage: Sorting; Runtime < O(n); Find a position where left A-conditioned but right non-A conditioned; Find max/min
*/
    public List<Integer> findClosestElements658Sliding(int[] arr, int k, int x) {
        int low = 0, high = arr.length - 1;
        while (k <= high - low) { // 5 nums between 0..4; so stop when difference is k-1
            // example里尽量take靠左，所以相等时靠左,high--
            if (Math.abs(x - arr[low]) <= Math.abs(arr[high] - x)) {
                high--;
            } else {
                low++;
            }
        }
        return Arrays.stream(arr, low, high+1).boxed().collect(Collectors.toList());//low to high inclusive
    }
    public List<Integer> findClosestElements658(int[] arr, int k, int x) {
        // sliding window + bs
        int low = 0, high = arr.length - k;
        while (low < high) {
            int mid = low + (high - low)/ 2;
            if (x-arr[mid] > arr[mid+k] - x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return Arrays.stream(arr, low, low + k).boxed().collect(Collectors.toList());//low to high inclusive
    }

    public int search702UnknownSize(ArrayReader reader, int t) {
        // unknown size: start from 1, double right and set to left boundary if < target
        if (reader.get(0) == t) return 0;
        int left = 0, right = 1;
        while(reader.get(right) < t) { // double until right >= t
            left = right;
            right = right << 1; // multiple<<1, divide>>1
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == t) return mid;
            else if (reader.get(mid) < t) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // while (left + 1 < right) {
        //     int mid = left + (right - left) / 2;
        //     if (reader.get(mid) == t) return mid;
        //     else if (reader.get(mid) < t) {
        //         left = mid;
        //     } else {
        //         right = mid;
        //     }
        // }
        // if (reader.get(left) == t) return left;
        // if (reader.get(right) == t) return right;
        return -1;
    }
    
    public int firstBadVersion278(int n) {
        int low = 1, high = n;
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
        // while (low + 1 < high) {
        //     int mid = low + (high - low)/2;
        //     if (isBadVersion(mid)) {
        //         high = mid;
        //     } else {
        //         low = mid;
        //     }
        // }
        // if (isBadVersion(low)) return low; // check left first
        // if (isBadVersion(high)) return high;
        // return -1;
    }

    public int searchInsertPosition35(int[] nums, int t) {
        // find rightmost <= t, or leftmost >= t
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] > t) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    public int guessNumber374(int n) {
        int low = 1, high = n;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if(guess(mid) > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (guess(low) == 0) return low;
        if (guess(high) == 0) return high;
        return -1;
    }

    public int binarySearch704Template(int[] nums, int t) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        // define start and end
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) { // l + 1 < r
            int mid = l + (r - l) / 2;
            if (nums[mid] == t) {
                return mid;
                // mid stays, no incerement, no decrement.
            } else if (nums[mid] > t) {
                r = mid;
            } else {
                l = mid;
            }
        }
        // check start and end
        if (nums[l] == t) return l;
        if (nums[r] == t) return r;
        return -1;
    }



