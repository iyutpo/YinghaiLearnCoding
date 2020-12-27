
/*
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
*/

#include <stddef.h>
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution{
public:
    int result;
    void getDepth(TreeNode* node, int depth){
        result = depth > result ? depth : result;  // Tree的中点
        if (node->left == NULL && node->right==NULL) return;
        if (node->left){
            depth++;
            getDepth(node->left, depth);
            depth--;
        }
        if (node->right){
            depth++;
            getDepth(node->right, depth);
            depth--;
        }
        return ;
    }
    int maxDepth(TreeNode* root){
        result = 0;
        if (root==0) return result;
        getDepth(root, 1);
        return result;
    }
};


