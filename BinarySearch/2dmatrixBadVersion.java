
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