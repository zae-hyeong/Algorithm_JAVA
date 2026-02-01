package silver.s1_15900_나무탈출;
import java.util.*;
import java.io.*;

public class Main {
	static int N, count = 0;
	static List<Integer>[] tree;
	static boolean[] v;
	
	static void dfs(int node, int depth) {
		v[node] = true;
		
		boolean isLeaf = true;
		for(int nextNode : tree[node]) {
			if(v[nextNode]) continue;
			
			dfs(nextNode, depth + 1);
			isLeaf = false;
		}
		if(isLeaf) count += depth;
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/silver/s1_15900_나무탈출/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		v = new boolean[N + 1];
		tree = new List[N + 1];
		for(int i = 1; i <= N; i++) tree[i] = new ArrayList<>(); 
		
		StringTokenizer st;
		int a, b;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		v[1] = true;
		dfs(1, 0);

		System.out.println(count % 2 != 0 ? "Yes" : "No");
		br.close();
	}
}
