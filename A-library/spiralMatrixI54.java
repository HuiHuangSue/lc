public class spiralMatrixI54 {
     /*
    Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
    1 -> 2 -> 3 -> 4         1  -> 2  -> 3  -> 4
                   5  -->    10 -> 11 -> 12 -> 5  
    9 <- 8 <- 7 <- 6         9  <- 8 <-  7  <- 6
    */

    /*
     * define row_begin, row_end, col_begin, col_end
     */
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            int rowBegin = 0, rowEnd = matrix.length - 1;
            int colBegin = 0, colEnd = matrix[0].length - 1;
            while (rowBegin <= rowEnd && colBegin <= colEnd) {
                // top row fixed, col left to right. boundary check covered by above
                for (int i = colBegin; i <= colEnd; i++) {
                    res.add(matrix[rowBegin][i]);
                }
                rowBegin++;
                // right col fixed, row top to bottom. boundary check covered by above
                for (int i = rowBegin; i <= rowEnd; i++) {
                    res.add(matrix[i][colEnd]);
                }
                colEnd--;
                if (rowBegin <= rowEnd) { // keep checking, ow goes back repeat
                    // bottom row fixed, col right to left
                    for (int i = colEnd; i >= colBegin; i--) {
                        res.add(matrix[rowEnd][i]);
                    }          
                    rowEnd--; 
                }
                if (colBegin <= colEnd) { // keep checking, ow goes back repeat
                    // left column fixed, row bottom to top
                    for (int i = rowEnd; i >= rowBegin; i--) {
                        res.add(matrix[i][colBegin]);
                    }
                    colBegin++;
                }
            }
            return res;
        }
    }
}
