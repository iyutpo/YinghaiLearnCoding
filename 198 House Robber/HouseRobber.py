'''
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
'''

class HouseRobber:
    '''该问题可以通过5个步骤来解决：
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
    '''
    def rob2(nums):
        def rob2Helper(nums, i):
            if i < 0: return 0     # 当 i < 0 的时候，说明没有房子给小偷偷了。
            return max(rob2Helper(nums, i-2) + nums[i], rob2Helper(nums, i-1))

        return rob2Helper(nums, len(nums)-1)
        # rob(nums, i-2) + nums[i] 是偷 i, i-2, i-4, ...房子的收益
        # rob(nums, i-1) 是偷 i-1, i-3, ... 房子的收益
        # 该算法会超时


    # Step 3: Recursive + memo (top-down):
    # 相比于 Step 2方法，该方法由于有 memo 的存在，使得 从 len(nums)-1 到 0 递归（自上而下）
    # 的过程中，将每次递归得到的 result 存在 memo[i] 中。当 i < 0 时，停止递归，此时得到的
    # result 就是我们的答案。
    # 而相比之下， Step 2 方法不仅需要top-down这个过程，也就是将递归函数写成：
    '''
    result = max(rob2Helper(nums, 4-2) + nums[4], rob2Helper(nums, 4-1))
           = max(max(rob2Helper(nums, 4-2-2) + nums[4-2]) + nums[4], max(rob2Helper(nums, 4-1-2) + nums[4-1], rob2Helper(nums, 4-1-1)))
           = max(max(max(rob2Helper(nums,4-2-2-2) + nums[4-2-2]) + nums[4-2]) + nums[4], max(max(rob2Helper(nums, 4-1-2-2) + nums[4-1]), rob2Helper(nums, 4-1-2-1), max(nums, 4-1-1-2) + nums[4-1-1], rob2Helper(nums, nums[4-1-1-1]))))
           = ...
           = 12
    '''
    memo = []
    def rob3(nums):
        memo = [-1 for _ in range(len(nums)+1)]
        def rob3Helper(nums, i):
            if i < 0: return 0
            if memo[i] >= 0: return memo[i]
            print(i, memo)
            result = max(rob3Helper(nums, i-2) + nums[i], rob3Helper(nums, i-1))
            print('result: ', result)
            memo[i] = result
            print(memo[i], i, result)
            return result
        return rob3Helper(nums, len(nums)-1)
        [2, 7, 9, 3, 1]


    # Step 4: Iterative + memo (bottom-up)
    def rob4(nums):
        if len(nums) == 0: return 0
        memo = [-1 for _ in range(len(nums)+1)]
        memo[0] = 0
        memo[1] = nums[0]
        for i in range(1, len(nums)):
            val = nums[i]
            memo[i+1] = max(memo[i], memo[i-1] + val)
        return memo[len(nums)]
    

    # Step 5: Iterative + 2 variables (bottom-up)
    def rob5(nums):
        if len(nums) == 0: return 0
        prev1 = 0
        prev2 = 0
        for num in nums:
            temp = prev1
            prev1 = max(prev2 + num, prev1)
            prev2 = temp
        return prev1


if __name__ == '__main__':
    solution = HouseRobber
    nums = [2,7,9,3,1]
    # print(solution.rob2(nums))
    print(solution.rob3(nums))
    # print(solution.rob4(nums))
    # print(solution.rob5(nums))
