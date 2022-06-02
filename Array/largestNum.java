public String largestNumber179(int[] nums) {
    // convert int to string
    // sort w/ customized comparator, s1=9,s2=31-->931 vs 319, put s1 in front of s2. 
    // compareTo: s1 > s2 --> +; s1==s2-->0; s1<s2 --> < 0; 需要从大到小排
    if (nums == null || nums.length == 0) return "";
    String[] arr = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
        arr[i] = String.valueOf(nums[i]);
    }
    // Above can be replaced by
    // String[] arr = Arrays.stream(num).mapToObj(String::valueOf).toArray(String[]::new);
    Arrays.sort(arr, (String s1, String s2) -> (s2+s1).compareTo(s1+s2));
    // below can be replaced by return Arrays.stream(array).reduce((x, y) -> x.equals("0") ? y : x + y).get();
    if (arr[0].charAt(0) == '0') return "0"; //[0,0,0] not 000
    StringBuilder sb = new StringBuilder();
    for(String str : arr) {
        sb.append(str);
    }
    return sb.toString();
}