public class dynamicProgrammingTemplate {
    // State(递归的定义); Function(递归的拆解);Initialization(递归的出口) Answer(递归的调用
    Runtime: O(状态总数 * 每个状态的处理耗费) ○ 等于 O(状态总数 * 决策数)
    Space: O(状态总数) (不使用滚动数组优化) OR O(状态总数 / n)(使用滚动数组优化, n 是被滚动掉的那一个维度)
    // Time: 
    适用: 求方案总数 (90%),求最值(80%), 求可行性(80%) 
    不适用: 
        找所有具体的方案(准确率 99%)
        输入数据无序(除了背包问题外，准确率 60%~70%) 
        暴力算法已经是多项式时间复杂度(准确率 80%) 
    背包型 
    - 给出 n 个物品及其大小,问是否能挑选出一些物品装满大小为 m 的背包
    - 题目中通常有“和”与“差”的概念，数值会被放到状态中
    - 通常是二维的状态数组，前 i 个组成和为 j 状态数组的大小需要开 (n + 1) * (m + 1)
	1. Basic Backpack
    State: dp[i][j] 表示前 i 个数里挑若干个数是否能组成和为 j
    Function: 
        dp[i][j] = dp[i - 1][j] or dp[i - 1][j - A[i - 1]] 如果 j >= A[i - 1] dp[i][j] = dp[i - 1][j] 如果 j < A[i - 1]
        第 i 个数的下标是 i - 1，所以用的是 A[i - 1] 而不是 A[i]
    Initialization: dp[0][0] = true; dp[0][1...m] = false
    Answer: 使得 dp[n][v], 0 s <= v <= m 为 true 的最大 v

    2. 多重背包
    State: dp[i][j] 表示前 i 个物品挑出一些放到 j 的背包里的最大价值和
    Function: 
        dp[i][j] = max(dp[i - 1][j - count * A[i - 1]] + count * V[i - 1]) 其中 0 <= count <= j / A[i - 1]
    Initialization: dp[0][0..m] = 0
    Answer: dp[n][m]

    3. 区间型
    题目中有 subarray / substring 的信息
    - 大区间依赖小区间
    - 用 dp[i][j] 表示数组/字符串中 i, j 这一段区间的最优值/可行性/方案总数
    State: dp[i][j] 表示数组/字符串中 i,j 这一段区间的最优值/可行性/方案总数 
    Function: dp[i][j] = max/min/sum/or(dp[i,j 之内更小的若干区间])

    4. 匹配型
    -通常给出两个字符串
    - 两个字符串的匹配值依赖于两个字符串前缀的匹配值
    - 字符串长度为 n,m 则需要开 (n + 1) x (m + 1) 的状态数组 ○ 要初始化 dp[i][0] 与 dp[0][i]
    - 通常都可以用滚动数组进行空间优化
    State: dp[i][j] 表示第一个字符串的前 i 个字符与第二个字符串的前 j 个字符怎么样怎么样 (max/min/sum/or)

    5. 划分型
    - 是前缀型动态规划的一种, 有前缀的思想
    - 如果指定了要划分为几个部分:
        - dp[i][j] 表示前 i 个数/字符划分为 j 个 部分的最优值/方案数/可行性
    - 如果没有指定划分为几个部分:
        - dp[i] 表示前 i 个数/字符划分为若干个 部分的最优值/方案数/可行性
    State: 
        指定了要划分为几个部分:dp[i][j] 表示前 i 个数/字符划分为 j 个部分的最优值/方案数/可 行性
    没有指定划分为几个部分: dp[i] 表示前 i 个数/字符划分为若干个部分的最优值/方案数/可 行性

    6. 接龙型
    通常会给一个接龙规则，问你最长的龙有多长
        State: dp[i] 表示以坐标为 i 的元素结尾的最长龙的长度
        Function: dp[i] = max{dp[j] + 1}, j 的后面可以接上 i
    LIS 的二分做法选择性的掌握，但并不是所有的接龙型 DP 都可以用二分来优化
        State: dp[i] 表示以坐标为 i 的元素结尾的最长龙的长度
        Function: dp[i] = max{dp[j] + 1}, j 的后面可以接上 i
}