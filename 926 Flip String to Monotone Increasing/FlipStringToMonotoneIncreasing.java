/*
A string of '0's and '1's is monotone increasing if it consists of some number of 
'0's (possibly 0), followed by some number of '1's (also possibly 0.)
We are given a string S of '0's and '1's, and we may flip any '0' to a '1' 
or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.

Example 1:
    Input: "00110"
    Output: 1
    Explanation: We flip the last digit to get 00111.

Example 2:
    Input: "010110"
    Output: 2
    Explanation: We flip to get 011111, or alternatively 000111.

Example 3:
    Input: "00011000"
    Output: 2
    Explanation: We flip to get 00000000.

Note:
    1 <= S.length <= 20000
    S only consists of '0' and '1' characters.
*/

public class FlipStringToMonotoneIncreasing {
    public int Solution1(String S) {
        /*
        这题也是典型的动态规划问题。对于动态规划问题，我们先要考虑base case。本题的base case
        是，当我们在看最后一位数的时候，
        1) 如果是'1'那么就一定能保证 monontone increasing。
        2) 如果是'0'，那么就有两种情况:
            a. 要么就是'0'不变，将最后一位之前的所有'1' 都 flip 成 '0'
            b. 要么就是将最后一位的 '0' 变成 '1'。
        */
    }
}
