public int[] intersectionOf2Arrays349(int[] nums1, int[] nums2) {
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
    
    
    /* O(n) space */
    Set<Integer> set = new HashSet<>();
    Set<Integer> set2 = new HashSet<>(); 
    // [1,2,2,1], [2,2]; second needs to be set as well otherwise set2 duplicates
    for (int n : nums1) {
        set.add(n);
    }
    for(int n : nums2) {
        set2.add(n);
    }
    set.retainAll(set2);
    int[] res = new int[set.size()];
    int i = 0;
    for(int n : set) {
        res[i++] = n;
    }
    return res;
}

public int[] intersectOfTwoArraysII350(int[] nums1, int[] nums2) {
    // if already sorted, use 2 pointers, O(n) time and O(1) space
    // otherwise, use hashmap
    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int n : nums1) {
        map.put(n, map.getOrDefault(n, 0) + 1);
    }
    for (int n : nums2) {
        if (map.containsKey(n) && map.get(n) >= 1) {
            list.add(n);
            map.put(n, map.get(n)-1);
        }
    }
    int[] result = new int[list.size()];
    int i = 0;
    for(int n : list) {
        result[i++] = n;
    }
    return result;
}

public List<Integer> tersectionOfThreeSortedArrays1213(int[] nums1, int[] nums2, int[] nums3) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    Arrays.sort(nums3);
    int i = 0, j = 0, k = 0;
    List<Integer> res = new ArrayList<>();
    while (i < nums1.length && j < nums2.length && k < nums3.length) {
         if (nums1[i] == nums2[j] && nums2[j] == nums3[k]) {
            res.add(nums1[i]);
            i++;
            j++;
            k++;
         } else {
             if (nums1[i] < nums2[j]) {
                 i++;
             } else if (nums2[j] < nums3[k]) {
                 j++;
             } else {
                 k++;
             }
         }
    }
    return res;
}