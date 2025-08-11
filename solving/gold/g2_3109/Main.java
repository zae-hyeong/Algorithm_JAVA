package gold.g2_3109;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] arr;
    static boolean[][] visited;
    static int count;
    static int[] dy = { -1, 0, 1 };

    static boolean isValid(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C && !visited[y][x] && arr[y][x] == '.';
    }

    static boolean dfsFrom(int startY, int startX) {
        visited[startY][startX] = true;
//        System.out.println("====" + startY + ":" + startX + "====");

        if (startX == R - 1) {
            count++;
            return true;
        }

        for (int d : dy) {
//            System.out.println((startY + d) + "::" + (startX + 1) +"::"+isValid(startY + 1, startX + d));
            if (isValid(startY + d, startX + 1))
                if (dfsFrom(startY + d, startX + 1))
                    return true;
        }

        visited[startY][startX] = false;
        return false;
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("./solving/gold/g2_3109/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) arr[i] = br.readLine().toCharArray();

//        for (char[] c : arr) System.out.println(Arrays.toString(c));

        int count = 0;
        for (int i = 0; i < R; i++) {
            if (dfsFrom(i, 0))
                count++;
//            for (boolean[] b : visited) System.out.println(Arrays.toString(b));
//            System.out.println("----------------------");
        }

        System.out.println(count);
        br.close();
    }
}