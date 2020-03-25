package com.mbhatia.algorithms.hard;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return new int[0];

        Deque<Integer> window = new LinkedList<>();
        int length = nums.length, idx = 0;
        int[] output = new int[length-k+1];
        window.addFirst(nums[0]);

        //Set up initial window
        for(int i=1; i<k && i<length; i++){
            addItemToWindow(window, nums[i], k);
        }
        output[idx++] = window.peekFirst();

        for(int i=k; i<length; i++){
            if(!window.isEmpty() && isValidIndex(i-k, length) && window.peekFirst() == nums[i-k])
                window.pollFirst();
            addItemToWindow(window, nums[i], k);
            output[idx++] = window.peekFirst();
        }
        return output;
    }

    private void addItemToWindow(Deque<Integer> window, int num, int k) {
        while (!window.isEmpty() && num > window.peekFirst()){
            window.pollFirst();
        }
        while (!window.isEmpty() && window.peekLast() < num){
            window.pollLast();
        }
        window.addLast(num);
    }

    private boolean isValidIndex(int i, int length) {
        return (i >= 0 && i < length);
    }

    public static void main(String[] args) {
        SlidingWindowMax obj = new SlidingWindowMax();
//        int[] output = obj.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        int[] output = obj.maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
        for(int max:output)
            System.out.print(max + " ");
    }
}
