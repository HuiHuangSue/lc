public class implementQByStack232 {
    /*
     * Implement a first in first out (FIFO) queue using only two stacks. 
     * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
        Implement the MyQueue class:

        void push(int x) Pushes element x to the back of the queue.
        int pop() Removes the element from the front of the queue and returns it.
        int peek() Returns the element at the front of the queue.
        boolean empty() Returns true if the queue is empty, false otherwise.

        Notes: You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 

        Example 1:
        Input
        ["MyQueue", "push", "push", "peek", "pop", "empty"]
        [[], [1], [2], [], [], []]
        Output
        [null, null, null, 1, 1, false]
        Explanation
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
     */


    class MyQueue {
        Stack<Integer> stIn, stOut;
    
        public MyQueue() {
            stIn = new Stack<>();
            stOut = new Stack<>();
        }
        
        /** Push element x to the back of queue. */
        void push(int x) {
            stIn.push(x);
        }
    
        /** Removes the element from in front of queue and returns that element. */
        int pop() {
            // 只有当stOut为空的时候，再从stIn里导入数据（导入stIn全部数据）
            if (stOut.empty()) {
                // 从stIn导入数据直到stIn为空
                while(!stIn.empty()) {
                    stOut.push(stIn.pop());
                }
            }
            int result = stOut.pop();
            return result;
        }
    
        /** Get the front element. */
        int peek() {
            int res = this.pop(); // 直接使用已有的pop函数
            stOut.push(res); // 因为pop函数弹出了元素res，所以再添加回去
            return res;
        }
    
        /** Returns whether the queue is empty. */
        boolean empty() {
            return stIn.empty() && stOut.empty();
        }
    }


    class MyQueue { // pretend s1 as the real queue. s2 as reversed queue
        Stack<Integer> s1, s2;
    
        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }
        
        /** Push element x to the back of queue. */
        void push(int x) {
            // transfer what s2 has to s1
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
            s1.push(x);
            
        }
    
        /** Removes the element from in front of queue and returns that element. */
        int pop() {
            // 1,2,3,4，sk will pop 4, but we want queue pop 1; move all sk1 to sk2 reversely
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            } // s2 becomes 4,3,2,1, then s2.pop() will pop 1 out. 
            return s2.pop();
        }
    
        /** Get the front element. */
        int peek() {
            int res = this.pop(); // 直接使用已有的pop函数
            s1.push(res); // 因为pop函数弹出了元素res，所以再添加回去s1, 不是自己的push
            return res;
        }
    
        /** Returns whether the queue is empty. */
        boolean empty() {
            return s1.empty() && s2.empty();
        }
    }
}
