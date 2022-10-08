public class LintcodeBasics {
    // 0 < num < 1000
    public int reverseInteger(int num) {
        int res = 0, sign = num > 0 ? 1 : -1;
        while (num > 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res * sign;

        StringBuilder sb = new StringBuilder(String.valueOf(num));
        return Integer.valueOf(sb.reverse().toString());
    }
}
