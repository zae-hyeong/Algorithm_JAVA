package d45.d4_5643;

import java.util.*;
import java.io.*;

class Graph  {
	int N;
	ArrayList<Integer>[] g;
	Graph(int N) {
		this.N = N;
		this.g = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			this.g[i] = new ArrayList<>();
		}
	}
	
	void connect(int from, int to) {
		this.g[from].add(to);
	}
	
	static int count = 0;
	int dfsFrom(int start) {
		boolean[] v = new boolean[this.N + 1];
		count = 0;
		
		dfs(start, v);
		
		return count;
	}
	
	void dfs(int n, boolean[] v) {
		count++;
		for (int next: this.g[n]) {
			if(v[next]) continue;
			
			v[next] = true;
			dfs(next, v);
		}
	}
}

public class Solution {
	static int N, M, result;
	static Graph g, reverseG;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/d45/d4_5643/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			g = new Graph(N);
			reverseG = new Graph(N);
			
			int from, to;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				
				g.connect(from, to);
				reverseG.connect(to, from);
			}
			
			for (int i = 1; i <= N; i++) {
//				System.out.println("------------------------" + i);
//				System.out.println("g count : " + g.dfsFrom(i));
//				System.out.println("rg count : " + reverseG.dfsFrom(i));
				if(g.dfsFrom(i) +  reverseG.dfsFrom(i) - 1 == N) {
					result++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
