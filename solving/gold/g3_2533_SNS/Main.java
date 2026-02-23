package gold.g3_2533_SNS;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] v, on, inEffect;
	static List<Integer>[] graph;
	
	static void dfs(int node, int parent) {
		System.out.format("parent=%d, node=%d, on[parent]=%b, inEffect[parent]=%b\n", parent, node, on[parent], inEffect[parent]);
		v[node] = true;
		
		if(on[parent]) { // 부모가 켜져 있음 -> 스킵
			for(int child: graph[node]) {
				if(!v[child]) dfs(child, node);
			}
		} else if (inEffect[parent]) { // 부모가 영향권 안에 있음 -> 스킵
			boolean isLeaf = true;
			
			for(int child: graph[node]) {
				if(!v[child]) {
					isLeaf = false;
					dfs(child, node);
				}
			}
			
			if(isLeaf) on[node] = true; // 자신이 Leaf이면 -> 자신을 ON하기
		} else { // 부모가 영향권 안에 없음 -> 자신을 ON하기
			on[node] = true;
			for(int child: graph[node]) {
				if(v[child]) continue;
				
				inEffect[child] = true;
				dfs(child, node);
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
		on = new boolean[N + 1];
		inEffect = new boolean[N + 1];
		
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
		
		// 루트(1번)을 켜기
		on[1] = true;
		v[1] = true;
		for(int child: graph[1]) {
			inEffect[child] = true;
			dfs(child, 1);
		}
		
		System.out.print("ON : ");
		for(int i = 1; i <= N; i++) if(on[i]) System.out.print(i + ", ");
		System.out.println("\n==============================");
		
		// 개수 세기
		int result = 0, count = 0;
		for(int i = 1; i <= N; i++) if(on[i]) count++;
		result = count;
		
		// 루트(1번)을 끄기(초기화)
		Arrays.fill(on, false);
		Arrays.fill(inEffect, false);
		Arrays.fill(v, false);
		v[1] = true;
		for(int child: graph[1]) {
			dfs(child, 1);
		}
		
		// 개수 세기
		count = 0;
		for(int i = 1; i <= N; i++) if(on[i]) count++;
		result = Math.min(result, count);
		
		System.out.print("ON : ");
		for(int i = 1; i <= N; i++) if(on[i]) System.out.print(i + ", ");
		System.out.println();

		System.out.println(result);
		br.close();
	}
}
