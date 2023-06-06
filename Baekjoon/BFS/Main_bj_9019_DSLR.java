package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_9019_DSLR {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Queue<State> q = new LinkedList<>();
            boolean v[] = new boolean[10000];

            q.offer(new State(A, ""));
            v[A] = true;

            while (!q.isEmpty()) {

                State state = q.poll();

                if (state.num == B) {
                    System.out.println(state.command);
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    String str[] = {"D", "S", "L", "R"};
                    int num = command(d, state.num);
                    if (!v[num]) {
                        v[num] = true;
                        q.offer(new State(num, state.command + str[d]));
                    }
                }
            }
        }
    }

    static class State {

        int num;
        String command;

        State(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }

    static int command(int cmd, int A) {

        if (cmd == 0) {
            return (A * 2) % 10000;

        } else if (cmd == 1) {
            if (A == 0) return 9999;
            else return A - 1;

        } else if (cmd == 2) {
            return (A * 10) % 10000 + (A / 1000);

        } else {
            return (A / 10) + (A % 10) * 1000;
        }
    }
}
