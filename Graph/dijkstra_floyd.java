// from 1 vertex to all other vertex
public class dijkstra_floyd {

    public class dijkstra {
        // n vertexes, e edges; O(n*(n-1)) --> O(n^2)
        // To improve, can use minHeap to get the minimum, --> O(e * logn)
        public static int[] dijkstra(Graph graph, int startIndex) {
            // 图的vertex数量
            int size = graph.vertexes.length;
            // 创建距离表
            int[] distances = new int[size];
            for(int i = 1; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
            }
            boolean[] traversed = new boolean[size];
            traversed[0] = true; // mark start point as traversed;
            List<Edge> edgestFromStart = graph.adj[startIndex]; // A's edges
            for(Edge edge : edges) {
                distances[edge.index] = edge.weight; // assign A's neighbor distances; B=5,C=2;
            }
            for(int i = 1; i < size; i++) { // go through B,C,D,E,F,G
                int minDistanceFromStart = Integer.MAX_VALUE;
                int minDistanceIndex = -1;
                for(int j = 1; j < size; j++) { //
                    if(!traversed[j] && (distances[j] < minDistanceFromStart)) {
                        minDistanceFromStart = distances[j];
                        minDistanceIndex = j;
                    }
                }
                if (minDistanceIndex == -1) break;
                traversed[minDistanceIndex] = true; // mark as traveresd
                for(Edge edge : graph.adj[minDistanceIndex]) {
                    if(traversed[edge.index]) continue; // skip traversed
                    int weight = edge.weight;
                    int preDistance = distances[edge.index];
                    if((weight != Integer.MAX_VALUE) && (minDistanceFromStart + weight) < preDistance) { //
                        distances[edge.index] = minDistanceFromStart + weight;
                    }
                }
                return distances;
            }
        }
    }


    // complete path
    public static int[] dijkstraCompletePath(Graph graph, int startIndex) {
        // 图的vertex数量
        int size = graph.vertexes.length;
        // 创建距离表
        int[] distances = new int[size];
        for(int i = 1; i < size; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        int[] prevs = new int[size]; // RECORD
        boolean[] traversed = new boolean[size];
        traversed[0] = true; // mark start point as traversed;
        List<Edge> edgestFromStart = graph.adj[startIndex]; // A's edges
        for(Edge edge : edges) {
            distances[edge.index] = edge.weight; // assign A's neighbor distances; B=5,C=2;
            prevs[edge.index] = 0; // cannot omit as A may not the starting point
        }
        for(int i = 1; i < size; i++) { // go through B,C,D,E,F,G
            int minDistanceFromStart = Integer.MAX_VALUE;
            int minDistanceIndex = -1;
            for(int j = 1; j < size; j++) { //
                if(!traversed[j] && (distances[j] < minDistanceFromStart)) {
                    minDistanceFromStart = distances[j];
                    minDistanceIndex = j;
                }
            }
            if (minDistanceIndex == -1) break;
            traversed[minDistanceIndex] = true; // mark as traveresd
            for(Edge edge : graph.adj[minDistanceIndex]) {
                if(traversed[edge.index]) continue; // skip traversed
                int weight = edge.weight;
                int preDistance = distances[edge.index];
                if((weight != Integer.MAX_VALUE) && (minDistanceFromStart + weight) < preDistance) { //
                    distances[edge.index] = minDistanceFromStart + weight;
                    prevs[edge.index] = minDistanceIndex;
                }
            }
            return prevs;
        }
    }
}


public class Floyd {
    // if using Dijkstra for every vertex, it'll be O(n^3) (or O(n^2(logn)))
    final static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[][] matrix = {
            {0,     5,   2, INF, INF, INF, INF},
            {5,     0, INF,   1,   6, INF, INF},
            {2,   INF,   0,   6, INF,   8, INF},
            {INF,   1,   6,   0,   1,   2, INF},
            {INF,   6, INF,   1,   0, INF,   7},
            {INF, INF,   8,   2, INF,   0,   3}
            {INF, INF, INF, INF,   7,   3,   0}
        };
    }
    public static void floyd(int[][] matrix) {
        for(int k = 0; k < matrix.length; k++) { // k is index of each A,B,C,D,E,F,G
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix.length; j++) {
                    // K as interconnected vertex, [ik]-->[kj] means from i to k, k to j...
                    // Integer.MAX_VALUE 代表还不通
                    if(matrix[i][k] == INF || matrix[k][j] ==INF) continue;
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
    }

}