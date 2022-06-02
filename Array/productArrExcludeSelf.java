public int[] productExceptSelf238(int[] nums) {
    if (nums.length <= 1) return nums;
    // M1: prefixArr and postfixArr
    // Res(nums[i]) = predix[i-1] * postfix[i+1]
    
    /*
        2          3         4           5
L2R     1          1*2      1*2*3=6      1*2*3*4=24
R2L post=1
                                          1*24
                             post=1*5
                             6*post=30      1*24
                post=1*5*4=20
                2*post=40
     post=1*5*4*3=60
     1*post=60
    */
    int[] res = new int[nums.length];
    int prefix = 1;
    res[0] = 1;
    for(int i = 1; i < nums.length; i++) {
        prefix *= nums[i - 1];
        res[i] = prefix;
    }
    
    int post = 1;
    for(int i = nums.length - 2; i >= 0; i--) {
        post *= nums[i + 1];
        res[i] *= post;
    }
    return res;
}