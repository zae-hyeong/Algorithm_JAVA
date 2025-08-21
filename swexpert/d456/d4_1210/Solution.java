package d456.d4_1210;

import java.io.*;
import java.util.*;

public class Solution {
	static int result, start;
	static int[][] ladder;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./solving/d45/d4_1210/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			ladder = new int[100][100];
			br.readLine();

			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if (ladder[i][j] == 2)
						start = j;
				}
			}

			int x = start, y = 99;
			while (y > 0) {
				if (x > 0 && ladder[y][x - 1] == 1)
					while (x > 0 && ladder[y][x - 1] == 1) {
						x--;
					}
				else if (x < 99 && ladder[y][x + 1] == 1) {
					while (x < 99 && ladder[y][x + 1] == 1) {
						x++;
					}
				}

				y--;
//				System.out.println("x : " + x + " / y : " + y);
			}

			sb.append("#").append(tc).append(" ").append(x).append("\n");
		}

		System.out.print(sb);
		br.close();
	}

}
