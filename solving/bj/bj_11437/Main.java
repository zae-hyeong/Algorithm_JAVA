package bj.bj_11437;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] parent, depth;
	static List<Integer>[] g;

	static int findLCS(int a, int b) {
		int depthA = depth[a], depthB = depth[b];

		while (depthA > depthB) {
			a = parent[a];
			depthA--;
		}
		
		while (depthA < depthB) {
		b = parent[b];
			depthB--;
		}
		
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}

		return a;
	}

	static void findDepth(int node, int d) {
		depth[node] = d;

		for (int next : g[node]) {
			findDepth(next, d + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_11437/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		g = new List[N + 1];
		for (int i = 0; i <= N; i++)
			g[i] = new ArrayList<>();

		int a, b, min, max;
		parent = new int[N + 1];
		depth = new int[N + 1];

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			min = Math.min(a, b);
			max = Math.max(a, b);

			g[min].add(max);
			parent[max] = min;
		}

		findDepth(1, 1);
//		System.out.println(Arrays.toString(depth));

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
//			System.out.println("---------------");
			System.out.println(findLCS(a, b));
		}

		
		br.close();
	}
}
