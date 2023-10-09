package d2310;

import java.util.*;
import java.io.*;

class 과제Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        PriorityQueue<Study> pq = new PriorityQueue<>();
        Stack<Study> stack = new Stack<>();
        List<String> completeList = new ArrayList<>();

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            String sub[] = plans[i][1].split(":");
            int start = Integer.parseInt(sub[0]) * 60 + Integer.parseInt(sub[1]);
            int playTime = Integer.parseInt(plans[i][2]);

            pq.offer(new Study(name, start, playTime));
        }

        Study curStudy = pq.poll();
        String curName = curStudy.name;
        int curTime = curStudy.start;
        int curPlayTime = curStudy.playTime;

        while (!pq.isEmpty()) {
            Study study = pq.poll();
            int nextTime = study.start;

            // 현재 과제를 온전히 수행하고 바로 다음 과제로 이어지는 경우
            if ((curTime + curPlayTime) == nextTime) {
                completeList.add(curName);

                // 현재 과제를 할 수 있는만큼 수행하고 다음 과제로 이동해야 하는 경우
            } else if ((curTime + curPlayTime) > nextTime) {
                stack.push(new Study(curName, curPlayTime - (nextTime - curTime)));
            }

            // 다음 과제 사이에 잠시 멈춘 과제를 수행 (존재한다면)
            else {
                completeList.add(curName);
                if (!stack.isEmpty()) {
                    int leftTime = nextTime - (curTime + curPlayTime);
                    while (leftTime != 0) {
                        Study stopStudy = stack.pop();

                        if (leftTime >= stopStudy.playTime) {
                            leftTime = leftTime - stopStudy.playTime;
                            completeList.add(stopStudy.name);
                        } else {
                            stack.push(new Study(stopStudy.name, stopStudy.playTime - leftTime));
                            leftTime = 0;
                        }
                        if (stack.isEmpty()) leftTime = 0;
                    }
                }
            }

            curName = study.name;
            curTime = study.start;
            curPlayTime = study.playTime;

            if (pq.isEmpty()) completeList.add(curName);
        }

        while (!stack.isEmpty()) {
            completeList.add(stack.pop().name);
        }

        for (int i = 0; i < completeList.size(); i++) {
            answer[i] = completeList.get(i);
            System.out.println(answer[i]);
        }

        return answer;
    }

    static class Study implements Comparable<Study> {
        String name;
        int start;
        int playTime;

        Study(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }

        Study(String name, int playTime) {
            this.name = name;
            this.playTime = playTime;
        }

        @Override
        public int compareTo(Study o) {
            return this.start - o.start;
        }
    }
}

public class pg_과제진행하기 {

    public static void main(String[] args) {
        과제Solution solution = new 과제Solution();
        String[][] plans = {
                {"A", "11:50", "30"},
                {"B", "13:00", "20"},
                {"C", "13:10", "30"}
        };
        solution.solution(plans);
    }
}