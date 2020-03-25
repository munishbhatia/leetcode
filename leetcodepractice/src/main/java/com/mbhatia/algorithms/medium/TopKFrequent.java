package com.mbhatia.algorithms.medium;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequent {
    private class ElementFrequency implements Comparable{
        int element;
        int frequency;

        public ElementFrequency(int element, int frequency) {
            this.element = element;
            this.frequency = frequency;
        }

        public int getElement() {
            return element;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof ElementFrequency))
                throw new IllegalArgumentException("Provided element is of class " + o.getClass());
            return this.getFrequency() - ((ElementFrequency) o).getFrequency();
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        int length = nums.length;
        List<Integer> kFrequentList;
        Map<Integer, Integer> itemFrequencyMap = new HashMap<>(length);
        PriorityQueue<ElementFrequency> kFrequentMinHeap = new PriorityQueue<>(k);

        //Fill the frequency map
        for (int num : nums) {
//            itemFrequencyMap.put(nums[i],itemFrequencyMap.containsKey(nums[i])?itemFrequencyMap.get(nums[i])+1:1);
            if (itemFrequencyMap.containsKey(num)) {
                itemFrequencyMap.put(num, itemFrequencyMap.get(num) + 1);
            } else {
                itemFrequencyMap.put(num, 1);
            }
        }

        //Make a MinHeap for the most frequent 'k' elements in itemFrequencyMap
        for (Map.Entry<Integer, Integer> itemFrequencyMapEntry : itemFrequencyMap.entrySet()) {
            if(kFrequentMinHeap.size() == k){
                if(kFrequentMinHeap.peek().getFrequency() < itemFrequencyMapEntry.getValue()){
                    kFrequentMinHeap.poll();
                    kFrequentMinHeap.add(new ElementFrequency(itemFrequencyMapEntry.getKey(), itemFrequencyMapEntry.getValue()));
                }
            }
            else
                kFrequentMinHeap.add(new ElementFrequency(itemFrequencyMapEntry.getKey(), itemFrequencyMapEntry.getValue()));
        }

        //Create the list from the MinHeap
        kFrequentList = kFrequentMinHeap.stream().map(ElementFrequency::getElement).collect(Collectors.toList());

        //return list
        return kFrequentList;
    }

    public static void main(String[] args) {
        TopKFrequent controller = new TopKFrequent();
        System.out.println(controller.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }
}
