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

public class spiralMatrixII59 {
    /*
    Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
    1 -> 2 -> 3         1 -> 2 -> 3
              4  -->     8 -> 9   4
    7 <- 6 <- 5         7 <- 6 <- 5
    */

    /*
     * define row_begin, row_end, col_begin, col_end
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
        int index = 1; // 1...n
        while (rowBegin <= rowEnd && colBegin <= colEnd) { // inclusive!! as innermost element will have begin=end
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

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix2326(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(res[i], -1);
        }
        int rowBegin = 0, rowEnd = m - 1;
        int colBegin = 0, colEnd = n - 1;
        int count = m * n;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; head != null && i <= colEnd; i++) {
                res[rowBegin][i] = head.val;
                head = head.next;
                count--;
            }
            rowBegin++; // update!!!

            // col fixed, up to down
            for (int i = rowBegin; head != null && i <= rowEnd; i++) {
                res[i][colEnd] = head.val;
                head=head.next;
                count--;
            }
            colEnd--;

            // row fixed, right to left
            for (int i = colEnd; head != null && i >= colBegin; i--) {
                res[rowEnd][i] = head.val;
                head=head.next;
                count--;
            }
            rowEnd--;

            // col fixed, down to up
            for (int i = rowEnd; head != null && i >= rowBegin; i--) {
                res[i][colBegin] = head.val;
                head=head.next;
                count--;
            }
            colBegin++;
        }
        return res;
    }
}

// Input: rows = 5, cols = 6, rStart = 1, cStart = 4
// Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
public int[][] spiralMatrixIII885(int rows, int cols, int rStart, int cStart) {
    int[][] res = new int[rows * cols][2];
    // step1, 1 step to the right: row stays, col + 1
    // step2, 1 step down: row+1, col stays
    int[] dr = new int[]{0, 1, 0, -1};
    int[] dc = new int[]{1, 0, -1, 0};
    int count = 0;
    res[count++] = new int[]{rStart, cStart}; // count++
    // 1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8...
    for(int step = 1; step < 2*(rows+cols); step+=2){
        for(int i = 0; i < 4; i++) { // 4 directions
            int dStep = step + (i / 2); // k,k,k+1,k+1, 处理了两层，所以上面是k+2
            for(int j = 0; j < dStep; j++) {// go j steps on ith direction
                rStart += dr[i];
                cStart += dc[i];
                if(rStart>=0 && rStart<rows && cStart>=0 && cStart<cols){
                    res[count++] = new int[]{rStart, cStart};//count++
                    if(count == rows * cols) return res;
                }
            }
        }
    }
    return res;

}