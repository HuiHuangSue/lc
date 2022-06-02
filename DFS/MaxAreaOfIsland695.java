public int maxAreaOfIsland695 {
    private final static int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) { // skip water0, starts with island 1
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }
    private int dfs(int[][] grid, int r, int c) {
        grid[r][c] = 2; // mark current as visited
        int curArea = 1; 
        // try out all directions
        for(int[] dir : dirs) {
            int newR = r + dir[0], newC = c + dir[1];
            boolean valid = (newR >= 0 && newR < grid.length) && (newC >= 0 && newC < grid[0].length) 
                            && (grid[newR][newC] == 1);
            if (valid) { // continue dfs if within grid edges and next is island
                curArea += dfs(grid, newR, newC);
            }
        }
        return curArea; // return accumulated area or currentArea
    }
    
    private int bfs(int[][] grid, int i, int j){
        int m=grid.length,n=grid[0].length;
        if(grid[i][j]==0) return 0;
        grid[i][j]=0;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{i,j});
        int res=1;
        while(!q.isEmpty()){
            int[] pos=q.poll();
            for (int[] dir:dirs){
                int x=dir[0]+pos[0];
                int y=dir[1]+pos[1];
                if(x<0||x>=m||y<0||y>=n||grid[x][y]==0){
                    continue;
                }
                grid[x][y]=0;
                res++;
                q.offer(new int[]{x,y});
            }
        }
        return res;
    }
}