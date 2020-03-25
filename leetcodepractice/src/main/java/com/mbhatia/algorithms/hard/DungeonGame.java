package com.mbhatia.algorithms.hard;

//https://leetcode.com/problems/dungeon-game/
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0)
            return 0;
        int length = dungeon.length, width = dungeon[0].length;
        int[][] DP = new int[length][width];

        //Build solution bottom up
        if(dungeon[length-1][width-1] > 0){
            DP[length-1][width-1] = 0;
        }
        else { //dungeon[length-1][width-1] <= 0
            DP[length-1][width-1] = Math.abs(dungeon[length-1][width-1]) + 1;
        }

        //Fill last row of DP; only move possible is right
        for(int j=width-2; j>=0; --j){
            int ref = DP[length-1][j+1];
            int additive = (ref>0)?0:1;
            DP[length-1][j] = (dungeon[length-1][j]-ref > 0) ? 0
                                :Math.abs(dungeon[length-1][j]-ref)+additive;
        }

        //Fill last column of DP; only move possible is down
        for(int i=length-2; i>=0; --i){
            int ref = DP[i+1][width-1];
            int additive = (ref>0)?0:1;
            DP[i][width-1] = (dungeon[i][width-1]-ref > 0) ? 0
                    :Math.abs(dungeon[i][width-1]-ref)+additive;
        }

        //Fill rest of the DP matrix
        for(int i=length-2; i>=0; --i){
            for(int j=width-2; j>=0; --j){
                int ref = DP[i+1][j];
                int additive = (ref>0)?0:1;
                int down = (dungeon[i][j]-ref > 0) ? 0
                        :Math.abs(dungeon[i][j]-ref)+additive;

                ref = DP[i][j+1];
                additive = (ref>0)?0:1;
                int right = (dungeon[i][j]-ref > 0) ? 0
                        :Math.abs(dungeon[i][j]-ref)+additive;

                DP[i][j] = Math.min(down, right);
            }
        }

        return DP[0][0];
    }

    public static void main(String[] args) {
        DungeonGame obj = new DungeonGame();
        int[][] dungeon = new int[][]{{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        System.out.println(obj.calculateMinimumHP(dungeon));
    }
}
