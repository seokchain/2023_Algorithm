package d230404;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_bj_14710_빗물 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_14710.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int map[][] = new int[H][W];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            int high = Integer.parseInt(st.nextToken());
            for (int j = 0; j < high; j++) {
                map[j][i] = 1;
            }
        }

        int water = 0;

        for (int i = 0; i < H; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 1) {
                    if (stack.isEmpty()) {
                        stack.push(1);
                    } else if (stack.peek() == 1) {
                        continue;
                    } else if (stack.peek() == 0) {
                        while (stack.peek() != 1) {
                            stack.pop();
                            water++;
                        }
                    }
                } else if (map[i][j] == 0 && !stack.isEmpty()) {
                    stack.push(0);
                }
            }

        }
        System.out.println(water);
    }
}
