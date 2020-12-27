/*
You are a professional robber planning to rob houses along a street. Each house 
has a certain amount of money stashed. All houses at this place are arranged in
 a circle. That means the first house is the neighbor of the last one. Meanwhile, 
 adjacent houses have a security system connected, and it will automatically 
 contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers nums representing the amount of money of 
each house, return the maximum amount of money you can rob tonight without 
alerting the police.

Example 1:
    Input: nums = [2,3,2]
    Output: 3
    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.

Example 3:
    Input: nums = [0]w
    Output: 0
*/

public class HouseRobberII {
    // 本题与第198题（House Robber）的唯一区别在于，本题中的houses是环形的，而第198题是线形的
    // 现在假设我们已经有了第198题的解，那么我们可以用它作为本题的helper function：
    
    // public int HouseRobber(int[] num, int lo, int hi) {
    //     int include = 0, exclude = 0;
    //     for (int j = lo; j <= hi; j++) {
    //         int i = include, e = exclude;
    //     }
    // }

    public static void main (String[] args) {
        System.out.println();
    }
}

