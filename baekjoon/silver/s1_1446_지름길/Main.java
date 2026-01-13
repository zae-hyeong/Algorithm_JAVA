package silver.s1_1446_지름길;

import java.util.*;
import java.io.*;

public class Main {
	static int N, D;
	static int[] dists;
	static ArrayDeque<int[]>[] roads;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/silver/s1_1446_지름길/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		dists = new int[D + 1];
		roads = new ArrayDeque[D + 1];
		for(int i = 0; i <= D; i++) {
			dists[i] = i;
			roads[i] = new ArrayDeque<>();
		}
		
		int start, end, dist;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			
			if(end > D) continue;
			
			roads[start].add(new int[] {end, dist}); 
		}
		
		int[] skip;
		for(int i = 0; i < D; i++) {
			dists[i + 1] = Math.min(dists[i] + 1, dists[i + 1]);
			
			while(!roads[i].isEmpty()) {
				skip = roads[i].poll();
				
				dists[skip[0]] = Math.min(dists[i] + skip[1], dists[skip[0]]);
			}
		}
		
//		System.out.println(Arrays.toString(dists));

		System.out.println(dists[D]);
		br.close();
	}
}