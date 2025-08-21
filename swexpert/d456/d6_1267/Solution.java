package d456.d6_1267;

import java.util.*;
import java.io.*;

class Graph {
	List<Integer>[] g;

	Graph(int N) {
		g = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
	}

	void connect(int from, int to) {
		g[from].add(to);
	}
}

public class Solution {
	static int N, E;
	static int[] indegree;
	static Graph g;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/d456/d6_1267/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			g = new Graph(N);
			indegree = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			int from, to;
			for (int i = 0; i < E; i++) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());

				g.connect(from, to);
				indegree[to]++;
			}

			ArrayDeque<Integer> q = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0)
					q.addLast(i);
			}

			while (!q.isEmpty()) {
				int node = q.pollFirst();
				sb.append(node).append(" ");

				for (int next : g.g[node]) {
					if (--indegree[next] == 0) {
						q.addLast(next);
					}
				}
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
