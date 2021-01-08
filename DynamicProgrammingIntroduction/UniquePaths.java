/*
A robot is located at the top-left corner of a m x n grid 
(marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the 
grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Example 1:
    +-------------------------------------------------------+
    | start |       |       |       |       |       |       |
    |-------------------------------------------------------|
    |       |       |       |       |       |       |       |
    |-------------------------------------------------------|
    |       |       |       |       |       |       | Finish|
    +-------------------------------------------------------+
    Input: m = 3, n = 7
    Output: 28

Example 2:
    Input: m = 3, n = 2
    Output: 3
    Explanation:
    From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
        1. Right -> Down -> Down
        2. Down -> Down -> Right
        3. Down -> Right -> Down

Example 3:
    Input: m = 7, n = 3
    Output: 28

Example 4:
    Input: m = 3, n = 3
    Output: 6
*/

public class UniquePaths {
    /*
    该问题是非常典型的动态规划问题。所以关键在于如何找到递推公式。
    我们可以从最终的情况入手。即，如果想要到达“Finish”单元格，那么机器人的上一个状态只有两种，
    一种是在“Finish”的上方，另一种是在左方。由于从start -> finish 和 从 finish -> start 是等价的，
    所以我们可以通过 m - 1 或者 n - 1 的方式来实现从 finish -> start 的过程。
    对于base case，第一种自然是当网格不存在的时候，返回 0 个路径。
    第二种是当网格为 1 × 1 的时候，返回 1 个路径
    */
    public int Solution1(int m, int n){
        if (m <= 0 || n <= 0) { return 0; }
        if (m == 1 && n == 1) { return 1; }
        return Solution1(m - 1, n) + Solution1(m, n - 1);
    }
    /* 对于第一种Solution来说，由于该问题的要求是： 1 <= m, n <= 100，所以第一种方法会超时。
    这时候通常需要考虑添加memoization或者迭代的方式来实现动态规划
    */

    public int Solution2(int m, int n){
        /* 在这种方法中，我们将添加memoization和迭代的方式来节省计算时间
        */
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) { memo[i][0] = 1; }  // 第一行的值都为1
        for (int j = 0; j < n; j++) { memo[0][j] = 1; }  // 第一列的值都为1

        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                memo[i][j] = memo[i-1][j] + memo[i][j-1];
            }
        }
        return memo[m-1][n-1];
    }

    public static void main (String[] args) {
        int m = 7, n = 3;
        UniquePaths s = new UniquePaths();
        System.out.println(s.Solution1(m, n));
        System.out.println(s.Solution2(m, n));
    }
}
