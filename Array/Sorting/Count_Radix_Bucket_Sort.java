package Sorting;

import java.util.Collection;

public class Count_Radix_Bucket_Sort {
    // 一定要整数，且max-min的值不能太宽和稀疏
    public int[] countingSort(int[] arr) {
        // get max value and set array length
        // To OPTIMIZE
        int max = arr[0];
        for(int a : arr) max = Math.max(max, a);
        int[] countArr = new int[max + 1];
        // Traverse and Fill
        for(int a : arr) {
            countArr[a]++;
        }
        // stats and present
        int[] res = new int[arr.length];
        int index = 0;
        for(int i = 0; i < countArr.length; i++) { //遍利每个数
            for(int c = 0; c < countArr[i]; c++) {
                res[index++] = i; // 数字打印c次
            }
        }
        return res;
    }

    public int[] countingSort_stable(int[] arr) {
        int max = arr[0], min=arr[0];
        for(int a : arr) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int d = max - min;
        int[] countArr = new int[d + 1];
        for(int a : arr) {
            countArr[a - min]++;
        }
        // 变形
        for(int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1];
        }
        // 倒序遍历原始数组
        int res = new int[arr.length];
        for(int i = arr.length - 1; i>=0;i--) {
            res[countArr[arr[i] - min] - 1] = arr[i];// (95分-90)的index, 在stats arr里的值是4，也就要放新的index3
            countArr[arr[i] - min]--; // 把值-1 （下一次同数值的就是第4-1名
        }
        return res;
    }


    public static double[] bucketSort(double[] arr) {
        int max = arr[0], min=arr[0];
        for(int a : arr) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int d = max - min;
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>();
        for(int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }

        for(int num : arr) {
            int index = (int)((num - min) * (bucketNum - 1) / d);
            bucketList.get(index).add(num);
        }
        for(int i = 0; i < bucketList.size(); i++) {
            Collection.sort(bucketList.get(i)); // JDK optimized using mergeSort
        }

        int index = 0;
        double[] res = new double[arr.length];
        for(LinkedList<Double> list : bucketList){
            for(double num : list) {
                res[index++] = num;
            }
        }
        return res;
    }

    // phone numbers, characters
    // If different length, use 0 append to tail, treat as value < 'a'
    public static final int ASCII_RANGE = 128;
    public static String[] radixSort(String[] arr, int maxLen) {
        String[] sortedArr = new String[arr.length]; // tmp sortedArr each round
        for(int k = maxLen - 1; k >= 0; k--){
            int[] count = new int[ASCII_RANGE];
            for(int i = 0; i < arr.length; i++) {
                int index = getCharIndex(arr[i], k);
                count[index]++;
            }
            for(int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i-1];
            }
            for(int i = arr.length - 1; i >= 0; i--) {
                int index = getCharIndex(arr[i], k);
                int sortedIndex = count[index] - 1;
                sortedArr[sortedIndex] = arr[i];
                count[index]--;
            }
            arr = sortedArr.clone(); // 下一轮以上一轮做基础;
        }
        return arr;
    }
    private static int getCharIndex(String str, int k) {
        if(str.length() < (k+1)) return 0;
        return str.charAt(k);
    }
}
