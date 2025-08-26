package d456.d4_3124;

import java.io.*;
import java.util.*;

public class Solution {
	static int V, E;
	static long mst;
	static List<int[]>[] g;

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int[] minCost = new int[V + 1];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		boolean[] v = new boolean[V + 1];

		pq.add(new int[] { 1, 0 });

		mst = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0], cost = cur[1];

			if (cost > minCost[node])
				continue;
			if (v[node])
				continue;

			v[node] = true;
			mst += cost;

			if (cnt++ == V - 1)
				break;

			for (int[] near : g[node]) {
				if (!v[near[0]] && minCost[near[0]] > near[1]) {
					minCost[near[0]] = near[1];
					pq.add(new int[] { near[0], near[1] });
				}
			}
		}
	}

	static int[] parent;

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;

		parent[rootB] = rootA;
		return true;
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static PriorityQueue<int[]> ppq;

	static void kruskal() {
		parent = new int[V + 1];
		for (int i = 0; i <= V; i++)
			parent[i] = i;

		int cnt = 0;
		mst = 0;
		while (!ppq.isEmpty()) {
			int[] edge = ppq.poll();
			int a = edge[0], b = edge[1], c = edge[2];

			if (union(a, b)) {
				mst += c;
				if (cnt++ == V - 1)
					return;
			} else
				continue;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_3124/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			g = new List[V + 1];
			for (int i = 0; i <= V; i++)
				g[i] = new ArrayList<>();

			int from, to, c;
			ppq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2])); // kruskal
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				ppq.add(new int[] { from, to, c }); // kruskal
//				g[from].add(new int[] { to, c });  //prim
//				g[to].add(new int[] { from, c });  //prim
			}

			mst = 0;
//			prim();
			kruskal();

			sb.append("#").append(tc).append(" ").append(mst).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
