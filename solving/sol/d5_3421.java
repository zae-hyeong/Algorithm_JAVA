package sol;

import java.io.*;
import java.util.*;

public class d5_3421 {
	static int N, M, result;
	static boolean[][] g;
	static boolean[] v;
	
	static void makeComb(int idx) {
		if (idx == N + 1) {
			result++;
			return;
		}

		// 안넣고 comb
		makeComb(idx + 1);

		boolean flag = true; // 넣을 수 있는지 확인
		for (int i =  1; i <= N; i++) {
			if (v[i] && g[i][idx]) { // 이미 썼는데, 사용하면 안되는 조합이라면 
				flag = false;
				break;
			}
		}

		// 넣을 수 있으면 넣고 comb
		if (flag) {
			v[idx] = true;
			makeComb(idx + 1);
			v[idx] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/sol/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			g = new boolean[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				g[a][b] = g[b][a] = true;
			}

			result = 0;
			v = new boolean[N + 1];
			makeComb(1);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
