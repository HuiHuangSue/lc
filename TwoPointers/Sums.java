public class HashMapSet {
    public int[] twoSum1(int[] nums, int target) {
        // ex: [2,11,7,9], t = 9
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};//new int[]{e1, e2}
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    public boolean containsDuplicate217(int[] nums) {
        boolean res = false;
       Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
       return false;
   }
}