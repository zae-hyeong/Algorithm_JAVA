package sol;

import java.io.*;
import java.util.*;

public class g5_14568_sol2 {
	static int N, M;
	static int[] indegree;
	static List<Integer>[] g;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/sol/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		g = new List[N + 1];
		indegree = new int[N + 1];

		for (int i = 1; i <= N; i++)
			g[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			g[a].add(b);
			indegree[b]++;
		}

		ArrayDeque<int[]> ad = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				ad.add(new int[] {i, 1});
		}
		
		int[] resultArr = new int[N + 1];
		while(!ad.isEmpty()) {
			int[] cur = ad.poll();
			resultArr[cur[0]] = cur[1];
			
			for(int next: g[cur[0]]) {
				if(--indegree[next] == 0) ad.add(new int[] {next, cur[1] + 1});
			}
		}
		for (int i = 1; i <= N; i++) {
			sb.append(resultArr[i]).append(" ");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
