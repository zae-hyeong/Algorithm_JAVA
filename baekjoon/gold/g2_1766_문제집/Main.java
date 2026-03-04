package gold.g2_1766_문제집;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static List<Integer>[] prevList;
	static int[] nextCount;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_1766_문제집/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prevList = new List[N + 1];
		for(int i = 1; i <= N; i++) prevList[i] = new ArrayList<>();
		nextCount = new int[N + 1];
		
		int prev, next;
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			prev = Integer.parseInt(st.nextToken());
			next = Integer.parseInt(st.nextToken());
			
			prevList[prev].add(next);
			nextCount[next]++;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(nextCount[i] == 0) pq.add(i);
		}
		
		StringBuffer sb = new StringBuffer();
		
		while(!pq.isEmpty()) {
			prev = pq.poll();
			
			sb.append(prev).append(' ');
			
			for(int n: prevList[prev]) {
				if(--nextCount[n] == 0) pq.add(n);
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
