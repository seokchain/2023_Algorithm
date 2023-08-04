package d2308;

class 혼자서하는틱텍토Solution {
    public int solution(String[] board) {
        int answer = 1;
        char map[][] = new char[3][3];

        int O = 0;
        int X = 0;
        int SO = 0;
        int SX = 0;

        for (int i = 0; i < 3; i++) {
            String str = board[i];
            for (int j = 0; j < 3; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'X') X++;
                else if (map[i][j] == 'O') O++;
            }
        }

        // X > O 또는 O-1 > X 이면 0
        if (X > O || O - 1 > X) answer = 0;

        if (O >= 3) {
            SO = check('O', map);
        }

        if (X >= 3) {
            SX = check('X', map);
        }

        // X, O 둘 다 승리할 수 없음.
        if (SO == 1 && SX == 1) answer = 0;
        // X가 후공이기 때문에 X가 승리했을 때 O와X 크기는 같아야 함.
        if (SX == 1 && O > X) answer = 0;
        // O가 선공이기 때문에 O가 승리했을 때 O와X 크기는 같을 수 없음.
        if (SO == 1 && O == X) answer = 0;

        return answer;
    }

    static int check(char ch, char[][] map) {
        int res = 0;

        for (int i = 0; i < 3; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < 3; j++) {
                if (map[j][i] == ch) row++;
                if (map[i][j] == ch) col++;
            }
            if (row == 3) res++;
            if (col == 3) res++;
        }

        if (map[0][0] == ch && map[1][1] == ch && map[2][2] == ch) res++;
        if (map[0][2] == ch && map[1][1] == ch && map[2][0] == ch) res++;

        return res;
    }
}

public class pg_혼자서하는틱텍토 {
    public static void main(String[] args) {
        혼자서하는틱텍토Solution solution = new 혼자서하는틱텍토Solution();
        String[] board = {"OXO", "XOX", "OXO"};
        solution.solution(board);
    }
}
