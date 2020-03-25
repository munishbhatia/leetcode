package com.mbhatia.algorithms.hard;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingMatrixPath {
    private boolean isValidMatrixLocation(int i, int j, int length, int width){
        return (i>=0 && i<length && j>=0 && j<width);
    }

    private int _longestIncreasingMatrixPath(int[][] matrix, int[][] LIP, int length, int width, int ci, int cj){
        int currElem = matrix[ci][cj];

        if(!isValidMatrixLocation(ci, cj, length, width))
            return -1;
        if(LIP[ci][cj] != -1)
            return LIP[ci][cj];

        //Upper neighbour
        if(isValidMatrixLocation(ci-1, cj, length, width) && currElem > matrix[ci-1][cj]){
            LIP[ci][cj] = Math.max(LIP[ci][cj], _longestIncreasingMatrixPath(matrix, LIP, length, width, ci-1, cj)+1);
        }
        //Lower neighbour
        if(isValidMatrixLocation(ci+1, cj, length, width) && currElem > matrix[ci+1][cj]){
            LIP[ci][cj] = Math.max(LIP[ci][cj], _longestIncreasingMatrixPath(matrix, LIP, length, width, ci+1, cj)+1);
        }
        //Left neighbour
        if(isValidMatrixLocation(ci, cj-1, length, width) && currElem > matrix[ci][cj-1]){
            LIP[ci][cj] = Math.max(LIP[ci][cj], _longestIncreasingMatrixPath(matrix, LIP, length, width, ci, cj-1)+1);
        }
        //Right neighbour
        if(isValidMatrixLocation(ci, cj+1, length, width) && currElem > matrix[ci][cj+1]){
            LIP[ci][cj] = Math.max(LIP[ci][cj], _longestIncreasingMatrixPath(matrix, LIP, length, width, ci, cj+1)+1);
        }
        //If the elem is less than all it's neighbours, the length of the LIP beginning at the elem is 1 (elem itself)
        LIP[ci][cj] = Math.max(LIP[ci][cj], 1);

        return LIP[ci][cj];
    }

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        int length = matrix.length, width = matrix[0].length, LIPLength = Integer.MIN_VALUE;
        int[][] LIP = new int[length][width]; //LIP = Longest Increasing Path

        //Initialize LIP elements to -1
        for(int i=0; i<length; ++i){
            for(int j=0; j<width; ++j)
                LIP[i][j] = -1;
        }

        //Build the solution bottom up
        for(int i=length-1; i>=0; --i) {
            for (int j=width-1; j>=0; --j)
                _longestIncreasingMatrixPath(matrix, LIP, length, width, i, j);
        }

        //Calculate max length LIP
        for(int i=0; i<length; ++i){
            for(int j=0; j<width; ++j)
                LIPLength = Math.max(LIP[i][j], LIPLength);
        }
        return LIPLength;
    }

    public static void main(String[] args) {
        LongestIncreasingMatrixPath obj = new LongestIncreasingMatrixPath();
//        int[][] input = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        int[][] input = new int[][]{{3,4,5},{3,2,5},{2,2,1}};
        System.out.println(obj.longestIncreasingPath(input));
    }
}
