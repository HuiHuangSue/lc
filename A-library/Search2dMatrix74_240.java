public class Search2dMatrix74and240 {
    /*
     * https://leetcode.com/problems/search-a-2d-matrix/
     * https://leetcode.com/problems/search-a-2d-matrix-ii/
     * From top right to lower left, then only has 2 options to grow or shrink O(n)
     * Binary. left=0,right=m*n-1; getMidNumber=matrix[mid/n][mid%n], regardless of m
     * 
     * Search2dMatrix74.java
    */
    public boolean searchMatrix74_TopRight2LowerLeft(int[][] matrix, int target) {
        int rowCount = matrix.length, colCount = matrix[0].length;
        for(int r = 0, c = colCount - 1; r < rowCount && c >= 0;) {
            if(matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                r++;
            } else{
                c--;;
            }
        }
        return false;
    }
    public boolean searchMatrix74_BinarySearch(int[][] matrix, int target) {
        /* m x n = 3 * 4
        [0,1,2 , 3
         4,5,6 , 7
         8,9,10,11]  5: r=1=5/4; c=5%4=1; 11: r=2=11/4;c=11%4=3
         row=index/n, col=index%n
        */
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1; // l,r是展开matrix后的index
        while(l <= r) { // l,r exclude mid when updating, then can meet
            int mid = l + (r - l) / 2;
            int midNum = matrix[mid / n][mid % n]; //calculation!!!!!!
            if (midNum == target) {
                return true;
            } else if (midNum < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    public boolean searchMatrixII240(int[][] matrix, int target) {
        // Same as 74I.
        int rowCount = matrix.length, colCount = matrix[0].length;
        for(int r = 0, c = colCount - 1; r < rowCount && c >= 0;) {
            if(matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                r++;
            } else{
                c--;;
            }
        }
        return false;
    }
}
