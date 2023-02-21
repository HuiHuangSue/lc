/*  121 at most once
    122 infinite times
    123 at most twice
    714 transaction fee
    188 at most k times
    309 cool down
*/
// Single day: 要先把min定下来，买，才可以开始卖，来计算profit
public int maxProfit121_SingleDay(int[] prices) {
    //min starts with first element, otherwise always 0;
    int minPrice = prices[0], maxProfit = Integer.MIN_VALUE; //=0 也过
    for(int p : prices) {
        minPrice = Math.min(p, minPrice);
        maxProfit = Math.max(maxProfit, p - minPrice);
    }
    return maxProfit < 0 ? 0 : maxProfit; // return Math.max(0, maxProfit);
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

// hold at most 1 share, can buy and sell on the same day,不限次数
// 只要大于前一天，就撸下来
public int maxProfit122_anyDays(int[] prices) {
    int profit = 0;
    for(int i = 1; i < prices.length; i++) {
        int diff = prices[i] - prices[i-1];
        profit += diff > 0 ? diff : 0;
    }
    return profit;
}

// at most 2 transactions, must sell before buy
public int maxProfit123_AtMost2(int[] prices) {
    // 不能凭空拥有股票，一本万利；否则hold1的max(0,-p)会是0
   int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
   int sold1 = 0, sold2 = 0;
   for(int p : prices) {
       int hold1Tmp = hold1, hold2Tmp = hold2, sold1Tmp = sold1, sold2Tmp = sold2;
       // 顺序不能变，状态转移hold1, sold1, hold2, sold2
       hold1 = Math.max(hold1Tmp, -p);// 之前就hold; 刚买 （hold1不能initialize成0）
       //刚卖，或之前就卖了
       sold1 = Math.max(hold1Tmp + p, sold1Tmp);//hold1Tmp 和现在卖的价格
       // 之前就hold;刚又买
       hold2 = Math.max(sold1 - p, hold2Tmp); // 状态由sold1转移来
       // 今天卖；之前就卖了
       sold2 = Math.max(hold2Tmp + p, sold2Tmp);
   }
   // 求N-1天的这四个的最大值。显示手头保留不卖肯定亏，所以选sold的更大值 max(sold1, sold2)
   return Math.max(sold1, sold2); // 或者直接sold2;
}
public int maxProfit123(int[] prices) {
    int hold1 = -prices[0], hold2 = -prices[0];
    int sold1 = 0, sold2 = 0;
    for(int i = 1; i < prices.length; i++) {
        int hold1Tmp = hold1, hold2Tmp = hold2, sold1Tmp = sold1, sold2Tmp = sold2;
        hold1 = Math.max(hold1Tmp, -prices[i]);
        sold1 = Math.max(hold1Tmp + prices[i], sold1Tmp);
        hold2 = Math.max(sold1 - prices[i], hold2Tmp);
        sold2 = Math.max(hold2Tmp + prices[i], sold2Tmp);
    }
    return sold2;
}

public int maxProfit188_atMostKTrans(int k, int[] prices) {
    // sold[k]: completed k transaction
    // hold[k]: completed k transactions, and have 1 stock in hand
    int[] hold = new int[k+1];
    int[] sold = new int[k+1];
    Arrays.fill(hold, Integer.MIN_VALUE); // 要initiate成min，不然会一直是0

    for (int p : prices) {
        for (int j = 1; j <= k; j++) { // k starts from 1, ends at k inclusive
            hold[j] = Math.max(sold[j-1] - p, hold[j]); //昨天卖了今天新买；昨天就hold着
            sold[j] = Math.max(hold[j]+ p, sold[j]); //昨天hold着今天卖钱；昨天就卖了
        }
    }
    return sold[k];
}


public int maxProfit714_fee(int[] prices, int fee) {
    int hold = Integer.MIN_VALUE, sold = 0; // hold cannot be 0
    for(int p : prices){
        //之前就hold,或者之前卖过又买了个; keep 'hold'value; prev_sold - p
        hold = Math.max(hold, sold - p);
        // 之前就卖了，现在又卖，然后进了P，减去fee
        sold = Math.max(sold, hold + p - fee);
    }
    return sold; // hold for sure less than sold.
}
public int maxProfit714(int[] prices, int fee) {
    int hold = Integer.MIN_VALUE, sold = 0; // hold cannot be 0
    for(int p : prices){
        int holdTmp = hold, soldTmp = sold; //转移的sold,虽然值相同
        //之前就hold,或者之前卖过又买了个; keep 'hold'value; prev_sold - p
        hold = Math.max(holdTmp, sold - p);
        // 之前就卖了，现在又卖，然后进了P，减去fee
        sold = Math.max(soldTmp, hold + p - fee);//转移的hold,不是旧的holdTmp
    }
    return sold; // hold for sure less than sold.
}

// 不限次数，但 cooldown for a day
public int maxProfit309_cooldown(int[] prices) {
    // hold, sold, cool
    int hold = Integer.MIN_VALUE, sold = 0, cooled = 0;
    for(int p : prices) {
        //必须要用上一个值，不然会覆盖。不然就用array
        int holdPrev = hold, soldPrev = sold, cooledPrev = cooled;
         // 昨天就持有了; 昨天cool过了，今天可以买了
        hold = Math.max(holdPrev, cooledPrev - p);
        // 昨天不能是sold, 否则是cool；必须是持有，然后卖
        sold = holdPrev + p;  // previous hold, not current hold!!!
        // 昨天卖了； 昨天冷却今天还是冷却
        cooled = Math.max(cooledPrev, soldPrev);
    }
   return Math.max(cooled, sold); // 不然[1]会返回min_value
}

