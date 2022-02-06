public class backtrack {

    public int numIslands200(char[][] grid) {
        int rc = grid.length, cc = grid[0].length;
        boolean[][] visited = new boolean[rc][cc];
        int count = 0;
        for (int i = 0; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                if (visited[i][j] || grid[i][j] == '0') continue;
                count++;
                visit(grid, i, j, count, visited);
            }
        }
        return count;
    }

    private void visit(char[][] grid, int i, int j, int count, boolean[][] visited) {
        // below 3 conditions to stop backtracking
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return; // >=grid[0].length
        if(visited[i][j]) return;
        if(grid[i][j] == '0') return;

        visited[i][j] = true;
        visit(grid, i - 1, j, count, visited);
        visit(grid, i + 1, j, count, visited);
        visit(grid, i, j - 1, count, visited);
        visit(grid, i, j + 1, count, visited);
    }

    public int numDistinctIslands694(int[][] grid) {
        int rc = grid.length, cc = grid[0].length;
        boolean[][] visited = new boolean[rc][cc];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < rc; i++){
            for (int j = 0; j < cc; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i,j,visited,sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visited, StringBuilder sb) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (visited[i][j]) return;
        if (grid[i][j] == 0) return;
        visited[i][j] = true;
        dfs(grid, i + 1, j, visited, sb.append('r'));
        dfs(grid, i - 1, j, visited, sb.append('l'));
        dfs(grid, i, j + 1, visited, sb.append('d'));
        dfs(grid, i, j - 1, visited, sb.append('u'));
        sb.append('b'); // append when back 1 step
    }

    public int closedIsland1254(int[][] grid) {
        int rc = grid.length, cc = grid[0].length;
        boolean[][] visited = new boolean[rc][cc];
        int count = 0;
        for (int i = 0; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                if(!visited[i][j] && grid[i][j] == 0){
                    if(dfs(grid, i, j, visited)) count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid, int i, int j, boolean[][] visited) {
        // DFS: stop when find water or edge; continue if also land
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false; // >=grid[0].length
        if(grid[i][j] == 1) return true; // found water on a side, success and keep on dfs
        if(visited[i][j]) return true;
        visited[i][j] = true;
        return // must be single &, otherwise will skip visiting the latter islands
            (dfs(grid, i - 1, j, visited) &
            dfs(grid, i + 1, j, visited) &
            dfs(grid, i, j - 1, visited) &
            dfs(grid, i, j + 1, visited));
    }

    public int islandPerimeter463_Omn(int[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) { // land
                    res += dfs(grid, i, j, visited);
                }
            }
        }
        return res;
    }
    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
            || grid[i][j] == 0 ) return 1; // add 1 perimeter when it's water or edge/out_of_bound
        if (visited[i][j]) return 0; // if visited, do not count
        visited[i][j] = true;
        return dfs(grid, i - 1, j, visited) + dfs(grid, i+1, j, visited) + dfs(grid, i, j-1, visited) + dfs(grid, i, j+1, visited) ;

    }
    public int islandPerimeter463_n^2(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) { // land
                    int neighbors = isNeighbor(grid,i-1,j)+isNeighbor(grid,i+1,j)+isNeighbor(grid,i,j-1)+isNeighbor(grid,i,j+1);
                    res += (4 - neighbors);
                }
            }
        }
        return res;
    }
    private int isNeighbor(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        } // out of bound or water
        return 1;
    }
}