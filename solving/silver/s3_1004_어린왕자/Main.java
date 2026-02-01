package silver.s3_1004_어린왕자;

import java.util.*;
import java.io.*;

public class Main {
	static int sx, sy, dx, dy, N;
	static int cx, cy;
	static double r;
	
	public static double getDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/silver/s3_1004_어린왕자/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		final int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int count = 0;
		for(int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			dx = Integer.parseInt(st.nextToken());
			dy = Integer.parseInt(st.nextToken());
			
			N = Integer.parseInt(br.readLine());
			
			double dist1, dist2;
			boolean inBound1, inBound2;
			count = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				cx = Integer.parseInt(st.nextToken());
				cy = Integer.parseInt(st.nextToken());
				r = Double.parseDouble(st.nextToken());
				
				dist1 = getDist(sx, sy, cx, cy);
				dist2 = getDist(dx, dy, cx, cy);
				
				inBound1 = inBound2 = false;
				if(dist1 <= r) inBound1 = true;
				if(dist2 <= r) inBound2 = true;
				
				if((!inBound1 && inBound2) || (inBound1 && !inBound2)) count++;				
			}
			
			sb.append(count).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
