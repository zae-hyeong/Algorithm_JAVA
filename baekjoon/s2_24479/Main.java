package s2_24479;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[] visited;
	static Graph g;
	
	public static void dfs(int V, int cnt) {
		visited[V] = cnt;
		
		for(int nextNode: g.hm.get(V)) {
			if(visited[nextNode] > 0) continue;
			
			dfs(nextNode, cnt + 1);
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
		
		dfs(R, 1);
		
		for (int i = 1; i < visited.length; i++) {
			sb.append(visited[i]).append("\n");
		}
		
		System.out.println(sb.toString());

		br.close();
	}
}
