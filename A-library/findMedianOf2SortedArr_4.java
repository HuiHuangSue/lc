public class findMedianOf2SortedArr_4 {
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int[] merged = mergeSorted88(nums1, nums2);
        System.out.println(Arrays.toString(merged));
        int len = nums1.length + nums2.length;
        if((len & 1) == 1) return (double)merged[len / 2]; // odd [1,2,3,4,5]
        else return (double)((merged[len/2-1] + merged[len/2]))/2; //len even; 6 lements[1,2,3,4,5,6], nums(2+3)/2
    }
    public int[] mergeSorted88(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int index1 = nums1.length - 1, index2 = nums2.length-1, index = nums1.length + nums2.length - 1;
        while(index1 >= 0 && index2 >= 0) {
            // 只有在确定大的那个，才indexI--
            res[index--] = (nums1[index1] > nums2[index2] ? nums1[index1--] : nums2[index2--]);
        }
        while(index1 >= 0) {
            res[index--] = nums1[index1--];
        }
        while(index2 >= 0) {
            res[index--] = nums2[index2--];
        }
        return res;
    }
}
