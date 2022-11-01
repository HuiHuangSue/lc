public class happyNumber202 {
    /*
     * Write an algorithm to determine if a number n is happy.
        A happy number is a number defined by the following process:
        Starting with any positive integer, replace the number by the sum of the squares of its digits.
        Repeat the process until the number equals 1 (where it will stay), 
        or it loops endlessly in a cycle which does not include 1.
        Those numbers for which this process ends in 1 are happy.
        Return true if n is a happy number, and false if not.

        Example 1:
        Input: n = 19
        Output: true
        Explanation:
        12 + 92 = 82
        82 + 22 = 68
        62 + 82 = 100
        12 + 02 + 02 = 1
        Example 2:

        Input: n = 2
        Output: false
     */
    public boolean isHappy(int n) {
        // 4: 16->37->58->89->145->42->20->4 loop
        if (n == 1) return true;
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) { // 条件是不重复
            set.add(n); //前面查过不重复了，先加上n
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (sum == 1) {
                return true;
            }
            if (set.contains(sum)) {
                return false;
            }
            n = sum; // re-assign n with sum
        }
        
        return true;
    }
}
