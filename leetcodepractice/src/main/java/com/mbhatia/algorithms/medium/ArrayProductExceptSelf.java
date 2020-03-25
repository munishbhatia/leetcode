package com.mbhatia.algorithms.medium;

//https://leetcode.com/problems/product-of-array-except-self/
public class ArrayProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length, prevProduct = 1;
        int[] result = new int[length];

        //init result elems to 1
        for(int i=0; i< length; ++i)
            result[i] = 1;

        //Walk through the array left to right and calculate running product till the previous element
        for(int i=0; i<length; ++i){
            result[i] *= prevProduct;
            prevProduct *= nums[i];
        }

        //Walk through the array right to left and calculate running product till the previous element
        //Initialize previous product again before the iteration
        prevProduct = 1;
        for(int i=length-1; i>=0 ; --i){
            result[i] *= prevProduct;
            prevProduct *= nums[i];
        }

        return result;
    }
}
