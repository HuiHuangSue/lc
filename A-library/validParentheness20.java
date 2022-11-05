public class validParentheness20 {
    // [
    // ][
    // [)
    public boolean isValid20(String s) {
        // [(]) need stack
        Stack<Character> sk = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for(char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                sk.push(c);
            } else {
                if (sk.isEmpty() || c != map.get(sk.pop())) { // check if sk empty, and (), not[(])
                    return false;
                }
            }
        }
        return sk.isEmpty(); // eventually '['
    }
}
