public boolean isHappy202(int n) {
    if (n == 1) return true;
    Set<Integer> set = new HashSet<>();
    while (!set.contains(n)) {
        set.add(n);// add visited first
        int curSum = 0;
        while(n > 0) {
            curSum += Math.pow(n % 10, 2);
            n /= 10;
        }
        if (curSum == 1) return true;
        System.out.println(curSum);
        n = curSum;
    }
    return false;
}