/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    Map<Integer, Employee> map; // global map with id and its Employee object
    public int getImportance690(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return dfs(id);
    }
    private int dfs(int id) {
        Employee e = map.get(id);
        int res = e.importance; // initialize with its own importance, and dfs on value
        for(int subordinateId : e.subordinates) {
            res += dfs(subordinateId);
        }
        return res; // dfs on accumulating value;
    }
}