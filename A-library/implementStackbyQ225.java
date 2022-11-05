public class implementStackbyQ225 {
    class MyStack {

        Queue<Integer> qq;
    
        public MyStack() {
            qq = new LinkedList<>();
        }
        
        public void push(int x) {
            qq.add(x); 
            // 1
            // 1,2 --> 2,1
            // 2,1,3 --> 1,3,2 --> 3,2,1. Notice i starts from 1, as 1 num added
            for(int i = 1; i < qq.size(); i++) {
                qq.add(qq.remove());
            }
        }
        
        public int pop() {
            return qq.remove();
        }
        
        public int top() {
            return qq.peek();
        }
        
        public boolean empty() {
            return qq.isEmpty();
        }
    }
}
