

'''QUESTION:
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
'''

''' SOLUTION:
For a sorted array, the left half will be in the left subtree, middle value as the root, right half in the right subtree. This holds true for every node:

[1, 2, 3, 4, 5, 6, 7] -> left: [1, 2, 3], root: 4, right: [5, 6, 7]
[1, 2, 3] -> left: [1], root: 2, right: [3]
[5, 6, 7] -> left: [5], root: 6, right: [7]

Many of the approaches here suggest slicing an array recursively and passing them. However, slicing the array is expensive. It is better to pass the left and right bounds into recursive calls instead.
'''


class TreeNode:
    def __init__(self, value):
        self.left = None
        self.right = None
        self.val = value

class Solution(object):
    def sortedArrayToBST(self, nums):
        def convert(left, right):
            if left > right:
                return 
            mid = (left + right) // 2
            node = TreeNode(nums[mid])
            node.left = convert(left, mid - 1)
            node.right = convert(mid + 1, right)
            return node
        return convert(0, len(nums) - 1)

if __name__ == "__main__":
    alist = [1,2,3,4,5,6,7]
    s = Solution()
    result = s.sortedArrayToBST(alist)
    print(result.val)