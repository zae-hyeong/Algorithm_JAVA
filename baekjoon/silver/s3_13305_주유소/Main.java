package silver.s3_13305_주유소;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] dist, cost;
	static long result = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/silver/s3_13305_주유소/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dist = new int[N];
		cost = new int[N];
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < N - 1; i++) {
			dist[i] = Integer.parseInt(st1.nextToken());
			cost[i] = Integer.parseInt(st2.nextToken());
		}
		System.out.println(Arrays.toString(dist));
		System.out.println(Arrays.toString(cost));
		
		int minIdx = 0, d = dist[0];
		for(int i = 1; i < N; i++) {
			if(cost[minIdx] < cost[i]) {
				d += dist[i];
				continue;
			}
			
			// System.out.format("[%d] minIdx=%d, result=%d, d * cost[minIdx]=%d \n", i, minIdx, result, d * cost[minIdx]);
			result += (long) d * cost[minIdx];
			d = dist[i];
			minIdx = i;
		}

		System.out.println(result);
		br.close();
	}
}
