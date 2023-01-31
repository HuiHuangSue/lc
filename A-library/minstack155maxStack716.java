class MinStack155 {
    Stack<Integer> mainSk, minSk;

    public MinStack() {
        mainSk = new Stack<>();
        minSk = new Stack<>();
    }

    public void push(int val) {
         //needs to be <=, otherwise miss pushing adding second 0 of 0,1,0 to minsk. when pop first
         // mainSk: 0,1,0
         // minSk:  0,0
         // pop(), if no val <=, then 0,1,0 min 0; after popping 0, no minSk though main has 0,1 left
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

class MaxStack716Heap {
    private Stack<int[]> stack;
    private Queue<int[]> heap;
    private Set<Integer> removed;
    private int cnt;

    public MaxStack() {
        stack = new Stack<>();
        heap = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
        removed = new HashSet<>();
    }

    public void push(int x) {
        stack.add(new int[] { x, cnt });
        heap.add(new int[] { x, cnt });
        cnt++;
    }

    public int pop() {
        while (removed.contains(stack.peek()[1])) {
            stack.pop();
        }
        int[] top = stack.pop();
        removed.add(top[1]);
        return top[0];
    }

    public int top() {
        while (removed.contains(stack.peek()[1])) {
            stack.pop();
        }
        return stack.peek()[0];
    }

    public int peekMax() {
        while (removed.contains(heap.peek()[1])) {
            heap.poll();
        }
        return heap.peek()[0];

    }

    public int popMax() {
        while (removed.contains(heap.peek()[1])) {
            heap.poll();
        }
        int[] top = heap.poll();
        removed.add(top[1]);
        return top[0];
    }
}

class MaxStack716TimeExceeded {
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
