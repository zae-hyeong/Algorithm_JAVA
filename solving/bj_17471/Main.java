package bj_17471;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static ArrayList<Integer>[] g;
	static int min, totalSum, sum;
	
	static boolean[] visited;
	static void bfsFrom(int start) {
		ArrayDeque<Integer> q= new ArrayDeque<>();
		q.add(start);
		visited[start] = true;
		sum = 0;
		
		while(!q.isEmpty()) {
			int cur = q.pollFirst();
			sum+= nums[cur];
			if(min > Math.abs(totalSum - sum)) System.out.println(min +":"+(totalSum - sum));
			min = Math.min(min, Math.abs(totalSum - sum));
			
			
			for (int near: g[cur]) {
				if(visited[near]) continue;
				
				visited[near] = true;
				q.add(near);
			}
		}
		System.out.println(Arrays.toString(visited));
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_17471/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		g = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) 
			g[i] = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		totalSum = 0;
		
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			totalSum += nums[i];
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				g[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			bfsFrom(i);
		}
		
		System.out.println(min);
	}
}
