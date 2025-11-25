package gold.g2_11780_플로이드2;

import java.util.*;
import java.io.*;

public class Main2 {
	static final int INF = 100_005;
	static int N, E;
	static int[][] dist, before;
	
	static String trackRoute(int i, int j) {
		StringBuffer sb = new StringBuffer();
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		stack.push(j);
		
		int prev = before[i][j];
		while(prev != 0) {
			stack.push(prev);
			prev = before[i][prev];
		}
		
		sb.append(stack.size()).append(" ");
		while(!stack.isEmpty())
			sb.append(stack.pop()).append(" ");
		
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_11780_플로이드2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist = new int[N + 1][N + 1];
		before = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dist[i][j] = i == j ? 0 : INF;
			}
		}

		StringTokenizer st;
		int a, b, c;
		E = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			if(dist[a][b] > c) {
				dist[a][b] = c;
				before[a][b] = a;
			}
		}

		// 플로이드-워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						before[i][j] = before[k][j];
					}
				}
			}
		}

		// Map 출력
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
			}
			sb.append("\n");
		}

		// 경로 출력
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j || dist[i][j] == INF) {
					sb.append("0\n");
					continue;
				}
				//경로 역추적
				sb.append(trackRoute(i, j)).append("\n");
			}
		}

		System.out.println(sb.toString());
		br.close();
	}
}
