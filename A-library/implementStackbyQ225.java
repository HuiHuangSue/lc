public class implementStackbyQ225 {
    class MyStack {

        Queue<Integer> qq;
    
        public MyStack() {
            qq = new LinkedList<>();
        }
        
        public void push(int x) {
            qq.add(x); 
            // 1
            // 1,2 --> remove() removes 1, then add at the end --> 2,1
            // 2,1,3 --> remove() removes 2, at the end --> 1,3,2 --> 3,2,1. 
            for(int i = 1; i < qq.size(); i++) { //Notice i starts from 1, for n-1 times, as 1 num stays
                qq.add(qq.remove());
            }
        }
        
        public int pop() {
            return qq.remove(); // remove head of the linkedlist; peekFirst(), peekLast() 
        }
        
        public int top() {
            return qq.peek();
        }
        
        public boolean empty() {
            return qq.isEmpty();
        }
    }
}
