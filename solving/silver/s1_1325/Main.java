package silver.s1_1325;

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
	}

	ArrayList<Integer> get(int n) {
		return arr[n];
	}
}

public class Main {
	static int N, M;
	static Graph g;
	static int[] cntArr;
	static boolean[] visited;

	static void dfs(int n) {
		visited[n] = true;

		for (int next : g.get(n)) {
			if (visited[next])
				continue;

			cntArr[next]++;
			dfs(next);
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/silver/s1_1325/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		g = new Graph(N);

		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			g.connect(from, to);
		}

		cntArr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			dfs(i);
			System.out.println(Arrays.toString(cntArr));
		}
		
		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, cntArr[i]);
		}
		
		for (int i = 1; i <= N; i++) {
			if(cntArr[i] == max) sb.append(i).append(" ");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}