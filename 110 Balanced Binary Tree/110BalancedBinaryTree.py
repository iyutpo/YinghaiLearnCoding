
'''
LeetCode 110. Balanced Binary Tree
https://leetcode-cn.com/problems/balanced-binary-tree

CN:
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。


EN:
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
'''

class TreeNode:
    def __init__(self, value):
        self.left = None
        self.right = None
        self.val = value

class Solution(object):
    def isBalanced(self, root):
        
        def helper(root):
            if not root: return 0
            left = helper(root.left)
            right = helper(root.right)
            if left == -1 or right == -1 or abs(left - right) > 1:
                return -1
            return 1 + max(left, right)
        return helper(root) != -1

    def isBalancedIter(self, root):
        stack, node, last, depths = [], root, None, {}
        while stack or node:
            if node:
                stack.append(node)
                node = node.left
            else:
                node = stack[-1]
                if not node.right or last == node.right:
                    node = stack.pop()
                    left, right = depths.get(node.left, 0), depths.get(node.right, 0)
                    if abs(left - right) > 1: return False
                    depths[node] = 1 + max(left, right)
                    last = node
                    node = None
                else:
                    node = node.left
        return True

if __name__ == "__main__":
    t = TreeNode(3)
    t.left, t.right = TreeNode(1), TreeNode(5)
    t.left.left, t.right.right = TreeNode(2), TreeNode(10)
    s = Solution()
    print("This is recursive result: ", s.isBalanced(t))
    print("This is iterative result: ",  s.isBalancedIter(t))


    
    
