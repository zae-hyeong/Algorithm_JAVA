package gold.g2_1949_우수마을;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] v;
	static List<Integer>[] graph;
	static int[][] dp;
	static int[] population;
	
	static void dfs(int me) {
		v[me] = true;
		boolean isLeaf = true;

		// 나를 끔 
		for(int child: graph[me]) {
			if(v[child]) continue;
			
			isLeaf = false;
			
			if(dp[child][0] == 0 && dp[child][1] == 0) dfs(child);
			
			dp[me][0] += Math.max(dp[child][0], dp[child][1]);
		}
		
		// 나를 켬
		dp[me][1] = population[me];
		for(int child: graph[me]) {
			if(v[child]) continue;
			
			dp[me][1] += dp[child][0];
		}
		
		if(isLeaf) {
			dp[me][0] = 0;
			dp[me][1] = population[me];
		}
		
		v[me] = false;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_1949_우수마을/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		v = new boolean[N + 1];
		dp = new int[N + 1][2];
		population = new int[N + 1];
		graph = new List[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 인구 수 input
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>(); 
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 그래프 input
		int a, b;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}

		dfs(1);
		
//		for(int i = 1; i <= N; i++) System.out.println(i + ": " + Arrays.toString(dp[i]));
		
		System.out.println(Math.max(dp[1][0], dp[1][1]));
		br.close();
	}
}
