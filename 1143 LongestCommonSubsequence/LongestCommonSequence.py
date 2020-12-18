'''
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string 
with some characters(can be none) deleted without changing the relative order 
of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" 
is not). A common subsequence of two strings is a subsequence that is common to 
both strings.

If there is no common subsequence, return 0.

Example 1:
    Input: text1 = "abcde", text2 = "ace" 
    Output: 3
    Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
    Input: text1 = "abc", text2 = "abc"
    Output: 3
    Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
    Input: text1 = "abc", text2 = "def"
    Output: 0
    Explanation: There is no such common subsequence, so the result is 0.
'''

class Solution:
    def longestCommonSequence(s1, s2):
        
        dp = [[0] * (len(s2) + 1) for _ in range(len(s1) + 1)]
        for i, c in enumerate(s1):
            for j, d in enumerate(s2):
                if c == d:
                    dp[i + 1][j + 1] = 1 + dp[i][j] 
                else:
                    dp[i + 1][j + 1] = max(dp[i][j + 1], dp[i + 1][j])
        return dp[-1][-1]

if __name__ == '__main__':
    s = Solution
    s1 = 'XMJYAUZ'
    s2 = 'MZJAWXU'
    print(s.longestCommonSequence(s1, s2))



