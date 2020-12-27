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
    // Solution of House Robber: (!! 注意，这个不是本题的Solution！！)
    // 本题与第198题（House Robber）的唯一区别在于，本题中的houses是环形的，而第198题是线形的
    // 现在假设我们已经有了第198题的解，那么我们可以用它作为本题的helper function：
    private int HouseRobber(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }

    public int HouseRobberSolution(int[] num) {
        return HouseRobber(num, 0, num.length-1);
    }


    // Solution 1:
    // 现在，问题就变成如何偷一排环形houses。虽然有点复杂，但我们因为已经有了上面的HouseRobber function，
    // 问题就变成了如何将该问题进行简化。 从“第 i 个house是否被偷过” 进行延伸，我们可以自由选择
    // “第 i+1 个house是否被偷过” ，然后就可以通过假设 “某个house没被偷”， 将 环 break down。
    // 例如，1 -> 2 -> 3 -> 1 这个环，如果 1 没被偷，那就可以变成 2 -> 3
    // 由于每个house只有“被偷”和“没被偷”两种状态，且至少要有一般的houses没被偷，所以该算法
    // 的解 就是 两组连续houses 中 较大的一组。即： 
    // 要么 house i 没被偷，然后将 环 break down，用算法解出来；
    // 或者 house i+1 没被偷，然后将 环 break down，用算法解出来。
    // 因此，我们可以选择让 i = n, i + 1 = 0来简化我们的代码（其中 n 表示最后一个house，0是第一个house）。
    // 当然也可以选择任意其他两个连续的index （例如 i = 2, i + 1 = 3等等）
    public int Solution1(int[] nums){
        if (nums.length == 1) return nums[0];
        return Math.max(HouseRobber(nums, 0, nums.length - 2), HouseRobber(nums, 1, nums.length - 1));
    }

    public static void main (String[] args) {
        int[] nums1 = {2,3,2};
        int[] nums2 = {1,2,3,1};
        int[] nums3 = {0};
        HouseRobberII s = new HouseRobberII();
        System.out.println("Solution 1 with example 1: " + s.Solution1(nums1));
        System.out.println("Solution 1 with example 2: " + s.Solution1(nums2));
        System.out.println("Solution 1 with example 3: " + s.Solution1(nums3));
    }
}

