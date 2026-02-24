package gold.g3_2533_SNS;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] dp;
	static boolean[] v;
	static List<Integer>[] graph;
	
	static void dfs(int node) {
		v[node] = true;
		dp[node][0] = 0;
		dp[node][1] = 1;
		
		for(int child: graph[node]) {
			if(!v[child]) {
				dfs(child);
				dp[node][0] += dp[child][1];
				dp[node][1] += Math.min(dp[child][0], dp[child][1]);;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_2533_SNS/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		v = new boolean[N + 1];
		dp = new int[N + 1][2];
		
		String input;
		StringTokenizer st;
		int a, b;
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dfs(1);

		System.out.println(Math.min(dp[1][0], dp[1][1]));
		br.close();
	}
}
