package com.mbhatia.algorithms.medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/course-schedule-ii/
public class CourseSchedule_II {
    //Paradigm used: Topological sort

    private void topologicalSortHelper(int numCourses, int currNode, List<Integer>[] edges, boolean[] visited,
                                       boolean[] visiting, List<Integer> order){
        if(currNode > numCourses || currNode < 0)
            return;
        visiting[currNode] = true;
        if(edges[currNode] != null){
            int length = edges[currNode].size();
            for(int i=0; i<length; ++i){
                if(!visited[edges[currNode].get(i)]){
                    if(visiting[edges[currNode].get(i)]){
                        //cyclic dependency found!
                        throw new IllegalArgumentException("Cycle found");
                    }
                    else
                        topologicalSortHelper(numCourses, edges[currNode].get(i), edges, visited, visiting, order);
                }
            }
        }

        visited[currNode] = true;
        visiting[currNode] = false;
        order.add(currNode);
    }

    private int[] topologicalSort(int numCourses, List<Integer>[] graph){
        if(numCourses <= 0 || graph == null)
            return null;

        List<Integer> sequence = new ArrayList<>(numCourses);
        boolean[] visited = new boolean[numCourses], visiting = new boolean[numCourses];

        for (int i=0; i<numCourses; ++i){
            if(!visited[i]){
                try {
                    topologicalSortHelper(numCourses, i, graph, visited, visiting, sequence);
                } catch (IllegalArgumentException iae){
                    return new int[0];
                }
            }
        }

        return sequence.stream().mapToInt(x -> x).toArray();
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0)
            return null;

        List<Integer>[] graph = new ArrayList[numCourses];

        for(int i=0; i<prerequisites.length; ++i){
            if(prerequisites[i] != null){
                int src = prerequisites[i][0];
                int dest = prerequisites[i][1];
                if(graph[src] == null)
                    graph[src] = new ArrayList<>();
                graph[src].add(dest);
            }
        }

        return topologicalSort(numCourses, graph);
    }

    public static void main(String[] args) {
        CourseSchedule_II obj = new CourseSchedule_II();
        int numCourses = 5;
        int[][] edges = new int[numCourses][];
        edges[0] = new int[]{1,0};
        edges[1] = new int[]{2,0};
        edges[2] = new int[]{3,1};
        edges[3] = new int[]{3,2};
        edges[4] = new int[]{0,1};
        int[] order = obj.findOrder(numCourses, edges);
        for (int node:order)
            System.out.print(node + " ");
    }
}
