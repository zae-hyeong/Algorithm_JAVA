package silver.s2_5567;

import java.io.*;
import java.util.*;

class Graph {
	ArrayList<Integer>[] arr;

	Graph(int N) {
		arr = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
	}

	void connect(int a, int b) {
		arr[a].add(b);
		arr[b].add(a);
	}

	ArrayList<Integer> get(int n) {
		return arr[n];
	}
}

public class Main {
	static int N, M, result;
	static Graph g;
	static Set<Integer> s;

	static void bfs(int i) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		boolean[] v = new boolean[N + 1];
		ad.add(new int[] { i, 0 });

		while (!ad.isEmpty()) {
			int[] nodeInfo = ad.poll();
			int node = nodeInfo[0];
			int depth = nodeInfo[1];

			if (v[node])
				continue;
			if (depth > 2)
				continue;

			s.add(node);

			v[node] = true;
			for (int next : g.get(node)) {
				if (!v[next])
					ad.add(new int[] { next, depth + 1 });

			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/s2_5567/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		g = new Graph(N);
		s = new HashSet<>();
		result = 0;

		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			g.connect(from, to);
		}


		bfs(1);
		System.out.println(s.size() - 1);
		br.close();
	}
}
