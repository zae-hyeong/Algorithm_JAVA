package gold.g4_1753_최단경로;

import java.util.*;
import java.io.*;

public class Main {
	static int V, E;
	static List<int[]>[] g;
	static int[] dist;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./solving/gold/g4_1753_최단경로/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[V + 1];
		v = new boolean[V + 1];
		
		g = new List[V + 1];
		for(int i = 1; i <= V; i++) {
			g[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		int start = Integer.parseInt(br.readLine());
		dist[start] = 0;
		
		int a, b, c;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			g[a].add(new int[] {b, c});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.add(new int[] {start, 0});
		
		int cur[];
		while(!pq.isEmpty()) {
			cur = pq.poll();
			//System.out.println(Arrays.toString(cur));
			
			if(v[cur[0]]) continue;
			
			v[cur[0]] = true;
			
			for(int[] next: g[cur[0]]) {
				if(!v[next[0]] && (dist[next[0]] > cur[1] + next[1])) {
					dist[next[0]] = cur[1] + next[1];
					pq.add(new int[] {next[0], dist[next[0]]});
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i <= V; i++) {
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}

}
