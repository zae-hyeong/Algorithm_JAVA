package gold.g5_5972_택배배송;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static ArrayList<int[]>[] graph;
	static int[] dist;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g5_5972_택배배송/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1];
		v = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		int a, b, c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, c});
			graph[b].add(new int[] {a, c});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.add(new int[] {1, 0});
		dist[1] = 0;
		
		int[] cur;
		int curNode, curCost;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			curNode = cur[0];
			curCost = cur[1];
			
			if(v[curNode] || dist[curNode] < curCost) continue;
			
			v[curNode] = true;
			
			if(curNode == N) break;
			
			for(int[] nextNode: graph[curNode]) {
				if(dist[nextNode[0]] > curCost + nextNode[1]) {
					dist[nextNode[0]] = curCost + nextNode[1];
					pq.add(new int[] {nextNode[0], curCost + nextNode[1]});
				}
			}
		}
		
		System.out.println(dist[N]);
		br.close();
	}
}
