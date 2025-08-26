package gold.g1_10868;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static long[] nums;
	static long[] minSegTree;

	static long makeSegTree(int rangeStart, int rangeEnd, int idx) {
		if (rangeStart >= rangeEnd)
			return minSegTree[idx] = nums[rangeStart];

		int mid = (rangeStart + rangeEnd) / 2;

		return minSegTree[idx] = Math.min(makeSegTree(rangeStart, mid, idx * 2),
				makeSegTree(mid + 1, rangeEnd, idx * 2 + 1));
	}

	static long getMin(int rangeStart, int rangeEnd, int start, int end, int idx) {
		if (end < rangeStart || rangeEnd < start)
			return Long.MAX_VALUE;

		if (rangeStart <= start && end <= rangeEnd)
			return minSegTree[idx];

		int mid = (start + end) / 2;

		return Math.min(getMin(rangeStart, rangeEnd, start, mid, idx * 2),
				getMin(rangeStart, rangeEnd, mid + 1, end, idx * 2 + 1));
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_10868/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		// 트리 크기 구하는 방법 1
		int size = Integer.highestOneBit(N) << 2;
		minSegTree = new long[size];
		// 트리 크기 구하는 방법 2
		// int size = 4*N;

		makeSegTree(1, N, 1);

		int start, end;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			sb.append(getMin(start, end, 1, N, 1)).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
