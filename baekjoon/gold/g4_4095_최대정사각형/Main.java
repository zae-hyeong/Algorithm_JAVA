package gold.g4_4095_최대정사각형;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_4095_최대정사각형/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		StringTokenizer st;
		int N, M;
		int[][] map;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N == 0 || M == 0) break;
			
			map = new int[N + 1][M + 1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0;
			int min = 0;
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					if(map[i][j] == 0) continue;
					
					min = Math.min(map[i - 1][j - 1], map[i - 1][j]);
					map[i][j] = Math.min(min, map[i][j - 1]) + 1;
					max = Math.max(max, map[i][j]);
				}
			}
			
//			for(int[] m: map) System.out.println(Arrays.toString(m));
			System.out.println(max);
		}

		br.close();
	}
}
