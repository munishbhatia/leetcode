package com.mbhatia.algorithms.hard;

//https://leetcode.com/problems/cherry-pickup/
public class CherryPickup {
//    public int cherryPickup(int[][] grid) {
//        if(grid == null || grid[0] == null || grid[0].length == 0)
//            return 0;
//        int length = grid.length, width = grid[0].length;
//        int[][] cherryPickTopBottom = new int[length][width], cherryPickBottomUp = new int[length][width];
//        Direction[][] directionApproachedFrom = new Direction[length][width];
//
//        //Top to Bottom Iteration
//        for(int i=0; i<length; ++i){
//            for(int j=0; j<width; ++j){
//                if(grid[i][j] == -1){
//                    cherryPickTopBottom[i][j] = 0;
//                }
//                else if(grid[i][j] == 0){
//                    if(isValidIndex(i-1,j,length,width) || isValidIndex(i,j-1,length,width)) {
//                        int left = (isValidIndex(i - 1, j, length, width) && (grid[i - 1][j] != -1)) ? cherryPickTopBottom[i - 1][j] : 0;
//                        int top = (isValidIndex(i, j - 1, length, width) && (grid[i][j - 1] != -1)) ? cherryPickTopBottom[i][j - 1] : 0;
//                        if (left > top) {
//                            cherryPickTopBottom[i][j] = left;
//                            directionApproachedFrom[i][j] = Direction.Left;
//                        } else {
//                            cherryPickTopBottom[i][j] = top;
//                            directionApproachedFrom[i][j] = Direction.Top;
//                        }
//                    }
//                    else {
//                        cherryPickTopBottom[i][j] = 0;
//                        directionApproachedFrom[i][j] = Direction.Unapproachable;
//                    }
//                }
//                else { //grid[i][j] == 1
//                    if((isValidIndex(i-1,j,length,width) && grid[i-1][j] == -1)
//                            && (isValidIndex(i,j-1,length,width) && grid[i][j-1] == -1))
//                        cherryPickTopBottom[i][j] = 0; //There's no way to reach this cherry in top down iteration
//                    else if(!isValidIndex(i-1,j,length,width)
//                            && (isValidIndex(i,j-1,length,width) && grid[i][j-1] == -1))
//                        cherryPickTopBottom[i][j] = 0;
//                    else if((isValidIndex(i-1,j,length,width) && grid[i-1][j] == -1)
//                            && (!isValidIndex(i,j-1,length,width)))
//                        cherryPickTopBottom[i][j] = 0;
//                    else{
//                        cherryPickTopBottom[i][j] = 1 + Math.max((isValidIndex(i-1,j,length,width) && (grid[i-1][j]!=-1))?cherryPickTopBottom[i-1][j]:0,
//                                (isValidIndex(i,j-1,length,width) && (grid[i][j-1]!=-1))?cherryPickTopBottom[i][j-1]:0);
//                        grid[i][j] = 0; //Cherry has been picked
//                    }
//                }
//            }
//        }
//
//        //Bottom up iteration
//        for(int i=length-1; i>=0; --i){
//            for(int j=width-1; j>=0; --j){
//                if(grid[i][j] == -1){
//                    cherryPickBottomUp[i][j] = 0;
//                }
//                else if(grid[i][j] == 0){
//                    cherryPickBottomUp[i][j] = Math.max((isValidIndex(i+1,j,length,width) && (grid[i+1][j]!=-1))?cherryPickBottomUp[i+1][j]:0,
//                            (isValidIndex(i,j+1,length,width) && (grid[i][j+1]!=-1))?cherryPickBottomUp[i][j+1]:0);
//                }
//                else { //grid[i][j] == 1
//                    if((isValidIndex(i+1,j,length,width) && grid[i+1][j] == -1)
//                            && (isValidIndex(i,j+1,length,width) && grid[i][j+1] == -1))
//                        cherryPickBottomUp[i][j] = 0; //There's no way to reach this cherry in top down iteration
//                    else if(!isValidIndex(i+1,j,length,width)
//                            && (isValidIndex(i,j+1,length,width) && grid[i][j+1] == -1))
//                        cherryPickBottomUp[i][j] = 0;
//                    else if((isValidIndex(i+1,j,length,width) && grid[i+1][j] == -1)
//                            && (!isValidIndex(i,j+1,length,width)))
//                        cherryPickBottomUp[i][j] = 0;
//                    else{
//                        cherryPickBottomUp[i][j] = 1 + Math.max((isValidIndex(i+1,j,length,width) && (grid[i+1][j]!=-1))?cherryPickBottomUp[i+1][j]:0,
//                                (isValidIndex(i,j+1,length,width) && (grid[i][j+1]!=-1))?cherryPickBottomUp[i][j+1]:0);
//                        grid[i][j] = 0; //Cherry has been picked
//                    }
//                }
//            }
//        }
//
//        return cherryPickTopBottom[length-1][width-1] + cherryPickBottomUp[0][0];
//    }

    public int cherryPickup(int[][] grid) {
        if(grid == null || grid[0] == null || grid[0].length == 0)
            return 0;
        int length = grid.length, width = grid[0].length;
        int[][] cherryPickTopBottom = new int[length][width], cherryPickBottomUp = new int[length][width];
        Direction[][] directionApproachedFrom = new Direction[length][width];

        //Top to Bottom Iteration
        //Fill Start Block
        cherryPickTopBottom[0][0] = grid[0][0];

        //Fill Top Row
        for(int j=1; j<width; ++j){
            int i=0;
            if(grid[i][j] == -1){
                cherryPickTopBottom[i][j] = 0;
                directionApproachedFrom[i][j] = Direction.Unapproachable;
            }
            else { //grid[i][j] == 0 || grid[i][j] == 1
                if(grid[i][j-1] == -1){
                    cherryPickTopBottom[i][j] = 0;
                    directionApproachedFrom[i][j] = Direction.Unapproachable;
                }
                else {
                    directionApproachedFrom[i][j] = Direction.Left;
                    cherryPickTopBottom[i][j] = grid[i][j]==0?cherryPickTopBottom[i][j-1]:1+cherryPickTopBottom[i][j-1];
                }
            }
        }

        //Fill Leftmost Column
        for(int i=1; i<length; ++i){
            int j=0;
            if(grid[i][j] == -1){
                cherryPickTopBottom[i][j] = 0;
                directionApproachedFrom[i][j] = Direction.Unapproachable;
            }
            else { //grid[i][j] == 0 || grid[i][j] == 1
                if(grid[i-1][j] == -1){
                    cherryPickTopBottom[i][j] = 0;
                    directionApproachedFrom[i][j] = Direction.Unapproachable;
                }
                else {
                    directionApproachedFrom[i][j] = Direction.Top;
                    cherryPickTopBottom[i][j] = grid[i][j]==0?cherryPickTopBottom[i-1][j]:1+cherryPickTopBottom[i-1][j];
                }
            }
        }

        //Fill rest of the DP matrix
        for(int i=1; i<length; ++i){
            for(int j=1; j<width; ++j){
                if(grid[i][j] == -1){
                    cherryPickTopBottom[i][j] = 0;
                    directionApproachedFrom[i][j] = Direction.Unapproachable;
                }
                else {
                    if(grid[i-1][j] == -1 && grid[i][j-1] == -1){
                        cherryPickTopBottom[i][j] = 0;
                        directionApproachedFrom[i][j] = Direction.Unapproachable;
                    }
                    else {
                        int left = (grid[i-1][j] != -1)?cherryPickTopBottom[i-1][j]:0;
                        int top = (grid[i][j-1] != -1)?cherryPickTopBottom[i][j-1]:0;

                        if(left>top){
                            directionApproachedFrom[i][j] = Direction.Left;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?left:1+left;
                        }
                        else if(top>left){
                            directionApproachedFrom[i][j] = Direction.Top;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?top:1+top;
                        }
                        else if(grid[i-1][j] != -1){
                            directionApproachedFrom[i][j] = Direction.Left;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?left:1+left;
                        }
                        else {
                            directionApproachedFrom[i][j] = Direction.Top;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?top:1+top;
                        }
                    }
                }
            }
        }

        if(directionApproachedFrom[length-1][width-1] == Direction.Unapproachable)
            return 0;

        grid[0][0] = 0;
        grid[length-1][width-1] = 0;

        //Actually pick cherries on the path
        int i = length-1, j = width-1;
        while(!(i ==0 && j==0)){
            switch (directionApproachedFrom[i][j]){
                case Top: grid[i-1][j] = 0; i = i-1; break;
                case Bottom: grid[i+1][j] = 0; i = i+1; break;
                case Left: grid[i][j-1] = 0; j = j-1; break;
                case Right: grid[i][j+1] = 0; j = j+1; break;
            }
        }

        //Bottom up iteration
        //Fill End Block
        cherryPickTopBottom[length-1][width-1] = grid[length-1][width-1];

        //Fill Bottom Row
        for(j=width-2; j>=0; --j){
            i=length-1;
            if(grid[i][j] == -1){
                cherryPickTopBottom[i][j] = 0;
                directionApproachedFrom[i][j] = Direction.Unapproachable;
            }
            else { //grid[i][j] == 0 || grid[i][j] == 1
                if(grid[i][j+1] == -1){
                    cherryPickTopBottom[i][j] = 0;
                    directionApproachedFrom[i][j] = Direction.Unapproachable;
                }
                else {
                    directionApproachedFrom[i][j] = Direction.Right;
                    cherryPickTopBottom[i][j] = grid[i][j]==0?cherryPickTopBottom[i][j+1]:1+cherryPickTopBottom[i][j+1];
                }
            }
        }

        //Fill Rightmost Column
        for(i=length-2; i>=0; --i){
            j=width-1;
            if(grid[i][j] == -1){
                cherryPickTopBottom[i][j] = 0;
                directionApproachedFrom[i][j] = Direction.Unapproachable;
            }
            else { //grid[i][j] == 0 || grid[i][j] == 1
                if(grid[i+1][j] == -1){
                    cherryPickTopBottom[i][j] = 0;
                    directionApproachedFrom[i][j] = Direction.Unapproachable;
                }
                else {
                    directionApproachedFrom[i][j] = Direction.Bottom;
                    cherryPickTopBottom[i][j] = grid[i][j]==0?cherryPickTopBottom[i+1][j]:1+cherryPickTopBottom[i+1][j];
                }
            }
        }

        //Fill rest of the DP matrix
        for(i=length-2; i>=0; --i){
            for(j=width-2; j>=0; --j){
                //TODO Begin here
                if(grid[i][j] == -1){
                    cherryPickTopBottom[i][j] = 0;
                    directionApproachedFrom[i][j] = Direction.Unapproachable;
                }
                else {
                    if(grid[i-1][j] == -1 && grid[i][j-1] == -1){
                        cherryPickTopBottom[i][j] = 0;
                        directionApproachedFrom[i][j] = Direction.Unapproachable;
                    }
                    else {
                        int left = (grid[i-1][j] != -1)?cherryPickTopBottom[i-1][j]:0;
                        int top = (grid[i][j-1] != -1)?cherryPickTopBottom[i][j-1]:0;

                        if(left>top){
                            directionApproachedFrom[i][j] = Direction.Left;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?left:1+left;
                        }
                        else if(top>left){
                            directionApproachedFrom[i][j] = Direction.Top;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?top:1+top;
                        }
                        else if(grid[i-1][j] != -1){
                            directionApproachedFrom[i][j] = Direction.Left;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?left:1+left;
                        }
                        else {
                            directionApproachedFrom[i][j] = Direction.Top;
                            cherryPickTopBottom[i][j] = grid[i][j]==0?top:1+top;
                        }
                    }
                }
            }
        }

        return cherryPickTopBottom[length-1][width-1] + cherryPickBottomUp[0][0];
    }

    public static void main(String[] args) {
        CherryPickup obj = new CherryPickup();
        int[][] orchard = new int[][]{{0,1,-1},{1,0,-1},{1,1,1}};
        System.out.println(obj.cherryPickup(orchard));
    }
}

enum Direction{
    Left, Right, Top, Bottom, Unapproachable;
}
