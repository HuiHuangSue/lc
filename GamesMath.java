public boolean isValidSudoku36(char[][] board) {
    Set seen = new HashSet(); //only need to catch row/col/box, index, val as keys
    for (int i=0; i<9; ++i) {
        for (int j=0; j<9; ++j) {
            char number = board[i][j];
            if (number != '.')
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j) ||
                    !seen.add(number + " in block " + i/3 + "-" + j/3))
                    return false;
        }
    }
    return true;

    // Use hash set to record the status
    Set<Character>[] rows = new HashSet[9];
    Set<Character>[] cols = new HashSet[9];
    Set<Character>[] boxes = new HashSet[9];
    for (int r = 0; r < 9; r++) {
        rows[r] = new HashSet<Character>();
        cols[r] = new HashSet<Character>();
        boxes[r] = new HashSet<Character>();
    }
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            char val = board[r][c];
            if (val != '.') { // Check if the position is filled with number
                if (rows[r].contains(val)) { return false; } rows[r].add(val);
                if (cols[c].contains(val)) { return false; } cols[c].add(val);
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) { return false; } boxes[idx].add(val);
            }
        }   
    }
    return true;
}
public boolean checkValid2133(int[][] matrix) {
    Set seen = new HashSet(); //only need to catch row/col/box, index, val as keys
    for (int i=0; i<matrix.length; ++i) {
        for (int j=0; j<matrix.length; ++j) {
            int number = matrix[i][j];
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j))
                    return false;
        }
    }
    return true;
}