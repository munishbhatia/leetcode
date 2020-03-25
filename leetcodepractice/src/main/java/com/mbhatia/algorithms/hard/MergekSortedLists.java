package com.mbhatia.algorithms.hard;

import com.mbhatia.algorithms.common.SinglyLinkedListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/merge-k-sorted-lists/
public class MergekSortedLists {
    public SinglyLinkedListNode mergeKLists(SinglyLinkedListNode[] lists) {
        int numInputLists = lists.length;
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(HeapNode::getVal));
        SinglyLinkedListNode mergedHead = null, mergedTail = null;

        //Set up initial heap with the head elements of the input lists
        for(int i=0; i<numInputLists; ++i){
            if(lists[i] != null){
                minHeap.offer(new HeapNode(lists[i].val, lists[i]));
            }
        }

        //If all three lists were empty to begin with the minHeap root would be empty/null at this point
        if(minHeap.size() == 0 || minHeap.peek() == null)
            return mergedHead;

        //Keep going till min heap has elements
        while(!minHeap.isEmpty()){
            HeapNode root = minHeap.poll();
            if(root != null){
                if(mergedHead != null){
                    mergedTail.next = new SinglyLinkedListNode(root.getVal());
                    mergedTail = mergedTail.next;
                }
                else {
                    mergedHead = new SinglyLinkedListNode(root.getVal());
                    mergedTail = mergedHead;
                }
                SinglyLinkedListNode nextNode = root.getListPointer().next;
                if(nextNode != null)
                    minHeap.offer(new HeapNode(nextNode.val, nextNode));
            }
        }
        return mergedHead;
    }
}

class HeapNode{ //implements Comparable{
    private int val;
    private SinglyLinkedListNode listPointer;

    public HeapNode(int val, SinglyLinkedListNode listPointer) {
        this.val = val;
        this.listPointer = listPointer;
    }

    public int getVal() {
        return val;
    }

    public SinglyLinkedListNode getListPointer() {
        return listPointer;
    }

//    @Override
//    public int compareTo(Object o) {
//        HeapNode node = (HeapNode)o;
//        return this.val-node.val;
//    }
}
