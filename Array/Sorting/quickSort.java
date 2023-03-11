package Sorting;

import java.util.Map;
import java.util.Stack;

public class quickSort_双边交换 {
    class quicksprt_双边recursion_912noDup {
        public int[] sortArray(int[] nums) {
            return quickSort(nums, 0, nums.length-1);
        }
        private int[] quickSort(int[] nums, int start, int end) {
            if(start >= end) return nums; // 终止条件
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
            return nums;
        }
        private int partition(int[] arr, int start, int end) {
            // use left and right pointers. Need to keep 'start' index value at the end for exchanging pivot
            int left = start, right = end, pivot = arr[start];
            while(left < right) {
                while(left < right && arr[right] > pivot) {
                    right--;
                }
                while(left < right && arr[left] <= pivot) { //第一次比较时left和pivot重合，必须要让left往前移
                    left++;
                }
                if(left < right) { // check boundary
                    int tmp = arr[left]; arr[left] = arr[right]; arr[right] = tmp;
                }
            }
            // exchange pivot and the stop point; 全是right也行，因为最后left和right会相遇
            // left = right
            arr[start] = arr[left];
            arr[left] = pivot;
            return left;
        }
    }
    class quickSortStack{
        public quickSortStack(int[] arr, int startIndex, int endIndex) {
            Stack<Map<String, Integer>> quickSortSk = new Stack<Map<String, Integer>>();
            Map<String, Integer> rootProgram = new HashMap<>();
            rootProgram.put("startIndex", startIndex);
            rootProgram.put("endIndex", endIndex);
            quickSortSk.push(rootProgram);
            while(!quickSortSk.isEmpty()) {
                Map<String, Integer> param = quickSortSk.pop();
                int pivotIndex = partition单边(arr, param.get("startIndex"), param.get("endIndex"));
                if(param.get("startIndex") < pivotIndex - 1) {
                    Map<String, Integer> leftParam = new HashMap<>();
                    leftParam.put("startIndex", param.get("startIndex"));
                    leftParam.put("endIndex", param.get("endIndex - 1"));
                    quickSortSk.push(leftParam);
                }
                if(pivotIndex + 1 < param.get("endIndex")) {
                    Map<String, Integer> leftParam = new HashMap<>();
                    leftParam.put("startIndex", param.get("startIndex"));
                    leftParam.put("endIndex", param.get("endIndex - 1"));
                    quickSortSk.push(leftParam);
                }
            }
        }
    }


    class quickSprt_dup_912 {
        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length-1);
            return nums;
        }
        private void quickSort(int[] nums, int start, int end) {
            if(start < end) {
                int pivot = partition(nums, start, end);
                quickSort(nums, start, pivot);
                quickSort(nums, pivot + 1, end);
            }
        }
        public int partition(int[] arr, int start, int end) {
            int pivot = arr[start];
            int left = start - 1;
            int right = end + 1;
            while (true) {
                do {
                    left++;
                } while (arr[left] < pivot);
                do {
                    right--;
                } while (arr[right] > pivot);
                if (left >= right) return right;
                int temp = arr[left]; arr[left] = arr[right]; arr[right] = temp;
            }
        }
    }

    class quickSort_单边_不允许重复的数{
        private void quickSort(int[] nums, int start, int end) {
            if(start >= end) return ; // 终止条件
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }
        private int partition单边(int[] arr, int start, int end) {
            // use left and right pointers. Need to keep 'start' index value at the end for exchanging pivot
            int mark = start, pivot = arr[start];
            for(int i = start + 1; i <= end; i++) {
                if(arr[i] < pivot) {
                    mark++;
                    int tmp = arr[mark]; arr[mark] = arr[i]; arr[i] = tmp;
                }
            }
            arr[start] = arr[mark];
            arr[mark] = pivot;
            return mark;
        }
    }

    class sortArray912_dup {
        public int[] sortArray(int[] nums) {
            quick3(nums, 0, nums.length-1);
            return nums;
        }
        private void quick3(int[] nums, int start, int end) {
            if (start >= end) { return; }

            // int m = this.pickMediumIndex(nums, start, end, (start + (end - start)) / 2); // choose the mid point as the pivot
            int pivot = nums[start]; //简化成选第一个，也不用交换了...
            // int temp = nums[start];
            // nums[start] = nums[m];
            // nums[m] = temp;

            int left = start, right = end;
            int index = left + 1;
            while (index <= right) {
                // if less, move to front
                if (nums[index] < pivot) {
                    int tmp = nums[index];
                    nums[index] = nums[left];
                    nums[left] = tmp;
                    ++index;
                    ++left;
                }
                // if equal, just keep the same position and move to next index.
                else if (nums[index] == pivot) {
                    ++index;
                }
                // if greater, move to end;
                // we do not increment i here because we do not know if the new element in ith position is greater or less than pivot.
                else {
                    int tmp = nums[index];
                    nums[index] = nums[right];
                    nums[right] = tmp;
                    --right;
                }
            }
            this.quick3(nums, start, left-1);
            this.quick3(nums, right + 1, end);
        }

        private int pickMediumIndex(int[] nums, int head, int tail, int mid) {
            int l = nums[head],  m = nums[mid], r = nums[tail];
            if (l > m) {
                // l > m > r
                if (m > r) return mid;
                // r > l > m
                else if (r > l) return head;
                //l > r > m
                else return tail;
            }
            // m > l
            else {
                // r > m > l
                if (r > m) return mid;
                // m > l > r
                else if (l > r) return head;
                // m > r > l
                else return tail;
            }
        }
    }
}
