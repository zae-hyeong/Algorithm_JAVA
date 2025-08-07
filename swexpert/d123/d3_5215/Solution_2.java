package d123.d3_5215;

import java.io.*;
import java.util.*;

public class Solution_2 {
	static int N, L;
	static int[] points, cals;
	static int maxPoint;
	
	static void make(int index, int totalCal, int totalPoint) {
		maxPoint = Math.max(totalPoint, maxPoint);
		
		if(index >= N) return;
		
		if(totalCal + cals[index] <= L) 
			make(index + 1, totalCal + cals[index], totalPoint + points[index]);
		
		make(index + 1, totalCal, totalPoint);
	}


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d3_5215/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			maxPoint = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			points = new int[N];
			cals = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				points[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}
			
			make(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(maxPoint).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
