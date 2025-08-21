package d456.d4_3234;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, totalSum, result;
	static int[] arr;

	static void count(int[] a, int i, int leftSum, int rightSum) {
		if (leftSum < rightSum) return;
		
		if (i == N) {
			result++;
			return;
		}

		count(a, i + 1, leftSum + a[i], rightSum);
		count(a, i + 1, leftSum, rightSum + a[i]);
	}

	static int[] order;
	static boolean[] v;

	static void perm(int i) {
		if (i == N) {
			count(order, 0, 0, 0);
			return;
		}

		for (int j = 0; j < N; j++) {
			if (v[j])
				continue;

			v[j] = true;
			order[i] = arr[j];
			perm(i + 1);
			v[j] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d45/d4_3234/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			order = new int[N];
			v = new boolean[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				totalSum += arr[i];
			}

			result = 0;
			perm(0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
