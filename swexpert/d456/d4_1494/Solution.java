package d456.d4_1494;

import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static long result;
	static int[][] arr;

	static boolean[] v;
	static int[] permArr;

	static void calc(int index, int cnt) {
		if (cnt == N / 2) {
			long x = 0, y = 0;
			for (int i = 0; i < N; i++) {
				if (v[i]) {
					y += arr[i][0];
					x += arr[i][1];
				} else {
					y -= arr[i][0];
					x -= arr[i][1];
				}
			}
			result = Math.min(result, x * x + y * y);
			return;
		}
		for (int i = index; i < N; i++) {
			if (!v[i]) {
				v[i] = true;
				calc(i + 1, cnt + 1);
				v[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_1494/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][2];
			permArr = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
//			for(long[] d : distArr) System.out.println(Arrays.toString(d));

			v = new boolean[N];

			result = Long.MAX_VALUE;
			calc(0, 0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
