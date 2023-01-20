public class greatestCommonDivisorgcd1979 {
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(i, max);
        }
        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        // Method 1: gcd(a,b) = gcd(b,a%b), stop at b==0
        if (b == 0) return a;
        return gcd(b, a % b); 

        // Method 2 & 3
        int big = a > b ? a : b;
        int small = a < b ? a : b;

        // Method 2: gcd(a,b) = gcd(big %small, small); mod heavy when a,b big numbers, avg O(log(max[a,b]))
        if (big % small == 0) {
            return small;
        }
        return gcd(big % small, small);

        // Method 3: gcd(a,b) = gcd(big-small, small); when a,b far apart, 10001,1; worstO(max[a,b])
        if (big - small == 0){
            return small;
        }
        return gcd(big - small, small);

        // Method 4
        /* QuickestO(log(max[a,b]))
        a,b both even: gcd(a,b) = 2*gcd(a/2, b/2) = 2 * gcd(a >> 1, b >> 1). must multiply 2 back!!
        a even, b odd: gcd(a,b) = gcd(a/2, b) = gcd(a >> 1, b)
        a odd, b even;          = gcd(a, b/2) = gcd(a, b >> 1)
        both odd, substract, then both even: = gcd(big-small, small), then recursive
        */
        if( (a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1)<<1; // must multiply 2 back, otherwise gcd 8 became 4..
        } else if ((a & 1) == 0) {
            return gcd(a >> 1, b);
        } else if((b & 1) == 0) {
            return gcd(a, b >> 1);
        } else {
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            if (big == small) return small;
            return gcd(big - small, small);
        }
    }
}
