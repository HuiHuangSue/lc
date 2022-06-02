public void sortColors75(int[] nums) {
    /*
    双指针, first, last，和current index
    第一个pointer到0的末端，last在2的前一个，current index在first和last之间check
    current: 1,继续找，increment index
    current: 0, 和前面的swap，first和current都increment
    current: 2: 和last swap, 先不shift index, 因为不知道是0还是1，下一轮继续check; last--
    */
    // https://www.youtube.com/watch?v=J9DgvL6L1nk&ab_channel=basketwangCoding
    int first = 0, last = nums.length - 1;
    while(first < nums.length && nums[first] == 0) {
        first++;
    }
    while(last >= 0 && nums[last] == 2) {
        last--;
    }
    int current = first;
    while(current <= last) {
        if (nums[current] == 1) {
            current++;
        } else if (nums[current] == 0) { // 还是可能0，比如[0,0,1,0,2], 4th 0
            int tmp = nums[current];nums[current] = nums[first]; nums[first] = tmp;
            first++;
            current++;
        } else if(nums[current] == 2) {
            int tmp = nums[current];nums[current] = nums[last]; nums[last] = tmp;
            last--; // last--
        }
    }
    
    // two pass
    // int[] count = new int[3];
    // for (int n : nums) {
    //     int position = n % 3;
    //     count[position]++;
    // }
    // for (int i = 0; i < count[0]; i++) {
    //     nums[i] = 0;
    // }
    // for (int i = count[0]; i < count[0] + count[1]; i++) {
    //     nums[i] = 1;
    // }
    // for (int i = count[0] + count[1]; i < nums.length; i++) {
    //     nums[i] = 2;
    // }
}