'''
Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

Example 1:
    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], 
                therefore the length is 4.

Example 2:
    Input: nums = [0,1,0,3,2,3]
    Output: 4

Example 3:
    Input: nums = [7,7,7,7,7,7,7]
    Output: 1
'''


class Solution1:
    # The first solution is Brute Force.
    # 最直观的方法当然是遍历后，返回最长上升子序列。我们可以定义一个recursive function（lengthofLIS)
    # 然后让该function返回可能的 最长上升子序列 的长度。每次调用该function时，我们要考虑两件事：
    # 1. 当前元素大于前一个元素。此时，我们将当前元素放到 最长上升子序列 中。
    # 2. 当前元素小于前一个元素。此时，我们不能将当前元素放到 最长上升子序列 中
    def longestIncreasingSubsequence(nums):
        def longestIncreasingSubsequence(nums, prev, curr):
            if (curr == len(nums)):
                return 0
            taken = 0
            if (nums[curr] > prev):
                taken = 1 + longestIncreasingSubsequence(nums, nums[curr], curr+1)
            nottaken = longestIncreasingSubsequence(nums, prev, curr+1)
            return max(taken, nottaken)
        return longestIncreasingSubsequence(nums, float('-inf'), 0)


class Solution2:
    # 在第一个方法中，有些参数在递归的过程中被反复调用。这种redundancy可以通过一定的方法来消除
    # 在该方法中，我们加入一个2-d memoization数组memo。memo[i][j]表示LIS的长度。
    # nums[i]表示前一个元素是否该加入LIS。nums[j]表示当前元素是否该加入LIS
    def longestIncreasingSubsequence(nums):
        memo = []
        for _ in range(len(nums) + 1): memo.append([-1] * (len(nums)))
        def longestIncreasingSubsequence(nums, prevIndex, curr, memo):
            if (curr == len(nums)):
                return 0
            if (memo[prevIndex+1][curr] >= 0):
                return memo[prevIndex+1][curr]
            taken = 0
            if (prevIndex < 0 or nums[curr] > nums[prevIndex]):
                taken = 1 + longestIncreasingSubsequence(nums, curr, curr+1, memo)
            nottaken = longestIncreasingSubsequence(nums, prevIndex, curr+1, memo)
            memo[prevIndex+1][curr] = max(taken, nottaken)
            return memo[prevIndex+1][curr]
        return longestIncreasingSubsequence(nums, -1, 0, memo)


class Solution3:
    
if __name__ == '__main__':
    s1 = Solution1
    print(s1.longestIncreasingSubsequence([10,9,2,5,3,7,101,18]))
    s2 = Solution2
    print(s2.longestIncreasingSubsequence([10,9,2,5,3,7,101,18]))