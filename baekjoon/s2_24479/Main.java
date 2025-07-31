package s2_24479;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[] visited;
	static Graph g;

	static int cnt = 1;

	public static void dfs(int V) {
		visited[V] = cnt++;

		for (int nextNode : g.hm.get(V)) {
			if (visited[nextNode] == 0)
				dfs(nextNode);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		visited = new int[N + 1];
		g = new Graph(N);

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			g.connect(a, b);
		}

		g.sort();

		dfs(R);

		for (int i = 1; i < visited.length; i++) {
			sb.append(visited[i]).append("\n");
		}

		System.out.print(sb.toString());

		br.close();
	}
}
