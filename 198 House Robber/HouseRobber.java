/*
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint 
stopping you from robbing each of them is that adjacent houses have security 
system connected and it will automatically contact the police if two adjacent 
houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of 
each house, determine the maximum amount of money you can rob tonight without 
alerting the police.

Example 1:
    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.

Example 2:
    Input: nums = [2,7,9,3,1]
    Output: 12
    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    Total amount you can rob = 2 + 9 + 1 = 12.
*/

import java.util.Arrays;

public class HouseRobber {
    /*
    该问题可以通过5个步骤来解决：
        1. Find recursive relation
        2. Recursive (top-down)
        3. Recursive + memo (top-down)
        4. Iterative + memo (bottom-up)
        5. Iterative + N variables (bottom-up)
    
    Step 1: 首先要找到递归关系
        每当小偷遇到一个房子，他只有两个选项（偷和不偷）。
        如果选择 偷 第i个房子，那么就意味着 不偷 第i-1个房子。同理 也意味着 偷 第i-2个房子。
        所以递归到最后，我们要返回的是 最大利润
            * 当前房子的利润 + 第i-2个房子的利润 + ...
            * 前一个被偷的房子 + 第i-3个房子的利润 + ...
            表示成伪代码：rob(i) = Math.max( rob(i-2) + currentHouseValue, rob(i-1))
        
    Step 2: Recursive (top-down):
    将上面的递归关系写出：
    */
    public int rob2(int[] nums){
        return rob2(nums, nums.length-1);
    }
    private int rob2(int[] nums, int i) { // i is the i-th house
        if (i < 0)   // 当 i < 0 的时候，说明没有房子给小偷偷了。
            return 0;
        return Math.max(rob2(nums, i-2) + nums[i], rob2(nums, i-1));
        // rob(nums, i-2) + nums[i] 是偷 i, i-2, i-4, ...房子的收益
        // rob(nums, i-1) 是偷 i-1, i-3, ... 房子的收益
        // 该算法会超时
    }


    // Step 3: Recursive + memo (top-down):
    int[] memo;
    public int rob3(int[] nums){
        memo = new int[nums.length+1];
        Arrays.fill(memo, -1);
        return rob3(nums, nums.length-1);
    }
    private int rob3(int[] nums, int i){
        if (i < 0) return 0;
        if (memo[i] >= 0) return memo[i];
        int result = Math.max(rob3(nums, i-2) + nums[i], rob3(nums, i-1));
        memo[i] = result;
        return result;
    }


    // Step 4: Iterative + memo (bottom-up)
    public int rob4(int[] nums) {
        if (nums.length == 0) return 0;
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            memo[i+1] = Math.max(memo[i], memo[i-1] + val);
        }
        return memo[nums.length];
    }


    // Step 5: Iterative + 2 variables (bottom-up)
    public int rob5(int[] nums) {
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int temp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = temp;
        }
        return prev1;
    }

    
    public static void main(String[] args){
        HouseRobber solution = new HouseRobber();
        int[] nums = {2,7,9,3,1};
        int[] nums2 = {1,0,0,3};
        System.out.println(Integer.toString(solution.rob2(nums)));
        System.out.println(Integer.toString(solution.rob3(nums)));
        System.out.println(Integer.toString(solution.rob4(nums)));
        System.out.println(Integer.toString(solution.rob5(nums)));
        System.out.println(Integer.toString(solution.rob5(nums2)));
    }
}
