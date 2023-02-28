import java.util.Arrays;

public class heap_PriorityQueue {
    class heap {
        public static void bubbleUp(int[] arr) {
            int childIndex = arr.length - 1; // 加在最后一个，往上bubble
            int parentIndex = (childIndex - 1)/ 2;
            int tmp = array[childIndex]; //保存要插入的值，用于最后赋值，省去不断swap
            while(childIndex > 0 && tmp < arr[parentIndex]) { //值比parent小，就不停bubble up
                // 不需要真正交换，单向赋值就可
                arr[childIndex] = arr[parentIndex];
                childIndex = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
            arr[childIndex] = tmp;
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

        public static void buildHeap(int[] arr) {
            // 从最后一个非leaf开始
            for(int i = (arr.length - 2)/2; i>=0; i--){ //非leaf，parent of arr[len-1] is ((len-1)-1)/2
                bubbleDown(arr, arr[i], arr.length);
            }
        }
        public static void main(String[] args){
            int[] arr = new int[]{1,3,2,6,5,7,8,9,10,0};
            bubbleUp(arr);
            System.out.println(arr.toString(arr));

            arr = new int[]{7,1,3,10,5,2,8,9,6};
            buildHeap(arr);
            System.out.println(arr.toString(arr));
        }
    }

    class PriorityQueue {
        private int[] arr;
        private int size;
        public PriorityQueue() {
            arr = new int[32];
        }
        public void enQueue(int key) {
            // 扩容
            if(size >= arr.length) resize();
            arr[size++] = key;
            bubbleUp();
        }
        public int deQueue() throws Exception {
            if(size <= 0) throw new Exception("Empty Queue");
            int top = arr[0];
            arr[0] = arr[--size];
            bubbleDown();
            return top;
        }

        private void resize() {
            int newSize = this.size * 2;
            this.arr = Arrays.copyOf(this.arr, newSize);
        }

        public static void bubbleUp() {
            int childIndex = size - 1; // 加在最后一个，往上bubble
            int parentIndex = (childIndex - 1)/ 2;
            int tmp = array[childIndex];
            while(childIndex > 0 && tmp < arr[parentIndex]) {
                arr[childIndex] = arr[parentIndex];
                childIndex = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
            arr[childIndex] = tmp;
        }

        public static void main(String[] args) throws Exception {
            ...
        }

        public static void bubbleDown() {
            // tmp 用于最后赋值
            int parentIndex = 0, tmp = arr[parentIndex];
            int childIndex = 1; // parentIndex=0 * 2 + 1;
            while(childIndex < size) {
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

}