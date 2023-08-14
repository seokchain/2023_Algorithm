package d2308;

import java.util.*;

class 택배배달과수거하기Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Node> Ds = new Stack<>();
        Stack<Node> Ps = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) Ds.add(new Node(deliveries[i], i + 1));
            if (pickups[i] != 0) Ps.add(new Node(pickups[i], i + 1));
        }

        while (!Ds.isEmpty() || !Ps.isEmpty()) {
            int dist = 0;
            // 배달
            int tmp = cap;
            while (tmp != 0) {
                if (Ds.isEmpty()) break;

                Node node = Ds.pop();
                int dnum = node.num;
                int ddist = node.dist;

                dist = Math.max(dist, ddist);
                if (tmp >= dnum) {
                    tmp -= dnum;
                } else {
                    Ds.add(new Node(dnum - tmp, ddist));
                    tmp = 0;
                }
            }
            // 수거
            tmp = cap;
            while (tmp != 0) {

                if (Ps.isEmpty()) break;

                Node node = Ps.pop();
                int pnum = node.num;
                int pdist = node.dist;
                dist = Math.max(dist, pdist);

                if (tmp >= pnum) {
                    tmp -= pnum;
                } else {
                    Ps.add(new Node(pnum - tmp, pdist));
                    tmp = 0;
                }
            }

            answer += dist * 2;
        }

        return answer;
    }

    static class Node {
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}