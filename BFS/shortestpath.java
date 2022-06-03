private static final int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int shortestPathBinaryMatrix1091(int[][] grid) {
        int n = grid.length;  // check start, end
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        
        Queue<int[]> qq = new ArrayDeque<>();
        qq.add(new int[]{0, 0});
        
        int step = 1;
        while(!qq.isEmpty()) {
            int size = qq.size();
            for(int i = 0; i < size; i++) {
                int[] current = qq.poll();
                if(current[0] == n - 1 && current[1] == n-1) { // check each node if reach target call
                    // cannot place check in dir loop, otherwise miss [[0]]
                    return step;
                }
                for(int[] dir : dirs) {
                    int x = current[0] + dir[0], y = current[1] + dir[1];
                    if(x >= 0 && x < n && y >= 0 && y < n && grid[x][y]==0 ) {//in matrix, unvisited
                        grid[x][y] = 2; // mark as visited
                        qq.offer(new int[]{x, y});
                    }
                }
            }
            step++;
        }
        return -1;
    }   