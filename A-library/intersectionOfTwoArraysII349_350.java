public class intersectionOfTwoArraysII349_350 {
    /* 
    Given two integer arrays nums1 and nums2, return an array of their intersection. 
    Each element in the result must be unique and you may return the result in any order.
    Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2] [2]--->
    Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4] ---> Output: [9,4]
    Explanation: [4,9] is also accepted.
    */

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                resSet.add(i);
            }
        }
        return resSet.stream().mapToInt(x->x).toArray();// set to array
    }

    public int[] intersection349SortedTwoPointers(int[] nums1, int[] nums2) {
        // assume sorted, O(1) space, use pointers
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        Set<Integer> res = new HashSet<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                res.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[res.size()];
        int k = 0;
        for (Integer num : res) {
            result[k++] = num;
        }
        return result;
    }

    /* Given two integer arrays nums1 and nums2, return an array of their intersection. 
    Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

    Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2,2]
    Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [4,9]
    Explanation: [9,4] is also accepted.
    */
    public int[] intersect350(int[] nums1, int[] nums2) {
        // if already sorted, use 2 pointers, O(n), and O(1) space.
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int n : nums1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {// otherwise get fail on non-intersection
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        return list.stream().mapToInt(x->x).toArray();// set to array
    }
}
