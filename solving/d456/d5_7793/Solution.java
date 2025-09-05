package d456.d5_7793;
import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, result;
	static int[][] map;
	static boolean [][] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(""));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
//			map = new int[N][M];
			v = new boolean[N][M];
			result = 0;
			char[] tmp;
			int dY, dX, sY, sX, eY, eX;
			for (int i = 0; i < N; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					map[i][j] = tmp[j];
					if (tmp[j] == 'S') {
						sY = i;
						sX = j;
					} else if (tmp[j] == 'D') {
						eY = i;
						eX = j;
					}
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
