package d2310;

import java.util.*;

class 무인도여행Solution {

    static int R, C, map[][];
    static boolean[][] v;
    static int di[] = {0,0,1,-1};
    static int dj[] = {1,-1,0,0};

    public int[] solution(String[] maps) {
        int[] answer;
        R = maps.length;
        C = maps[0].length();

        map = new int[R][C];
        v = new boolean[R][C];
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < R; i++){
            String str = maps[i];
            for(int j = 0; j < C; j++){
                map[i][j] = str.charAt(j) - '0';
                if(map[i][j] == 40) v[i][j] = true;
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(v[i][j]) continue;
                list.add(bfs(i,j));
            }
        }

        if(list.size() == 0){
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[list.size()];
            for(int i = 0; i < list.size(); i++){
                answer[i] = list.get(i);
            }
            Arrays.sort(answer);
        }
        return answer;
    }

    static int bfs(int i, int j){

        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        v[i][j] = true;
        q.offer(new Node(i, j));

        while(!q.isEmpty()){

            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            cnt += map[x][y];

            for(int d = 0; d < 4; d++){
                int dx = x+di[d];
                int dy = y+dj[d];
                if(dx >= 0 && dx < R && dy >= 0 && dy < C && !v[dx][dy]){
                    v[dx][dy] = true;
                    q.offer(new Node(dx, dy));
                }
            }
        }
        return cnt;
    }

    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

public class pg_무인도여행 {

    public static void main(String[] args) {

        무인도여행Solution solution = new 무인도여행Solution();
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};
        solution.solution(maps);
    }
}
