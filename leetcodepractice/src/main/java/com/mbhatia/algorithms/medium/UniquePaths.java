package com.mbhatia.algorithms.medium;

//https://leetcode.com/problems/unique-paths/
//Top Down approach (uncommented) beats 100% of solutions on time
//Bottom up approach (commented) beats only 5% of solutions on time
//Memory requirements are same for both!
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if(m==0 && n==0)
            return 0;
        if(m==0 || n== 0)
            return 1;
        int[][] paths = new int[m][n];
//        //Initialize the num of paths from bottom row and rightmost column to final block as 1
//        for(int i=0; i<n; ++i)
//            paths[m-1][i] = 1;
//        for(int i=0; i<m; ++i)
//            paths[i][n-1] = 1;
//
//        //Fill the rest of the matrix using paths[i][j] = paths[i][j+1]+paths[i+1][j] (move right + move down)
//        for(int i=m-2; i>=0; --i){
//            for(int j=n-2; j>=0; --j)
//                paths[i][j] = paths[i][j+1]+paths[i+1][j];
//        }
//        return paths[0][0];

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(i==0 || j==0)
                    paths[i][j] = 1;
                else
                    paths[i][j] = paths[i][j-1]+paths[i-1][j];
            }
        }
        return paths[m-1][n-1];
    }
}
