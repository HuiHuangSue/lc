class MinStack155 {
    Stack<Integer> mainSk, minSk;

    public MinStack() {
        mainSk = new Stack<>();
        minSk = new Stack<>();
    }

    public void push(int val) {
         //needs to be <=, otherwise miss pushing adding second 0 of 0,1,0 to minsk. when pop first
        mainSk.push(val);
        if (minSk.isEmpty() || val <= minSk.peek()) {
            minSk.push(val);
        }
    }

    public void pop() {
        if (!mainSk.isEmpty()) {
            if (minSk.peek().equals(mainSk.peek())) { // has to use equals check for Integer
                minSk.pop();
            }
            mainSk.pop();
        }

    }

    public int top() {
        return mainSk.peek();
    }

    public int getMin() {
        return minSk.peek();
    }
}

class MaxStack716 {
    Stack<Integer> mainSk, maxSk;
    public MaxStack() {
        mainSk = new Stack<>();
        maxSk = new Stack<>();
    }

    public void push(int x) {
        int tmpMax = maxSk.isEmpty() ? Integer.MIN_VALUE : maxSk.peek();
        if (x >= tmpMax) {
            tmpMax = x;
        }
        mainSk.push(x);
        maxSk.push(tmpMax);
    }

    public int pop() {
        maxSk.pop();
        return mainSk.pop();
    }

    public int top() {
        return mainSk.peek();
    }

    public int peekMax() {
        return maxSk.peek();
    }

    public int popMax() {
        int curMax = maxSk.peek();
        Stack<Integer> sk = new Stack<>();
        while(!mainSk.peek().equals(curMax)) {
            sk.push(mainSk.pop());
            maxSk.pop();//keep updating maxSk
        }
        mainSk.pop();
        maxSk.pop();
        while(!sk.isEmpty()) {
            push(sk.pop());
        }
        return curMax;
    }
}
