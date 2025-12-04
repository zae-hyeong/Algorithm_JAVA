package gold.g3_23288_주사위굴리기2;

import java.io.*;
import java.util.*;

public class Main {
	static int H, W, K;
	static int[][] map;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./solving/gold/g3_23288_주사위굴리기2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();

        System.out.println(-1);
    }
}
