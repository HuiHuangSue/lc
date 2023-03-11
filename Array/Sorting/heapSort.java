package Sorting;

public class heapSort {
public static void heapSort(int[] arr) {
    // build unsorted array into maxHeap
    for(int i = arr.length - 2; i >= 0; i--){
        bubbleDown(arr, i, arr.length);
    }
    // 循环删除堆顶，moveToTail, 调整产生新的堆顶
    for(int i = arr.length - 1; i> 0;i--){
        swap(arr, arr[i], arr[0]);//最后一个和第一个交换
        bubbleDown(arr, 0, i); // 下沉调整
    }
}

    public static void bubbleDown(int[] arr, int parentIndex, int len) {
        // tmp 用于最后赋值
        int tmp = arr[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while(childIndex < len) {
            if(childIndex + 1 < len && arr[childIndex + 1] < arr[childIndex]) {
                childIndex++; // 右边孩子更小，把目标换成右边孩子
            }
            if(tmp <= arr[childIndex]) break; // 不需要动
            // 开始换
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        arr[parentIndex] = tmp;
    }
}
