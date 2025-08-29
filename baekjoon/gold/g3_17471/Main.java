package bj_17471;

import java.util.*;
import java.io.*;

public class Main {
	static int N, totalSum;
	static int[] nums;
	static Set<Integer>[] g;
	
	void bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int[] groupNums = new int[N + 1];
		q.addFirst(start);
		
		while(!q.isEmpty()) {
			int cur = q.pollFirst();
			
			for (Iterator<Integer> next = g[cur].iterator(); next.hasNext();) {
				if(group.contains(next)) {
					
				}
				
			}
		}
	}
	
	static Set<Integer> group;
	static void subset(int idx) {
		if(idx > N) {
			//TODO
			return;
		}
		
		group.add(idx);
		subset(idx + 1);
		group.remove(idx);
		subset(idx + 1);
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_17471/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		g = new Set[N + 1];
		
		for (int i = 0; i <= N; i++) 
			g[i] = new HashSet<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
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
		
		group = new HashSet<>();
		
		int min = Integer.MAX_VALUE;
		
		
		System.out.println(min);
	}
}
