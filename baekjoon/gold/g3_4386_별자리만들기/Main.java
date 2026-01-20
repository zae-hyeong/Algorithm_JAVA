package gold.g3_4386_별자리만들기;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static double total;
	static double[][] pos;
	static boolean[] v;
	
	static void log(Object o) {
		System.out.println(o.toString());
	}
	
	static double getDistance(double y, double x, double ny, double nx) {
		return Math.sqrt(Math.pow(y - ny, 2) + Math.pow(x - nx, 2));
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_4386_별자리만들기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		pos = new double[N][2];
		v = new boolean[N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			pos[i][0] = Double.parseDouble(st.nextToken());
			pos[i][1] = Double.parseDouble(st.nextToken());
		}
		
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]));
		total = 0;
		v[0] = true;
		
		double dist;
		for(int i = 1; i < N; i++) {
			dist = getDistance(pos[0][0], pos[0][1], pos[i][0], pos[i][1]);
//			log(String.format("[%f, %f], [%f, %f]", pos[0][0], pos[0][1], pos[i][0], pos[i][1]));
			pq.add(new double[] {i, dist});
		}
		
		int node, count = 1;
		double[] cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			node = (int) cur[0];
			dist = cur[1];
			
			if(v[node]) continue;
			
			total += dist;
			v[node] = true;
			
			if(count++ == N) break;
			
			for(int j = 1; j < N; j++) {
				if(v[j] || node == j) continue;
				
				pq.add(new double[] {j, getDistance(pos[node][0], pos[node][1], pos[j][0], pos[j][1])});
			}
		}

		System.out.println(total);
		br.close();
	}
}
