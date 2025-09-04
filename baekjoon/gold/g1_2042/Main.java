package gold.g1_2042;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static long[] arr;
	static long[] segtree;

	static long getRangeSum(int rangeLeft, int rangeRight, int left, int right, int idx) {
		if (rangeLeft <= left && right <= rangeRight) {
			return segtree[idx];
		}

		if (rangeLeft > right || left > rangeRight)
			return 0;

		int mid = (left + right) / 2;

		long l = 0, r = 0;
		l = getRangeSum(rangeLeft, rangeRight, left, mid, idx * 2);
		r = getRangeSum(rangeLeft, rangeRight, mid + 1, right, idx * 2 + 1);

		return l + r;
	}

	static void change(int idx, long originNum, long num, int left, int right, int segIdx) {
		if (idx < left || idx > right)
			return;

		segtree[segIdx] = segtree[segIdx] - originNum + num;
		if (left >= right)
			return;

		int mid = (left + right) / 2;

		change(idx, originNum, num, left, mid, segIdx * 2);
		change(idx, originNum, num, mid + 1, right, segIdx * 2 + 1);
	}

	static long init(int left, int right, int idx) {
		if (left >= right) {
			return segtree[idx] = arr[left];
		}

		int mid = (left + right) / 2;

		long l = init(left, mid, idx * 2);
		long r = init(mid + 1, right, idx * 2 + 1);

		return segtree[idx] = l + r;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/bj_2042/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		segtree = new long[Integer.highestOneBit(N) << 2];
		init(0, N - 1, 1);

		int a, b;
		long c;
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());

			if (a == 2) {
				sb.append(getRangeSum(b - 1, (int) c - 1, 0, N - 1, 1)).append("\n");
			} else {
				change(b - 1, arr[b - 1], c, 0, N - 1, 1);
				arr[b - 1] = c;
			}
//			System.out.println(Arrays.toString(segtree));

		}

		System.out.println(sb.toString());
		br.close();
	}
}
