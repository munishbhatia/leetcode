package com.mbhatia.algorithms.hard;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-score-words-formed-by-letters/
public class MaxScoreWords {
    private boolean canFormWord(String word, int[] letterCount){
        int[] lettersCountAdj = Arrays.copyOf(letterCount, 26);
        for (char c : word.toCharArray()){
            if(lettersCountAdj[c-'a'] <= 0)
                return false;
            else
                lettersCountAdj[c-'a'] -= 1;
        }
        return true;
    }

    private int getWordScore(String word, int[] score){
        int wordScore = 0;
        for(char c : word.toCharArray())
            wordScore += score[c-'a'];
        return wordScore;
    }

    private void adjustLetterCount(int[] lettersCountAdj, String word) {
        for(char c : word.toCharArray()){
            lettersCountAdj[c-'a'] -= 1;
        }
    }

    private int _maxScoreWords(String[] words, int[] lettersCount, int[] score, int currIdx, int wordsCount){
        if(currIdx >= wordsCount)
            return 0;
        int scoreInclusive = 0, scoreExclusive = 0;
        if(canFormWord(words[currIdx], lettersCount)){
            scoreInclusive += getWordScore(words[currIdx], score);
            int[] lettersCountAdj = Arrays.copyOf(lettersCount, 26);
            adjustLetterCount(lettersCountAdj, words[currIdx]);
            scoreInclusive += _maxScoreWords(words, lettersCountAdj, score, currIdx+1, wordsCount);
        }
        scoreExclusive += _maxScoreWords(words, lettersCount, score, currIdx+1, wordsCount);
        return Math.max(scoreInclusive, scoreExclusive);
    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        if(words == null || words.length == 0)
            return 0;
        int wordsCount = words.length;
        int[] lettersCount = new int[26];

        for(char c : letters)
            lettersCount[c-'a'] +=1;

        return _maxScoreWords(words, lettersCount, score, 0, wordsCount);
    }

    public static void main(String[] args) {
        //Expected 23
        /*String[] words = new String[]{"dog","cat","dad","good"};
        char[] letters = new char[]{'a','a','c','d','d','d','g','o','o'};
        int[] score = new int[]{1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};*/

        //Expected 27
        /*String[] words = new String[]{"xxxz","ax","bx","cx"};
        char[] letters = new char[]{'z','a','b','c','x','x','x'};
        int[] score = new int[]{4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10};*/

        //Expected 0
        String[] words = new String[]{"leetcode"};
        char[] letters = new char[]{'l','e','t','c','o','d','e','x'};
        int[] score = new int[]{0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0};

        MaxScoreWords obj = new MaxScoreWords();
        System.out.println(obj.maxScoreWords(words, letters, score));
    }
}
