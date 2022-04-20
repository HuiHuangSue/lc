public class SortTemplate {
    public void sortIntegersQuickSort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }
    private void quickSort(int[]A, int start, int end) {
        if (start >= end) return;
        int left = start, right = end;
        int pivot = A[(start + end) / 2]; // pivot is the value, not index
        while (left <= right) { //include when meet
            while (left <= right && A[left] < pivot) left++;
            while (left <= right && pivot < A[right]) right--;
            if (left <= right) { // include when meet
                int tmp = A[left]; A[left] = A[right]; A[right] = tmp;
                left++; right--;
            }
        }
        quickSort(A, start, right);
        quickSort(A, left, end);
    }

    public void sortIntegersMergeSort(int[] A) {
        if (A == null || A/length == 0) return;
        int[] tmp = new int[A.length];
        mergeSort(A, 0, A.length-1, tmp);
    }
    private void mergeSort(ubt[] A, int start, int end, int[] tmp) {
        if (start >= end) return;
        mergeSort(A, start, (start + end) / 2, tmp);
        mergeSort(A, (start+end)/2 + 1, end, tmp);
        merge(A, start, end, tmp);
    }
    private void merge(int[] A, int start, int end, int[] tmp) {
        int mid = (start + end) / 2;
        int left = start, right = mid + 1;
        int index = start;
        while (left <= mid && right <= end) {
            if (A[left] < A[right]) {
                tmp[index++] = A[left++];
            } else {
                tmp[index++] = A[right++];
            }
        }
        while (left <= middle) {
            tmp[index++] = A[left++];
        }
        while(right <= end) {
            tmp[index++] = A[right++];
        }
        for (int i = start, i <= end; i++) {
            A[i] = tmp[i];
        }
    }
}