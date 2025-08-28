package silver.s2_2630;

import java.util.*;
import java.io.*;

public class Main {
	static int N, countBlue, countWhite;
	static int[][] map;

	// blue = 1 / white = 0 / else = 10
	static int calc(int startY, int endY, int startX, int endX) {
		if (startY >= endY) {
			if (map[startY][startX] == 1) {
				countBlue++;
			} else {
				countWhite++;
			}

			return map[startY][startX];
		}

		int midY = (startY + endY) / 2;
		int midX = (startX + endX) / 2;

		int a = calc(startY, midY, startX, midX);
//		System.out.println(startY + " : " + midY + " : " + startX + " : " + midX + " >>> " + a);

		int b = calc(startY, midY, midX + 1, endX);
//		System.out.println(startY + " : " + midY + " : " + (midX + 1) + " : " + endX + " >>> " + b);

		int c = calc(midY + 1, endY, startX, midX);
//		System.out.println((midY + 1) + " : " + endY + " : " + startX + " : " + midX + " >>> " + c);

		int d = calc(midY + 1, endY, midX + 1, endX);
//		System.out.println((midY + 1) + " : " + endY + " : " + (midX + 1) + " : " + endX + " >>> " + d);

//		System.out.println(a + " |||" + b + " |||" + c + " |||" + d);
		if (a + b + c + d == 4) { // blue
			countBlue = countBlue - 3;
			return 1;
		}
		if (a + b + c + d == 0) { // white
			countWhite = countWhite - 3;
			return 0;
		}

		return 10;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_2630/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		calc(0, N - 1, 0, N - 1);

		System.out.println(countWhite);
		System.out.println(countBlue);
		br.close();
	}
}
