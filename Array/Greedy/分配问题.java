
// 455 Assign Cookie & 135 Candy
/*************************************************************************************************************/
/*
There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
You are giving candies to these children subjected to the following requirements:
    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:

Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.*/
/*
把所有孩子的糖果数初始化为 1 [1,1,1]; 
先从左往右遍历一遍，如果右边孩子的评分比左边的高，则右边孩子的糖果数更新为左边孩子的 糖果数加 1; [1,1,2]
再从右往左遍历一遍，如果左边孩子的评分比右边的高，且左边孩子当前的糖果数 不大于右边孩子的糖果数，则左边孩子的糖果数更新为右边孩子的糖果数加 1。[2,1,2]
通过这两次遍历， 分配的糖果就可以满足题目要求了。这里的贪心策略即为，在每次遍历中，只考虑并更新相邻一 侧的大小关系。
*/

public int candy(int[] ratings) {
        // input validation
        int[] res = new int[ratings.length];
        Arrays.fill(res, 1);
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) {
                // 比令居多一颗，而不是多给一颗
                res[i] = res[i-1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                // 比邻居多一颗，或者自己本来就有很多颗了 (4,1+1)
                res[i] = Math.max(res[i + 1] + 1, res[i]);
            }
        }
        return Arrays.stream(res).sum();
    // [1,3,2,2,1]
    }

/*************************************************************************************************************/

// 455 Assign Cookie
/* Assume you are an awesome parent and want to give your children some cookies. 
    But, you should give each child at most one cookie.
    Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; 
    and each cookie j has a size s[j]. 
    If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. 
    Your goal is to maximize the number of your content children and output the maximum number.
Example 1:
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.

Example 2:
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, */

    /*因为饥饿度最小的孩子最容易吃饱，所以我们先考虑这个孩子。
    为了尽量使得剩下的饼干可以满足饥饿度更大的孩子，所以我们应该把大于等于这个孩子饥饿度的、且大小最小的饼干给这个孩子。
    满足了这个孩子之后，我们采取同样的策略，考虑剩下孩子里饥饿度最小的孩子，直到没有满足条件的饼干存在。
    简而言之，这里的贪心策略是，给剩余孩子里最小饥饿度的孩子分配最小的能饱腹的饼干。
    至于具体实现，因为我们需要获得大小关系，一个便捷的方法就是把孩子和饼干分别排序*/

    public int findContentChildren(int[] g, int[] s) {
        // input & edge case validations
        Arrays.sort(g);
        Arrays.sort(s);
        int cookieInx = 0, childInx = 0;
        // 只有cookie，只有child的情况都没必要继续了
        while (childInx < g.length && cookieInx < s.length ) {
            // 如果大小能满足，则满足掉一个小孩
            if (g[childInx] <= s[cookieInx]) {
                childInx++;
            }
            // 满足了小孩，则去掉current cookie，转移到下一个cookie;没满足，则试试下一个cookie够不够
            cookieInx++;
        }
        return childInx; //如果满足了，则已经被increment了
    }
    /*
    [1,2,3] [1,1]
    */

