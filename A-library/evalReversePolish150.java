public class evalReversePolish150 {
    /*
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
        Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
        Note that division between two integers should truncate toward zero.
        It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

        Example 1:
        Input: tokens = ["2","1","+","3","*"]
        Output: 9
        Explanation: ((2 + 1) * 3) = 9

        Example 2:
        Input: tokens = ["4","13","5","/","+"]
        Output: 6
        Explanation: (4 + (13 / 5)) = 6

        Example 3:
        Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        Output: 22
        Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        = ((10 * (6 / (12 * -11))) + 17) + 5
        = ((10 * (6 / -132)) + 17) + 5
        = ((10 * 0) + 17) + 5
        = (0 + 17) + 5
        = 17 + 5
        = 22
     */
    class Solution {
        public int evalRPN(String[] tokens) {
            List<String> operators = Arrays.asList(new String[]{"+", "-", "*", "/"});//注意写法
            Stack<String> sk = new Stack<>();
            int res = 0;
            for(String token : tokens) {
                if (operators.contains(token)) {
                    if (sk.size() < 2) return -1;
                    int a = Integer.valueOf(sk.pop()), b = Integer.valueOf(sk.pop());
                    res = calculate(b, a, token);// order must be adjusted, otherwise 4-3 --> 3-4
                    System.out.println(res);
                    sk.push(String.valueOf(res));
                } else {
                    sk.push(token);
                }
            }
            return Integer.valueOf(sk.pop());
        }
        private int calculate(int a, int b, String operator) {
            if (operator.equals("+")) {
                return a+b;
            } else if (operator.equals("-")) {
                return a-b;
            } else if (operator.equals("*")) {
                return a*b;
            } else if (operator.equals("/")) {
                return a/b;
            } else {
                return 0;
            }
        }
    }
}
