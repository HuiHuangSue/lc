package Sorting;

public class bubble_cocktail {
    public static void bubbleSort(int[] arr) {
        int lastIndex = 0, sortedBorder = arr.length - 1; // 优化尾巴
        for(int i = 0; i < arr.length - 1; i++) { // comparing using 2, so stops 1 index ahead
            boolean isSorted = true; // 标记有序，优化前面已经sort好的
            // for(int j = 0; j < arr.length -1 - i; j++) { // len-1 - i, i是已经处理过的，=最后排好的max的数量
            for(int j = 0; j < sortedBorder; i++) {
                if(arr[j] > arr[j+1]) {//swap
                    int tmp = arr[j]; arr[j] = arr[j+1]; arr[j+1] = tmp;
                    isSorted = false;
                    lastIndex = j;
                }
            }
            if(isSorted) { // did not swap
                sortedBorder = lastIndex;
                break;
            }
        }
    }

    // when most numbers are already sorted
    public static void cocktailSort(int[] arr){
        int tmp = 0;
        for(int i = 0; i < arr.len/2; i++) {
            boolean isSorted = true;
            // ODD: Left --> Right
            for(int j = i; j < arr.length -1 - i; j++) {
                if(arr[j] > arr[j+1]) swap(arr, j, j+1);
                isSorted = false;
            }
            if(isSorted) break;

            // EVEN: right --> left
            isSorted = true;
            for(int j = arr.len - 1 - i; j > i; j--) {
                if(arr[j] > arr[j-1]) swap(arr, j, j-1);
                isSorted = false;
            }
            if(isSorted) break;
        }
    }

    // unstable; everytime find minimum, then swap left
    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) { // 在倒数第二个停
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] > arr[minIndex]) {
                    minIndex = j;
                }
            }
            if(i != minIndex) swap(arr[i], arr[minIndex]);
        }
    }

    // 1,2,3,4,...., n-1 times
    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int j = i - 1;
            for(; j >= 0 && insertVal < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j+1] = insertVal;
        }
    }
}
