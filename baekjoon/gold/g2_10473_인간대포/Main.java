package gold.g2_10473_인간대포;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static double[] times;
	static boolean[] v;
	static double[][] cord; // 순서, 좌표
	
	static double startX, startY;
	static double destX, destY;
	
	static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g2_10473_인간대포/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		startX = Double.parseDouble(st.nextToken());
		startY = Double.parseDouble(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		destX = Double.parseDouble(st.nextToken());
		destY = Double.parseDouble(st.nextToken());
		
		N = Integer.parseInt(br.readLine());
		
		times = new double[N + 1];
		Arrays.fill(times, Double.MAX_VALUE);
		
		v = new boolean[N + 1];
		cord = new double[N + 1][2];
		
		cord[0][0] = destX;
		cord[0][1] = destY;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			cord[i][0] = Double.parseDouble(st.nextToken());
			cord[i][1] = Double.parseDouble(st.nextToken());
		}
		
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[3], o2[3]));
		pq.add(new double[] {-1, startX, startY, 0.0}); //id, x, y, time
		
		double[] cur;
		double id, x, y, time, newTime, dist;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
//			System.out.println(Arrays.toString(cur));
//			System.out.println("times : " + Arrays.toString(times));
			
			id = cur[0];
			x = cur[1];
			y = cur[2];
			time = cur[3];
			
//			if(id != -1 && (v[(int)id] || time > times[(int) id])) continue;
			if(id != -1 && time > times[(int) id]) continue;
			if(id != -1 && v[(int)id]) continue;
			if(id == 0) break;
			
			if(id != -1) v[(int) id] = true;
			
			for(int i = 0; i <= N; i++) {
				if(v[i] || id == i) continue;
				
				dist = getDistance(x, y, cord[i][0], cord[i][1]);
				
				newTime = dist / 5;
				if(id != -1) newTime = Math.min(newTime, (Math.abs(50 - dist) / 5) + 2);
				
				newTime += time;
				
				if(times[i] > newTime) {
					times[i] = newTime;
					pq.add(new double[] {i, cord[i][0], cord[i][1], newTime});
				}
			}
		}
		
		System.out.println(times[0]);
		br.close();
	}
}
