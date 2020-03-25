package com.mbhatia.algorithms.medium;

//https://leetcode.com/problems/palindromic-substrings/
public class PalindromicSubstringCount {
    public int countSubstrings(String s) {
        int count, length = s.length();
        boolean[][] DP = new boolean[length][length];

        //init count to length of the input string; every individual char is a palindromic substring
        count = length;

        //init DP to mark single chars as palindromes
        for(int i=0; i<length; ++i)
            DP[i][i] = true;

        //Find palindromic substrings
        for(int i=length-2; i>=0; --i){
            for(int j = i+1; j<length; ++j){
                boolean b = s.charAt(i) == s.charAt(j);
                if(j-i > 1){
                    DP[i][j] = (b && (DP[i + 1][j - 1]));
                }
                else if(j-i == 1){
                    DP[i][j] = b;
                }
                if(DP[i][j])
                    ++count;
            }
        }
        return count;
    }
}
