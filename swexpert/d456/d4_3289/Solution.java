package d456.d4_3289;
import java.util.*;
import java.io.*;

public class Solution {
	static int N, M;
	static int[] arr;
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		
		arr[rootB] = rootA;
		return true;
	}
	
	static int find(int v) {
//		System.out.println(v +" : "+arr[v]);
		if(v == arr[v]) return v;
		
		return arr[v] = find(arr[v]);
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_3289/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N + 1];
			for (int i = 0; i <= N; i++) 
				arr[i] = i;
			
			int op, a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				op = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(op == 0) {
					union(a, b);
				} else {
					int rootA = find(a);
					int rootB = find(b);
					sb.append(rootA == rootB ? 1 : 0);
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
