package gold.g2_1949_우수마을;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] v;
	static List<Integer>[] graph;
	static int[][] dp;
	static int[] population;
	
	static void dfs(int parent) {	
		for(int child: graph[parent]) {
			if(v[child]) continue;
			
			v[child] = true;
			
			// 부모 꺼짐 + 영향 없음 -> 자식은 무조건 켜야 함
			dp[child][2] = Math.max(dp[child][2], dp[parent][0] + population[child]);
			
			// 부모 꺼짐 + 영향 있음 + 자식 켬
			dp[child][2] = Math.max(dp[child][2], dp[parent][0] + population[child]);
			
			// 부모 꺼짐 + 영향 있음 + 자식 끔
			dp[child][0] = Math.max(dp[child][0], dp[parent][1]);
			
			// 부모 켜짐 -> 자식은 무조건 꺼야 함
			dp[child][0] = Math.max(dp[child][0], dp[parent][2]);
			
			dfs(child);
			
			v[child] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_1949_우수마을/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		v = new boolean[N + 1];
		dp = new int[N + 1][3];
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

		int result = 0;
		v[1] = true;
		dp[1][2] = population[1];
		dfs(1);
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 3; j++) {
				result = Math.max(result, dp[i][j]);
			}
			System.out.println(Arrays.toString(dp[i]));
		}
		
		System.out.println(result);
		br.close();
	}
}
