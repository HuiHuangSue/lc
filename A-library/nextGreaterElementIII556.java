class Solution {
    public int nextGreaterElementIII556(int n) {
        // refactor steps in helper functions, private static helper123
        // smallest: 12345, biggest 54321
        // 12354 --> 12535
        // 1233633 --> 123 6333
        char[] arr = ("" + n).toCharArray();
        int i = arr.length - 2; // at 5
        // 1. from the end, find index of first number in non-reversing
        while(i >= 0 && arr[i] >= arr[i + 1]) { // >=, as when equal, should keep looking
            i--;
        }
        if (i < 0) { // already decreasing order, which is maximum 54321
            return -1;
        } // i at 3
        // 2. find smallest number that's greater than arr[i]
        int j = arr.length - 1;
        while(j >= 0 && arr[j] <= arr[i]) { // when equal, should keep looking for GREATER one. 
            j--;
        } // 4 > 3, swap 3 and 4
        swap(arr, i, j); // --> 12354 --> 12453
        reverse(arr, i + 1); // reverse all segment after i. 124 35
        // return Integer.parseInt(new String(arr));
        try {
            return Integer.parseInt(new String(arr));
        } catch (Exception e) {
            return -1;
        }
    }
    private void reverse(char[] a, int start) {
        int i = start, j = a.length - 1;
        while (i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }
    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}