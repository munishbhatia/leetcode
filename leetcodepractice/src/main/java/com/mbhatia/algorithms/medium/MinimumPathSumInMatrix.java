package com.mbhatia.algorithms.medium;

//https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSumInMatrix {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        int length = grid.length, width = grid[0].length;
        int minSum[][] = new int[length][width];

        for(int i=0; i<length; ++i){
            for(int j=0; j<width; ++j){
                if(i==0 && j==0)
                    minSum[i][j] = grid[i][j];
                else if(i == 0){
                    minSum[i][j] = grid[i][j] + minSum[i][j-1];
                }
                else if(j == 0)
                    minSum[i][j] = grid[i][j] + minSum[i-1][j];
                else
                    minSum[i][j] = grid[i][j] + Math.min(minSum[i-1][j], minSum[i][j-1]);
            }
        }
        return minSum[length-1][width-1];
    }
}
