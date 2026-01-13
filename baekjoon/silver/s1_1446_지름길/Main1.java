package silver.s1_1446_지름길;

import java.util.*;
import java.io.*;

public class Main1 {
	static int N, D;
	static int[] dists;
	static int[][] roads;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		roads = new int[N][3];
		
		dists = new int[D + 1];
		for(int i = 0; i <= D; i++) dists[i] = i;
		
		int start, end, dist;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			
			roads[i][0] = start;
			roads[i][1] = end;
			roads[i][2] = dist;
		}
		
		Arrays.sort(roads, (o1, o2) -> o1[0] - o2[0]);
		
		int index = 0, next;
		for(int i = 0; i <= D; i++) {
			if(i > 0) dists[i] = Math.min(dists[i - 1] + 1, dists[i]);
			
			while(index < N && roads[index][0] == i) {
				if(roads[index][1] > D) {
					index++;
					continue;
				}
				
				next = roads[index][1];
				dists[next] = Math.min(dists[i] + roads[index][2], dists[next]);
				index++;
			}
		}
		
//		System.out.println(Arrays.toString(dists));

		System.out.println(dists[D]);
		br.close();
	}
}