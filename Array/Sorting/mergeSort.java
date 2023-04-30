package Sorting;

public class mergeSort {
    public static void mergeSort(int[] arr, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }
    private static void merge(int[] arr, int start, int mid, int end){
        int[] tmpArr = new int[end - start + 1];
        int p1 = start, p2 = mid + 1, p = 0;
        while(p1 <= mid && p2 <= end) {
            if(arr[p1] <= arr[p2]) {
                tmpArr[p++] = arr[p1++];
            } else{
                tmpArr[p++] = arr[p2++];
            }
        }
        while(p1 <= mid) tmpArr[p++] = arr[p1++];
        while(p2 <= end) tmpArr[p++] = arr[p2++];
        for(int i = 0; i < tmpArr.length; i++) {
            arr[i + start] = tmpArr[i];
        }
    }
}
