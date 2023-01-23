class Solution {
    public String removeKdigits402(String num, int k) {
       Stack<Character> stack = new Stack<>();
        for(char digit : num.toCharArray()) {
            while(!stack.isEmpty() && k > 0 && stack.peek() > digit) { // = will fail 112. 只pop比她大的，多的之后还可以处理
                stack.pop();
                k -= 1;
            }
            stack.add(digit); // 每一轮都有加上去
            /* sk: sk.isEmpty, 1
                digit=4 !< 1 , sk: 1,4
                digit=3 < 4, pop4, sk: 1, then add 3; sk: 1,3
                digit=2 < 3, pop3, sk: 1, then add 2; sk: 1,2
                digit=2, !<2 add. sk: 1,2,2
                digit=1, < 2, pop2, sk: 1,2,1. k=0, add remaining -->1219
            */
            /** 112, k=1: if condition pops when =: sk:1; 1=1 pop1,add1, res becomes 12; but should be 11
             */
        }
            
        /* remove the remaining digits from the tail when k still > 0. */
        for(int i=0; i<k; ++i) { // 999, k=3 or 345,k=3
            stack.pop();
        }
            
        // build the final string, while removing the leading zeros.
        StringBuilder res = new StringBuilder();
        boolean leadingZero = true; // one time flag
        for(char digit: stack) { // stack 可以这样打印！！！
            if(leadingZero && digit == '0') continue;
            leadingZero = false;
            res.append(digit);
        }
            
        return res.length() > 0 ? res.toString() : "0";
    }
}