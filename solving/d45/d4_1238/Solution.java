package d45.d4_1238;

import java.util.*;
import java.io.*;

class Graph {
	List<Integer>[] g;
	int N;
	
	Graph() {
		this.N = 101;
		this.g = new ArrayList[this.N];
		for (int i = 1; i <= 100; i++) {
			this.g[i] = new ArrayList<>();
		}
	}
	
	void connect (int from, int to) {
		this.g[from].add(to);
	}
	
	int bfsFrom(int start) {
		boolean[] v = new boolean[N +1];
		int[] depth = new int[N+1];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.addLast(new int[] {start, 0}); // node, depth
		v[start] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.pollFirst();
			depth[cur[0]] = cur[1];
			
			for(int next: this.g[cur[0]]) {
				if(v[next]) continue;
				
				q.addLast(new int[]{next, cur[1] + 1});
				v[next] = true;
			}
		}
		
//		System.out.println(Arrays.toString(depth));
		int max = 0;
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if(max <= depth[i]) {
				max = depth[i];
				result = i;
			}
		}
		return result;
	}
}

public class Solution {
	static int N, START;
	static int result;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d45/d4_1238/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			START = Integer.parseInt(st.nextToken());
			
			Graph g = new Graph();
			
			int from, to;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				
				g.connect(from, to);
			}
			
			result = g.bfsFrom(START);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
