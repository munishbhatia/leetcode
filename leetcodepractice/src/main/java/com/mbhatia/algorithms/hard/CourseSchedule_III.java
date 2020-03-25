package com.mbhatia.algorithms.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/course-schedule-iii/
public class CourseSchedule_III {
    class Course
    {
        private int time;
        private int closeDay;

        public Course(int time, int closeDay) {
            this.time = time;
            this.closeDay = closeDay;
        }
    }

    //O(n*d) time, O(n*d) space solution
    //Gives either TLE or MLE errors
    //Paradigm: DP
    private int maxCoursesTaken(Course[] courses, int totalCourses,
                                int currCourseIndex, int currTime,
                                int[][] timeCoursesMap){
        if(currCourseIndex >= totalCourses || currTime > 10000)
            return 0;
        int maxCoursesTaken;
        if(timeCoursesMap[currCourseIndex][currTime] != -1)
            return timeCoursesMap[currCourseIndex][currTime];
        else{
            if(courses[currCourseIndex].time+currTime <= courses[currCourseIndex].closeDay){
                maxCoursesTaken = Math.max(maxCoursesTaken(courses, totalCourses, currCourseIndex+1,
                        currTime+courses[currCourseIndex].time, timeCoursesMap)+1,
                        maxCoursesTaken(courses, totalCourses, currCourseIndex+1, currTime, timeCoursesMap));
            }
            else
                maxCoursesTaken = maxCoursesTaken(courses, totalCourses, currCourseIndex+1, currTime, timeCoursesMap);
            timeCoursesMap[currCourseIndex][currTime] = maxCoursesTaken;
            return maxCoursesTaken;
        }
    }

    public int scheduleCourse(int[][] courses) {
        if(courses == null)
            return 0;
        int totalCourses = courses.length;
        Course[] coursesArray = new Course[totalCourses];

        for(int i=0; i<totalCourses; ++i){
            coursesArray[i] = new Course(courses[i][0], courses[i][1]);
        }
        Arrays.sort(coursesArray, (o1, o2) -> {
            if(o1.closeDay != o2.closeDay)
                return o1.closeDay-o2.closeDay;
            else
                return o1.time-o2.time;
        });

        int maxDays = coursesArray[totalCourses-1].closeDay+1;
        int[][] timeMaxCoursesMap = new int[totalCourses][maxDays];
        for(int i=0; i<totalCourses; ++i){
            for(int j=0; j<maxDays; ++j)
                timeMaxCoursesMap[i][j] = -1;
        }

        return maxCoursesTaken(coursesArray, totalCourses, 0, 0, timeMaxCoursesMap);
    }

    //O(nlogn) time, O(n) space solution
    //Accepted solution
    //Paradigm: Greedy Approach
    public int scheduleCoursePQ(int[][] courses){
        if(courses == null)
            return 0;
        int totalCourses = courses.length;
        if(totalCourses <= 0)
            return 0;

        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> timeMaxHeap = new PriorityQueue<>((a,b) -> b-a);
        int time = 0;

        for(int i=0; i<totalCourses; ++i){
            int courseTime = courses[i][0], courseCloseDay = courses[i][1];
            if(time+courseTime <= courseCloseDay){
                timeMaxHeap.offer(courseTime);
                time += courseTime;
            } else {
                if(!timeMaxHeap.isEmpty() && timeMaxHeap.peek() > courseTime){
                    time -= timeMaxHeap.poll();
                    if(time+courseTime <= courseCloseDay){
                        timeMaxHeap.offer(courseTime);
                        time += courseTime;
                    }
                }
            }
        }
        return timeMaxHeap.size();
    }

    public static void main(String[] args) {
        int[][] courses = new int[6][2];
        courses[0] = new int[]{100, 200};
        courses[1] = new int[]{200, 1300};
        courses[2] = new int[]{200, 350};
        courses[3] = new int[]{100, 350};
        courses[4] = new int[]{1000, 1250};
        courses[5] = new int[]{2000, 3200};
        CourseSchedule_III obj = new CourseSchedule_III();
        System.out.println(obj.scheduleCourse(courses));
    }
}
