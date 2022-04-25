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
        Map<DirectedGraphNode, Integer> indegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            indegree.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        DirectedGraphNode root = get0IndegreeNode(indegree);
        while (root != null) {
            result.add(root);
            dfs(result, indegree, root);
            root = get0IndegreeNode(indegree);
        }
        return result;
    }
    DirectedGraphNode get0IndegreeNode(Map<DirectedGraphNode, Integer> indegree) {
        for (Map.Entry<DirectedGraphNode, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                return entry.getKey();
            }
        }
        return null;
    }
    void dfs(List<DirectedGraphNode> result, Map<DirectedGraphNode, Integer> indegree, DirectedGraphNode root) {
        indegree.put(root, -1);
        for (DirectedGraphNode neighbor : root.neighbors) {
            indegree.put(neighbor, indegree.get(neighbor) - 1);
            if (indegree.get(neighbor) == 0) {
                result.add(neighbor);
                dfs(result, indegree, neighbor);
            }
        }
    }
}
 