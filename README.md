big n: O(1) < O(logn) < O(n) < O(nlogn) < O(mn) < O(n^2) < O(n^3) < O(2^n) < O(n!)
增删查改

Array: heavy on read, less on write
 - Update/Add O(1); 查改
 - Insert 增 n
   - (head1, middle n; expand 2*size `System.arraycopy(srcarr, 0, destarr, 0, srcarr.length);` 
 - Delete 删 n
   - move leftwards
   - swap last element with the deleted index if order does not matter

LinkedList: heavy on write, less on reads
  - single / double (prev, data, next)
  - Update/Add O(n/1) then O(1)
  - Insert/Delete O(1)
    - head/Middle: new node next->originalHead, head=new head; tail O(1)
 
 Stack (push, pop, dish stack, O(1) 
   - implemet recursion, webpage navigation of back button
 
 Queue (enqueue, dequeue, craw URL
   - if (rear+1)%arrlen == front --> full; else arr[rear]=target; rear = rear + 1)%arrlen
   - if (rear == front) --> empty!; ele = arr[front]; front=(front+1）%arrlen
 
 Deque
 
 Priority Queue

HashMap
  - colliion, resize (capaity * load factor-default0.75)

Tree (BST)
  - root, leaf, parent, child, siblings
  - full Binary Tree vs Complete(往左靠满) Binary Tree 
  - index: left child = parent * 2 + 1; right child = parent * 2 + 2
  - balance: AVL, Red-black, Treap(BST+Heap)
  - Traverse: DFS (root优先): Pre, In, Post; BFS: Level

