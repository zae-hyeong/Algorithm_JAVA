package d456.d5_1247;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, result;
	static int[] start, dest;
	static int[] order;
	static int[][] customers;

	static void calc() {
		int y, x, ny, nx, sum = 0;
		y = start[0];
		x = start[1];

		for (int i = 0; i < N; i++) {
			ny = customers[order[i]][0];
			nx = customers[order[i]][1];

			sum += Math.abs(ny - y) + Math.abs(nx - x);

			x = nx;
			y = ny;
		}
		
		ny = dest[0];
		nx = dest[1];

		sum += Math.abs(ny - y) + Math.abs(nx - x);
		result = Math.min(result, sum);
	}

	static boolean[] v;

	static void perm(int n) {
		if (n == N) {
			calc();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;

			v[i] = true;
			order[n] = i;
			perm(n + 1);
			v[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/d456/d5_1247/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int y, x;
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			start = new int[] { y, x };

			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			dest = new int[] { y, x };

			customers = new int[N][2];
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}

			order = new int[N];
			v = new boolean[N];
			perm(0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
