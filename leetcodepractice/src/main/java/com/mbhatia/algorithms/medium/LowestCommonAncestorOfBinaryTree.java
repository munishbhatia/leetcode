package com.mbhatia.algorithms.medium;

import com.mbhatia.algorithms.common.TreeNode;

public class LowestCommonAncestorOfBinaryTree {
    private boolean isSubNode(TreeNode curr, TreeNode key){
        if(curr == null)
            return false;
        if(key == null)
            return true;
        if(curr.val == key.val)
            return true;
        else
            return (isSubNode(curr.left, key) || isSubNode(curr.right, key));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = null;
        if(root == null)
            return null;
        if(p != null && root.val == p.val){
            if(q == null || isSubNode(root, q))
                return root;
        }
        else if(q != null && root.val == q.val){
            if(p == null || isSubNode(root, p))
                return root;
        }
        else {
            TreeNode lcaLeft = lowestCommonAncestor(root.left, p, q);
            if(lcaLeft != null)
                LCA = lcaLeft;
            else {
                TreeNode lcaRight = lowestCommonAncestor(root.right, p, q);
                if (lcaRight != null)
                    LCA = lcaRight;
                else {
                    if(isSubNode(root, p) && isSubNode(root, q))
                        LCA = root;
                }
            }
        }
        return LCA;
    }
}
