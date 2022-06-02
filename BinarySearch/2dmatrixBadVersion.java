public boolean searchMatrix74(int[][] matrix, int target) {
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
        int midNum = matrix[mid / n][mid % n];
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
    // from bottom left, move row-- for smaller seeks, col++ for bigger seeks. If out of bound, false;
    int m = matrix.length, n = matrix[0].length;
    for(int r = m-1, c = 0; r >= 0 && c < n;) {
        if (matrix[r][c] == target) {
            return true;
        } else if (matrix[r][c] < target) {
            c++;
        } else {
            r--;
        }
    }
    return false;
}

public int firstBadVersion278(int n) { // find first ; false, false, false, false, true, true
    if (n < 1) return n;
    int l = 0, r = n;
    while(l < r) { // l < r as only 1 of them satisfies
        int mid = l + (r - l) / 2;
        if (isBadVersion(mid)) {
            r = mid;// answers to the left, eventually return l
        } else {
            l = mid + 1; // can shift 1 forward since it's not badVersion
        }
    }
    return l;
}