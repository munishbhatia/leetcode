package com.mbhatia.algorithms.medium;

import com.mbhatia.algorithms.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum {
//    class LevelTraversalQueueNode{
//        TreeNode node;
//        int level;
//
//        public LevelTraversalQueueNode(TreeNode node, int level) {
//            this.node = node;
//            this.level = level;
//        }
//    }
//    public int rob(TreeNode root) {
//        if(root == null)
//            return 0;
//        int oddSum = 0, evenSum = 0;
//        Queue<LevelTraversalQueueNode> levelOrderQ = new LinkedList<>();
//        levelOrderQ.add(new LevelTraversalQueueNode(root, 0));
//
//        while (!levelOrderQ.isEmpty()){
//            LevelTraversalQueueNode currNode = levelOrderQ.poll();
//            if(currNode.node != null){
//                if(currNode.level % 2 == 0)
//                    evenSum += currNode.node.val;
//                else
//                    oddSum += currNode.node.val;
//                levelOrderQ.add(new LevelTraversalQueueNode(currNode.node.left, currNode.level+1));
//                levelOrderQ.add(new LevelTraversalQueueNode(currNode.node.right, currNode.level+1));
//            }
//        }
//        return Math.max(oddSum, evenSum);
//    }

    private int robberyHelper(TreeNode node, Map<TreeNode, Integer> nodeMaxRobValMap){
        if(node == null)
            return 0;
        if(nodeMaxRobValMap.containsKey(node))
            return nodeMaxRobValMap.get(node);

        int robCurrOption = node.val;
        if(node.left != null) {
            robCurrOption += robberyHelper(node.left.left, nodeMaxRobValMap);
            robCurrOption += robberyHelper(node.left.right, nodeMaxRobValMap);
        }
        if(node.right != null) {
            robCurrOption += robberyHelper(node.right.left, nodeMaxRobValMap);
            robCurrOption += robberyHelper(node.right.right, nodeMaxRobValMap);
        }

        int dontRobCurrOption = 0;
        dontRobCurrOption += robberyHelper(node.left, nodeMaxRobValMap);
        dontRobCurrOption += robberyHelper(node.right, nodeMaxRobValMap);

        int maxRobVal = Math.max(robCurrOption, dontRobCurrOption);
        nodeMaxRobValMap.put(node, maxRobVal);

        return maxRobVal;
    }

    public int rob(TreeNode root) {
        if(root == null)
            return 0;
        Map<TreeNode, Integer> nodeMaxRobValMap = new HashMap<>();
        return robberyHelper(root, nodeMaxRobValMap);
    }
}
