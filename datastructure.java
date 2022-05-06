public List<Integer> findDuplicates442(int[] nums) {
    //negative: seen before; value: index
    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < nums.length; i++) {
        if (nums[Math.abs(nums[i]) - 1] < 0) {//could be negative. we only care value--index
            res.add(Math.abs(nums[i])); //add abs value
        }
        nums[Math.abs(nums[i])-1] *= -1;
    }
    return res;
}
public List<Integer> findDisappearedNumbers448(int[] nums) {
    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < nums.length; i++) {
        if (nums[Math.abs(nums[i]) - 1] > 0) {
            nums[Math.abs(nums[i]) - 1] *= (-1);
        }
    }
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] > 0) {
            res.add(i+1);
        }
    }
    return res;
}