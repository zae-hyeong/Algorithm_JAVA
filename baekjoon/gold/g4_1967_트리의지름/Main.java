package gold.g4_1967_트리의지름;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static List<int[]>[] tree;
	static boolean[] v;
	
	static int max;
	static int target;
	
	static void dfsFrom(int node, int totalDist) {
		v[node] = true;
		
		boolean isLeaf = true;
		for(int[] next: tree[node]) {
			if(v[next[0]]) continue;
			
			dfsFrom(next[0], totalDist + next[1]);
			isLeaf = false;
		}
		
		if(isLeaf && totalDist > max) {
			max = totalDist;
			target = node;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("./solving/gold/g4_1967_트리의지름/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		v = new boolean[N + 1];
		tree = new List[N + 1];
		for(int i = 0; i <= N; i++) tree[i] = new ArrayList<>();
		
		StringTokenizer st;
		int a, b, dist;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			
			tree[a].add(new int[] {b, dist});
			tree[b].add(new int[] {a, dist});
		}
		
		dfsFrom(1, 0);
		// System.out.format("target : %d / max : %d\n", target, max);
		
		Arrays.fill(v, false);
		max = 0;
		dfsFrom(target, 0);
		
		// System.out.format("target : %d / max : %d\n", target, max);
		
		System.out.println(max);
		br.close();
	}
}
