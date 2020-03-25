package com.mbhatia.algorithms.medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations/
public class IntegerPermutations {
    private void swapArrayElements(int[] nums, int lidx, int ridx){
        int temp = nums[lidx];
        nums[lidx] = nums[ridx];
        nums[ridx] = temp;
    }

    private List<List<Integer>> permuteHelper(int[] nums, int beginIndex, int endIndex){
        List<List<Integer>> result = new ArrayList<>();
        if(beginIndex == endIndex) { //Single element (sub) array
            List<Integer> sub = new ArrayList<>(1);
            sub.add(nums[beginIndex]);
            result.add(sub);
            return result;
        }
        for(int i=beginIndex; i<=endIndex; i++){
            swapArrayElements(nums, beginIndex, i);
            List<List<Integer>> subSolution = permuteHelper(nums, beginIndex+1, endIndex);
            for(List<Integer> subList:subSolution){
                subList.add(0, nums[beginIndex]);
            }
            result.addAll(subSolution);
            swapArrayElements(nums, beginIndex, i);
        }
        return result;
    }
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return permuteHelper(nums, 0, nums.length-1);
    }

    public static void main(String[] args) {
        IntegerPermutations obj = new IntegerPermutations();
        List<List<Integer>> result = obj.permute(new int[]{1, 2, 3, 4});
        System.out.println(result.size());
        for (List<Integer> list:result)
            System.out.println(list);
    }
}