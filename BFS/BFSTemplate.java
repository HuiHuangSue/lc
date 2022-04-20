public class BFSTemplate {
    // Topological sort (100%), level order traversal, shortest path in graph, DFS keyword. 
    // Given a rule, what’s the minimum steps required from initial state to ending state.
    // O(n + m) 点数+边数; – O(n)
    ReturnType bfs(Node startNode) {
        Queue<Node> queue = new ArrayDeque<>();
        // hashmap 有两个作用，一个是记录一个点是否被丢进过队列了，避免重复访问, 另外一个是记录 startNode 到其他所有节点的最短距离, 
        // 如果只求连通性的话，可以换成 HashSet 就行; node 做 key 的时候比较的是内存地址
        Map<Node, Integer> distance = new HAshMap<>();
        // 把起点放进队列和Hashtable里，如果有多个起点，都放进去
        queue.offer(startNode);
        distance.push(startNode, 0); // or push 1 if that's the scenario
        // while 队列不空，不停的从队列里拿出一个点，拓展邻居节点放到队列中
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            // 如果有明确的终点可以在这里加终点的判断
            if (node is 终点) {
                break; // or return sth
            }
            for (Node neighbor : node.getNeighbors()) {
                if (distance.containsKey(neighbor)) continue;
                queue.offer(nrighbor);
                distance.put(neighbor, distance.get(node) + 1);
            }
        }
        // 如果需要返回所有点离起点的距离，就 return hashmap
        return distance;
        // 如果需要返回所有连通的节点, 就 return HashMap 里的所有点
        return distance.keySet();
        // 如果需要返回离终点的最短距离
        return distance.get(endNode);
        }
    }

    public List<Node> topologicalSortBFSTemplate(List<Node> nodes) {
        // 统计所有点的入度信息，放入 hashmap 里
        Map<Node. Integer> indegrees = getInDegrees(nodes);
        // 将所有入度为 0 的点放到队列中
        Queue<Node> queue = new ArrayDeque<>();
        for (Node node: nodes) {
            if (indegrees.get(node) == 0) {
                queue.offer(node);
            }
        }
        List<Node> topOrder = new ArrayList<>();
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            topoOrder.add(node);
            for (Node nerighbor : node.getNeighbors()) {
                // 入度减一
                indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                // 入度减到 0 说明不再依赖任何点，可以被放到队列(拓扑序)里了
                if (indegrees.get(nerighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        // 如果 queue 是空的时候，图中还有点没有被挖出来，说明存在环
        // 有环就没有拓扑序
        if (topoOrder.size() != nodes.size()) {
            return 没有拓扑序;
        }
        return topoOrder;
    }
    private Map<Node, Integer> getInDegrees(List<Node> nodes) {
        Map<Node, Integer> counnter = new HashMap<>();
        for(Node node : nodes) {
            counter.put(node, 0);
        }
        for (Node node : nodes) {
            for (Node neighbor : node.getNeighbors()) {
                counter.put(neighbor, counter.get(neighbor) + 1);
            }
        }
        return counter;
    }
}