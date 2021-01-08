/*
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb 
to the top?

Example 1:
    Input: n = 2
    Output: 2
    Explanation: There are two ways to climb to the top.
        1. 1 step + 1 step
        2. 2 steps

Example 2:
    Input: n = 3
    Output: 3
    Explanation: There are three ways to climb to the top.
        1. 1 step + 1 step + 1 step
        2. 1 step + 2 steps
        3. 2 steps + 1 step
*/


public class ClimbingStairs {
    public int Solution1(int n) {
        /*
        很显然该问题就是斐波那契数列。最简单的方法就是通过递归来计算。
        Time: O(2^n), Space: O(1)
        */
        if (n == 0) { return 1; }
        else if (n == 1) { return 1; }
        else { return Solution1(n - 1) + Solution1(n - 2); }
    }

    public int Solution2(int n) {
        /*
        由于Solution1使用了递归，导致时间复杂度是指数级别的O(2^n)。
        这里我们可以使用迭代的方法降低时间复杂度。同时要使用memoization牺牲掉空间
        Time: O(n), Space: O(n)
        */
        int[] memo = new int[n+1];
        if ( n <= 1) { return 1; }
        for (int i = 0; i < memo.length; i++) {
            if (i == 0 || i == 1) { memo[i] = 1; }
            else {
                memo[i] = memo[i-1] + memo[i-2];
            }
        }
        return memo[memo.length-1];
    }


    public int Solution3(int n) {
        /*
        本问题还可以进一步优化，去掉memoization
        */
        int memo1 = 1, memo2 = 1, temp;
        for (int i = 2; i < n+1; i++) {
            temp = memo2;
            memo2 = memo1 + memo2;
            memo1 = temp;
        }
        return memo2;
    }

    public static void main (String[] args){
        ClimbingStairs s = new ClimbingStairs();
        System.out.println(Integer.toString(s.Solution1(10)));
        System.out.println(Integer.toString(s.Solution2(10)));
        System.out.println(Integer.toString(s.Solution3(10)));
    }
}
