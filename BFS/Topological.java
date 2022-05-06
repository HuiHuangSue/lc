class DirectedGraphNode { //Definition
    int label;
    List<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
// https://www.lintcode.com/problem/127/description
public class TopologicalSortBFS {
    //map:每个节点的入度; queue: 0入度节点; result: 
   private HashMap<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();
   private Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
   private ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
   public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
       getInDegree(graph); //Calculate in-degree of every node and put it in hashmap.
       putIntoQueue(graph); // put 0-indegree node(s) to in queue
       searchAndPush(queue); // search nodes w/ 0 in-degree and keep push into Queue
       return result;
   }
   private void getInDegree(ArrayList<DirectedGraphNode> graph) {
       for (DirectedGraphNode vertex : graph) {
           for (DirectedGraphNode neighbor : vertex.neighbors) {
               map.put(neighbor, map.getOrDefault(neighbor,0)+1);
           }
       }
   }
   private void putIntoQueue(ArrayList<DirectedGraphNode> graph) {
       for (DirectedGraphNode vertex : graph) {
           if (map.containsKey(vertex)) continue;
           queue.offer(vertex);
           result.add(vertex);
       }
   }
   private void searchAndPush(Queue<DirectedGraphNode> q) {
       while (!queue.isEmpty()) {
           int size = queue.size();
           for (int i = 0; i != size; ++i) {
               DirectedGraphNode vertex = queue.poll();
               for (DirectedGraphNode neighbor : vertex.neighbors) {
                   map.put(neighbor, map.get(neighbor) - 1);
                   if (map.get(neighbor) == 0) {
                       queue.offer(neighbor);
                       result.add(neighbor);
                   }
               }
           }
       }
   }
}

public class TopologicalSortDFS {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
        
        int nodeCount = 0;
        for(DirectedGraphNode vertex : graph) {
            for(DirectedGraphNode neighbor : vertex.neighbors) {
                nodeCount += 1;
                map.put(neighbor, map.getOrDefault(neighbor, 0) + 1);
            }
        }
        
        boolean[] visited = new boolean[nodeCount];
        Stack<DirectedGraphNode> sk = new Stack<>();
        
        for(DirectedGraphNode vertex : graph) {
            if(!map.containsKey(vertex)) {
                dfsHelper(vertex, visited, sk);
            }
        }
        
        while(!sk.isEmpty()) {
            result.add(sk.pop());
        }
        return result;
    }
    
    private void dfsHelper(DirectedGraphNode vertex, boolean[] visited, Stack<DirectedGraphNode> sk) {
        visited[vertex.label] = true;
        for(DirectedGraphNode neighbor: vertex.neighbors) {
            if(visited[neighbor.label]) {
                continue;
            }
            dfsHelper(neighbor, visited, sk);
        }
        sk.push(vertex);//push to stack once completed
    }
}
 