package gold.g3_1238_파티;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, X;
	static List<int[]>[] graph;
	static int[] dists;
	static boolean[] v;
	
	public static void dijkstra(int from, int to) {
		int[] tmpDists = new int[N + 1];
		v = new boolean[N + 1];
		Arrays.fill(tmpDists, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		
		tmpDists[from] = 0;
		pq.add(new int[] {from, 0});
		
		int[] cur;
		int node, dist;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			node = cur[0];
			dist = cur[1];
			
			if(node == to) {
				dists[from] += dist;
				break;
			}
			
			if(v[node]) continue;
			
			v[node] = true;
			
			for(int[] next: graph[node]) {
				if(!v[next[0]] && (tmpDists[next[0]] > dist + next[1])) {
					tmpDists[next[0]] = dist + next[1];
					pq.add(new int[] {next[0], tmpDists[next[0]]});
				}
			}
		}
		
		//System.out.println(Arrays.toString(tmpDists));
		
		if(to == -1) {
			for(int i = 1; i <= N; i++) {
				dists[i] += tmpDists[i];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./solving/gold/g3_1238_파티/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dists = new int[N + 1];
		graph = new List[N + 1];
		for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>(); 
		
		int a, b, cost;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, cost});
		}
		
		for(int i = 1; i <= N; i++)
			dijkstra(i, X);
		
		dijkstra(X, -1);
		
		//System.out.println(Arrays.toString(dists));
		
		int max = -1;
		for(int i = 1; i <= N; i++)
			max = Math.max(max, dists[i]);
		
		System.out.println(max);
		br.close();
	}
}
