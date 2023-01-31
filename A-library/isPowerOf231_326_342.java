public boolean isPowerOfTwo231(int n) {
    // keep dividing by 2 or shifting right >> 1, and see if eventually 1. 
    return (n > 0) && (n & (n-1)) == 0; // n > 0, cannot be negative nor 0
}

public boolean isPowerOfThree326(int n) {
    // M1 keep dividing
    if (n < 1) return false;
    while (n % 3 == 0){
        n /= 3;
    }
    return n == 1;

    // M2. find max limit of 3^x=1162261467 is. Watch type conversion
    long maxInt = (long)Math.pow(2, 32) / 2 - 1; // max, half negative, remove 0, then positive nums
    int limit = (int)(Math.log(maxInt) / Math.log(3));
    int powLimit = (int)Math.pow(3, limit); // 1162261467
    return (n > 0) && (powLimit % n == 0); // 3^19 is the max within limit

    // M3,convert to base 3, then check regular expression
    // eg Integer.toString(5, 2) == "101" and Integer.toString(5, 3) == "12".
    // starts with 1 ^1, is followed by zero or more 0s 0* and contains nothing else ＄. 10000
    return Integer.toString(n, 3).matches("^10*$");
}

public boolean isPowerOfFour342(int n) {
    // Convert to base 4, then match 10start, with 000...
    return Integer.toString(n, 4).matches("^10*$");

    // M1: keep dividing
    if (n < 1) return false; // 4^0=1
    while (n % 4 == 0) {
        n /= 4;
    }
    return n == 1;

    // M2 powerOf2 and % 3 == 1
    return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1); // avoid 8

    // M3: precompute, then check if contains O(1)
}