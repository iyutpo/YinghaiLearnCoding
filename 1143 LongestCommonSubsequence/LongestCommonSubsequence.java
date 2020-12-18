
/*
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
*/

class Solution {
    public int longestCommonSubsequence(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for ( int i = 0; i < s1.length(); ++i){
            for (int j = 0; j < s2.length(); ++j){
                if (s1.charAt(i) == s2.charAt(j)) 
                    dp[i+1][j+1] = 1 + dp[i][j];
                else
                dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

class Main {
    public static void main(String[] args){
        String s1 = "XMJYAUZ";
        String s2 = "MZJAWXU";
        Solution s = new Solution();
        int output = s.longestCommonSubsequence(s1, s2);
        System.out.println(Integer.toString(output));
    }
}