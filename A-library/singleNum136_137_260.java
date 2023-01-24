class Solution {
    public int singleNumber136(int[] nums) {
        int res = 0;
        for(int n : nums) {
            res ^= n;
        }
        return res;
    }

    // >>> is unsigned-shift; it'll insert 0. >> is signed, and will extend the sign bit.
    public int[] singleNumber260(int[] nums) {
        int xorRes = 0;
        for(int n : nums) {
            xorRes ^= n;
        }
        if(xorRes == 0) return null;
        int separator = 1;
        while((separator & xorRes) == 0) { // 00000100100 find the first 1
            separator<<=1;
        }
        int[] res = new int[2];
        // group into 2
        for(int n : nums) {
            if ((separator & n) == 0) {
                res[0] ^= n;
            } else {
                res[1] ^= n;
            }
        }
        return res;
    }

    // all three times except 1
    public int singleNumber137(int[] nums) {
        // M1 hashmap
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int num : nums)
        hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);

        for (int k : hashmap.keySet())
        if (hashmap.get(k) == 1) return k;
        return -1;

        // M2: 3×(a+b+c)−(a+a+a+b+b+b+c)=2c
        Set<Long> set = new HashSet<>();
        long actualSum = 0;
        for(long n : nums) {
            set.add(n);
            actualSum += n;
        }
        long fullSum = 0;
        for(long s : set) {
            fullSum += s;
        }
        return (int)((fullSum * 3 - actualSum) / 2);

        // M3: bit
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            // first appearence: 
            // add num to seen_once 
            // don't add to seen_twice because of presence in seen_once

            // second appearance: 
            // remove num from seen_once 
            // add num to seen_twice

            // third appearance: 
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }
}