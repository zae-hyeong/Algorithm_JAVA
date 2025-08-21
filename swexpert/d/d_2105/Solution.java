package d.d_2105;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, result = -1;
	static int[][] arr;

	static void comb(int y, int x) {
		for (int a = 1; a < N - 1; a++) {
			for (int b = 1; b < N - 1; b++) {
				if (y + a + b < N && x + a < N && x - b >= 0) {
					int tmp = rotateFrom(y, x, a, b);
//					System.out.println(a + " : " + b + " >> " + tmp);
					result = Math.max(result, tmp);
				}
			}
		}
	}

	static int rotateFrom(int sy, int sx, int a, int b) {
		int y = sy;
		int x = sx;
//		System.out.println("yyyyxxxx" + y + " : " + x + " aaabbb::: " + a + " : " + b);

		Set<Integer> set = new HashSet<>();

//		while (y + a + b < N && x + a < N && x - b >= 0) {

		for (int i = 0; i < a; i++, x++, y++) {
			if (set.contains(arr[y + 1][x + 1]))
				return -1;

			set.add(arr[y + 1][x + 1]);
		}

		for (int i = 1; i <= b; i++, y++, x--) {
			if (set.contains(arr[y + 1][x - 1]))
				return -1;
			set.add(arr[y + 1][x - 1]);
		}

		for (int i = 1; i <= a; i++, y--, x--) {
			if (set.contains(arr[y - 1][x - 1]))
				return -1;
			set.add(arr[y - 1][x - 1]);
		}

		for (int i = 1; i <= b; i++, y--, x++) {
			if (set.contains(arr[y - 1][x + 1]))
				return -1;
			set.add(arr[y - 1][x + 1]);
		}

		return set.size();
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/d/d_2105/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			result = -1;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
//					System.out.println("----------yx : " + i + " : " + j);
					comb(i, j);
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
