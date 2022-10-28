public class spiralMatrix59 {
    /*
    Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
    1 -> 2 -> 3         1 -> 2 -> 3   
              4  -->     8 -> 9    4  
    7 <- 6 <- 5         7 <- 6 <- 5
    */

    /*
     * define row_begin, row_end, col_begin, col_end
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
        int index = 1; // 1...n
        while (rowBegin <= rowEnd && colBegin <= colEnd) { // inclusive!!
            // row fixed, left to right
            for (int i = colBegin; i <= colEnd; i++) {
                res[rowBegin][i] = index;
                index++;
            }
            rowBegin++; // update!!!
            
            // col fixed, up to down
            for (int i = rowBegin; i <= rowEnd; i++) {
                res[i][colEnd] = index;
                index++;
            }
            colEnd--;

            // row fixed, right to left
            for (int i = colEnd; i >= colBegin; i--) {
                res[rowEnd][i] = index;
                index++;
            }
            rowEnd--;

            // col fixed, down to up
            for (int i = rowEnd; i >= rowBegin; i--) {
                res[i][colBegin] = index;
                index++;
            }
            colBegin++;
        }
        return res;
    }
}
