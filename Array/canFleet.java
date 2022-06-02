// https://www.youtube.com/watch?v=Pr6T-3yB9RM&ab_channel=NeetCode
public int carFleet853(int target, int[] position, int[] speed) {
    TreeMap<Integer, Double> map = new TreeMap<>(Collections.reverseOrder());//map w/ sorted keys in reverse order
    for (int i = 0; i < position.length; i++) {
        map.put(position[i], (double)(target-position[i])/speed[i]); //position, timeToTarget
    } // {10, 1}, {8, 1}, {5, 7}, {3, 3}, {0,12}
    int count = 0;
    double current = 0;
    for(double time : map.values()) {
        if(time > current) { // if smaller, cars meets at intersection then will merge to fleet; 
            // greater means cannot meet so create another group
            count++;
            current = time;
        }
    }
    return count;
}