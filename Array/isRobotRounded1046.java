public boolean isRobotBounded1046(String instructions) {
    int dirs[][] = {{0,1}, {1,0}, {0, -1}, {-1, 0}}; //N, E, S, W, counter clockwise
        int x = 0, y = 0, dir = 0;
        for(char c : instructions.toCharArray()) {
            if(c == 'G') {
                x += dirs[dir][0];
                y += dirs[dir][1];//dir is t
            } else if (c == 'L') {
                dir = (dir + 1) % 4; // left shift 1 forward, or back to front
            } else {
                dir = (dir + 3) % 4; // right shift 3 forward, or back to front
            }
        }
        return (x == 0 && y == 0) || (dir > 0);
        
    // true: Last position can go back to (0,0);
    // false: last direction is North, it will never come back
    // If left at other directions, it is still in the circle, and will come back always "GL"
    int x = 0, y = 0;
    String dir = "North";
    for(char c : instructions.toCharArray()) {
        if(c == 'G') {
            if(dir == "North") {
                y++;
            } else if (dir == "South") {
                y--;
            } else if (dir == "East") {
                x--;
            } else {
                x++;
            }
        } else if (c == 'L') {
            if(dir == "North") {
                dir = "West";
            } else if (dir == "West") {
                dir = "South";
            } else if (dir == "South") {
                dir = "East";
            } else if (dir == "East") {
                dir = "North";
            }
        } else { // R
            if(dir == "North") {
                dir = "East";
            } else if (dir == "West") {
                dir = "North";
            } else if (dir == "South") {
                dir = "West";
            } else if (dir == "East") {
                dir = "South";
            }
        }
    }
    if(x == 0 && y == 0) {
        return true;
    }
    if (dir == "North") {
        return false;
    }
    return true;
}