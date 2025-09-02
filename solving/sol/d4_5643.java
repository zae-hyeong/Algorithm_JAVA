package sol;

import java.io.*;
import java.util.*;

public class d4_5643 {
	static int N, M;
	static int[] count;
	static boolean[][] g;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/sol/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			g = new boolean[N + 1][N + 1];
			count = new int[N + 1];

			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				g[a][b] = true;
			}

			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (g[i][k] && g[k][j])
							g[i][j] = true;
					}
				}
			}

			int result = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					if (g[j][i] || g[i][j])
						count[i]++;
				}

				if (count[i] == N - 1)
					result++;
			}

//			System.out.println(Arrays.toString(count));
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
