package d2308;

import java.util.*;

class 숫자변환하기Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;

        boolean v[] = new boolean[y+1];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, 0));
        v[x] = true;

        while(!q.isEmpty()){

            Node node = q.poll();
            int num = node.num;
            int cnt = node.cnt;

            if(num == y) {
                answer = cnt;
                break;
            }

            for(int i = 0; i < 3; i++){
                int tmp = 0;
                if(i == 0){
                    tmp = num+n;
                }else if(i == 1){
                    tmp = num*2;
                }else {
                    tmp = num*3;
                }

                if(tmp <= y && !v[tmp]){
                    v[tmp] = true;
                    q.offer(new Node(tmp, cnt + 1));
                }
            }
        }

        return answer;
    }

    static class Node {
        int num;
        int cnt;
        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }
}

public class pg_숫자변환하기 {
    public static void main(String[] args) {
        숫자변환하기Solution solution = new 숫자변환하기Solution();
        solution.solution(10, 40, 5);
    }
}
