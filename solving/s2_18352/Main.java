package s2_18352;

import java.io.*;
import java.util.*;

class Graph {
	ArrayList<ArrayList<Integer>> arr = null;
	Graph(int N) {
		arr = new ArrayList<>(); 
		for (int i = 0; i <= N; i++) {
			arr.add(new ArrayList<>());
		}
	}
	
	void connect(int a, int b) {
		arr.get(a).add(b);
	}
	
	ArrayList<Integer> get(int a) {
		return this.arr.get(a);
	}
}

public class Main {
	static int N, M, K, START, count;
	static Graph g;
	static StringBuilder sb;
	
	static int bfsFrom(int a) {
		
		boolean[] visited = new boolean[N +1];
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		
		ad.offer(new int[] {a, 0});
		
		while(!ad.isEmpty()) {
			int[] cur = ad.poll();
			int node  = cur[0];
			
			if(visited[node]) continue;
			
			int depth = cur[1];
			visited[node] = true;
			
			if(depth ==K) {
				sb.append(node).append("\n");
				count++;
			} else if (depth > K) {
				continue;
			}
			
			for(int nearNodes : g.get(node)) {
				if(visited[nearNodes]) continue;
				
				ad.offer(new int[] {nearNodes, depth + 1});
			}
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/s2_18352/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		START = Integer.parseInt(st.nextToken());
		
		g = new Graph(N);
		
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			g.connect(a, b);
		}
		
		bfsFrom(START);
		
		if (count > 0)
			System.out.println(sb.toString());
		else 
			System.out.println(-1);
		
		br.close();
	}
}