package com.mbhatia.algorithms.medium;

import java.util.*;

//https://leetcode.com/problems/insert-delete-getrandom-o1/
public class ConstantTimeRandomSet {
    class RandomizedSet {
        private Map<Integer, Integer> elementIndexMap;
        private List<Integer> elements;
        private int setSize;
        private Random rand;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            elementIndexMap = new HashMap(10);
            elements = new ArrayList<>(10);
            setSize = 0;
            rand = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(elementIndexMap.containsKey(val))
                return false;
            elements.add(val); //O(1) because always add to the end of the array list
            elementIndexMap.put(val, elements.size()-1);
            setSize+=1;
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!elementIndexMap.containsKey(val))
                return false;
            //swap element to remove with last element in the array list to get an O(1) complexity remove op in arraylist
            int removeIndex = elementIndexMap.get(val);
            int temp = elements.get(setSize-1);
            elements.set(setSize-1, val);
            elements.set(removeIndex, temp);

            //Update arraylist index for the swapped element in the map
            elementIndexMap.put(temp, removeIndex);

            //remove element
            elements.remove(setSize-1);
            elementIndexMap.remove(val);
            setSize-=1;
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int randomIndex = rand.nextInt(setSize);
            return elements.get(randomIndex);
        }
    }
}
