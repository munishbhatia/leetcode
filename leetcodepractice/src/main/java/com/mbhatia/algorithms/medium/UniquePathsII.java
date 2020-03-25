package com.mbhatia.algorithms.medium;

//https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(null == obstacleGrid || null == obstacleGrid[0])
            return 0;
        int length = obstacleGrid.length, width = obstacleGrid[0].length;
        int[][] paths = new int[length][width];

        for(int i=0; i<length; ++i){
            for(int j=0; j<width; ++j){
                if(obstacleGrid[i][j] == 1)
                    paths[i][j] = 0;
                else {
                    if(i==0 && j==0){
                        paths[i][j] = 1;
                    }
                    else if(i==0){
                        paths[i][j] = paths[i][j-1];
                    }
                    else if(j==0){
                        paths[i][j] = paths[i-1][j];
                    }
                    else {
                        paths[i][j] = paths[i-1][j] + paths[i][j-1];
                    }
                }
            }
        }
        return paths[length-1][width-1];
    }
}
