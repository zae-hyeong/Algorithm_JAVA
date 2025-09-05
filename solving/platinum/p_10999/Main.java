package platinum.p_10999;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static long[] segtree, arr;

	static long getRangeSum(int rangeStart, int rangeEnd, int start, int end, int nodeIdx) {
		propagate(nodeIdx, start, end);

		if (end < rangeStart || rangeEnd < start)
			return 0;
		
		if (rangeStart <= start && end <= rangeEnd)
			return segtree[nodeIdx];


		int mid = (start + end) / 2;
		long l = getRangeSum(rangeStart, rangeEnd, start, mid, nodeIdx * 2);
		long r = getRangeSum(rangeStart, rangeEnd, mid + 1, end, nodeIdx * 2 + 1);

		return l + r;
	}

	static long[] lazy;

	static void propagate(int nodeIdx, int start, int end) {
		if (lazy[nodeIdx] == 0) // 전파할게 없으면 종료
			return;

		if (start < end) { // 리프노드가 아닌 경우 : 자식에게 전파
			lazy[nodeIdx * 2] += lazy[nodeIdx];
			lazy[nodeIdx * 2 + 1] += lazy[nodeIdx];
		}

		segtree[nodeIdx] += (end - start + 1) * lazy[nodeIdx]; // 노드 개수 * 변경값 만큼 lazy 값 할당

		lazy[nodeIdx] = 0; // 전파된 값 flush
	}

	static void updateRange(int rangeStart, int rangeEnd, int start, int end, int nodeIdx, long diff) {
		propagate(nodeIdx, start, end); // 범위에 갱신할 값 있으면 자식으로 전파

		if (end < rangeStart || rangeEnd < start)
			return;
		if (rangeStart <= start && end <= rangeEnd) { // 지금 범위가 타겟 범위 안에 있으면
			lazy[nodeIdx] += diff; // 업데이트 값 변경
			propagate(nodeIdx, start, end);
			return;
		}

		int mid = (start + end) / 2;
		updateRange(rangeStart, rangeEnd, start, mid, nodeIdx * 2, diff);
		updateRange(rangeStart, rangeEnd, mid + 1, end, nodeIdx * 2 + 1, diff);

		segtree[nodeIdx] = segtree[nodeIdx * 2] + segtree[nodeIdx * 2 + 1]; // 자식의 변경된 값 업데이트
	}

	static long init(int start, int end, int idx) {
		if (start >= end)
			return segtree[idx] = arr[start];

		int mid = (start + end) / 2;
		long l = init(start, mid, idx * 2);
		long r = init(mid + 1, end, idx * 2 + 1);

		return segtree[idx] = l + r;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("solving/bj_10999/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		K += Integer.parseInt(st.nextToken());

		arr = new long[N];
		for (int i = 0; i < N; i++)
			arr[i] = Long.parseLong(br.readLine());

		segtree = new long[Integer.highestOneBit(N) << 2];
		lazy = new long[Integer.highestOneBit(N) << 2];
		init(0, N - 1, 1);
//		System.out.println(Arrays.toString(segtree));

		int oper, start, end;
		long val;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			oper = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken()) - 1;
			end = Integer.parseInt(st.nextToken()) - 1;
			if (oper == 1) {
				val = Long.parseLong(st.nextToken());
				updateRange(start, end, 0, N - 1, 1, val);
//				System.out.println(Arrays.toString(segtree));
			} else {
				sb.append(getRangeSum(start, end, 0, N - 1, 1)).append("\n");
//				System.out.println(Arrays.toString(segtree));
			}
		}

		System.out.println(sb.toString());
		br.close();
	}
}
