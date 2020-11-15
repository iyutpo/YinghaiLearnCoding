'''
Given the root node of a binary search tree, return the sum of values of all nodes 
with a value in the range [low, high].
Example 1:
                10
            /       \
          5           15
        /  \            \
       3    7            18
    Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
    Output: 32

Example 2:
                    10
                /       \
              5           15
           /    \       /    \
        3         7    13     18
      /         /
    1          6
    Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
    Output: 23
'''

# 显然要用DFS。二叉树DFS模板是：
# def dfs(root):
#     if not root:         这里最常见的base case就是root不存在的情况，当然也可以用其他条件作为base case
#         return 
#     else:
#         if (Some Conditions):   这里一般是题中所问到的条件。例如本题就是node.lva介于[low, high]值域之间的节点
#             Some Operations     本题中，这里就是做一个 sum
#         if root.left:           然后如果存在左节点就向左递归
#             dfs(root.left)      就向左递归
#         if root.right:          然后如果存在右节点就向右递归
#             dfs(root.right)     就向右递归

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
    
class Solution:
    def rangeSumBST(self, root, low, high):
        self.ans = 0
        def dfs(node):
            if not node:
                return 0
            else:
                if low <= node.val <= high:
                    self.ans += node.val
                if node.left:
                    dfs(node.left)
                if node.right:
                    dfs(node.right)
        dfs(root)
        return self.ans

if __name__ == "__main__":
    # Initializa a BST (I'll use example 2):
    t = TreeNode(10)
    t.left, t.left.left, t.left.left = TreeNode(5), TreeNode(3), TreeNode(1)
    t.left.right, t.left.right.left = TreeNode(7), TreeNode(6)
    t.right, t.right.left, t.right.right = TreeNode(15), TreeNode(13), TreeNode(18)

    s = Solution()
    low, high = 6, 10
    print(s.rangeSumBST(t, low, high))    # Output is 23 which is correct :)