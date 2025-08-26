package bj_14267;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] nums;
	static int[] result;
	

	static List<Integer>[] g;

	static void dfs(int idx, int cost) {
		result[idx] = cost;

		for (int near : g[idx]) {
			dfs(near, cost + nums[near]);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/bj_14267/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N + 1];
		result = new int[N + 1];
		g = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		int parent, startIdx = 0;
		for (int i = 1; i <= N; i++) {
//			parents[i] = Integer.parseInt(st.nextToken());
			parent = Integer.parseInt(st.nextToken());
			if (parent == -1) {
				startIdx = i;
				continue;
			}
			g[parent].add(i);
		}

		int n, w;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			nums[n] = w;
		}
		
		dfs(startIdx, nums[startIdx]);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
		}
//		System.out.println(Arrays.toString(result));

		System.out.println(sb.toString());
		br.close();
	}
}
