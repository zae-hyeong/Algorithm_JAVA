package gold.g2_11780_플로이드2;

import java.util.*;
import java.io.*;

public class Main {
	static final int INF = 100_005;
	static int N, E;
	static int[][] dist;
	static List<Integer>[][] route;

	static void trackRoute(int i, int k, int j) {
		route[i][j].clear();

		for (int r : route[i][k]) {
			route[i][j].add(r);
		}

		route[i][j].add(k);

		for (int r : route[k][j]) {
			route[i][j].add(r);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_11780_플로이드2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist = new int[N + 1][N + 1];
		route = new List[N + 1][N + 1];

		E = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				route[i][j] = new ArrayList<>();
				dist[i][j] = i == j ? 0 : INF;
			}
		}

		StringTokenizer st;
		int a, b, c;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			dist[a][b] = Math.min(dist[a][b], c);
		}

		// 플로이드-워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						trackRoute(i, k, j);
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

				sb.append(route[i][j].size() + 2).append(" ");
				sb.append(i).append(" ");
				for (int r : route[i][j]) {
					sb.append(r).append(" ");
				}
				sb.append(j).append(" \n");
			}
		}

		System.out.println(sb.toString());
		br.close();
	}
}
