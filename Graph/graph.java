// graph.png[https://github.com/HuiHuangSue/lc/blob/main/Graph/graph.png] &
// graph_DFS_BFS.png [https://github.com/HuiHuangSue/lc/blob/main/Graph/graph_DFS_BFS.png]
public class graph {
    public static void dfs(Graph graph, int start, boolean[] visited]) {
        System.out.println(graph.vertexes[start].data);
        visited[start] = true;
        for(int index : graph.adj[start]) {
            if(!visited[index]) {
                dfs(graph, index, visited);
            }
        }
    }
    public static void bfs(Graph, graph, int start, boolean visited, LinkedList<Integer> queue) {
        queue.offer(start);
        while(!queue.isEmpty()) {
            int front = queue.poll();
            if(visited[front]) continue;
            System.out.println(graph.vertexes[front].data);
            visited[front] = true;
            for(int index : graph.adj[front]) {
                queue.offer(index);
            }
        }
    }

    private static class Vertex {
        int data;
        Vertex(int data) {
            this.data = data;
        }
    }
    private static class Graph {
        private int size; // 初始化定点array的size
        private Vertex[] vertexes;
        private LinkedList<Integer>[] adj; // array of linkedlist

        Graph(int size) {
            this.size = size;
            vertexes = new Vertexes[size];
            adj = new LinkedList<Integer>(size);
            for(int i = 0; i < size; i++) {
                adj[i] = new LinkedList<Integer>();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.adj[0].add(1); graph.adj[0].add(2); graph.adj[0].add(3);
        graph.adj[1].add(0); graph.adj[1].add(3); graph.adj[1].add(4);
        graph.adj[2].add(0);
        graph.adj[3].add(0); graph.adj[3].add(1); graph.adj[3].add(4); graph.adj[3].add(4);
        graph.adj[4].add(1); graph.adj[4].add(3); graph.adj[4].add(5);
        graph.adj[5].add(3); graph.adj[5].add(4);
        System.out.println("DFS: ");
        dfs(graph, 0, new boolean[graph.size]);
        System.out.println("BFS: ");
        bfs(graph, 0, new boolean[graph.size], new LinkedList<Integer>());
    }
}
