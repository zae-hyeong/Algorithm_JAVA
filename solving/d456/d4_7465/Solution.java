package d456.d4_7465;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, result;
	static int[] arr;

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return;

		arr[rootB] = rootA;
	}

	static int find(int v) {
		if (v == arr[v])
			return v;

		return arr[v] = find(arr[v]);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_7465/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N + 1];
			for (int i = 1; i <=N; i++) {
				arr[i] = i;
			}
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			HashSet<Integer> s = new HashSet<>();
			for (int i = 1; i <= N; i++) 
				s.add(find(i));
			
			sb.append("#").append(tc).append(" ").append(s.size()).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
