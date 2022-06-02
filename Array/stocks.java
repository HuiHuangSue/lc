public int maxProfit121(int[] prices) {
    int min = prices[0], max = Integer.MIN_VALUE;//min starts with first element, otherwise always 0; 
    for(int p : prices) {
        min = Math.min(p, min);
        max = Math.max(max, p - min);
    }
    return max < 0 ? 0 : max;
}
public int maxProfit121(int[] prices) {
    int minnum = Integer.MAX_VALUE, maxP = 0;
    for (int i = 0; i < prices.length; i++) {
        if (prices[i] < minnum) {
            minnum = prices[i];
        } else if (prices[i] - minnum > maxP) {
            maxP = prices[i] - minnum;
        }
    }
    return maxP;
}