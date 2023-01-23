class Solution {
    //用long相加。但如果超级长，java bigInteger, bigDecila底层是每9个数字相加
    // 因为整数范围是最长10位，所以最长9不会overflow，每9位相加
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1end = num1.length() -  1, n2end = num2.length() - 1;
        int carry = 0;
        while(n1end >= 0 || n2end >= 0) {
            int n1 = n1end >= 0 ? num1.charAt(n1end) - '0' : 0;
            int n2 = n2end >= 0 ? num2.charAt(n2end) - '0' : 0;
            int sum = carry + n1 + n2;
            sb.append(sum % 10);
            carry = sum / 10;
            n1end--; 
            n2end--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.length() > 0 ? sb.reverse().toString() : "0";
    }

    // num1 = "11", num2 = "123" --> "134"
    public String addStrings415(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while(i >= 0 || j >= 0) {
            int sum = carry;//sum should be refreshed
            if(i >= 0) {
                sum += (num1.charAt(i) - '0');
                i--;
            }
            if(j >= 0) {
                sum += (num2.charAt(j) - '0');
                j--;
            }
            sb.insert(0, (sum % 10));
            carry = sum/10;
        }
        if(carry > 0) {
            sb.insert(0, carry);
        }
        return sb.toString();//can keep appending & reverse at end
    }
}