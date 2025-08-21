package d456.d4_7733;

import java.util.*;
import java.io.*;

public class Solution {
	static int[][] arr;
	static boolean[][] visited;
	static int N;
	
	public static boolean isValid() {
		return false;
	}
	
	public static void dfs(int i, int j, int day) {

		visited[i][j] = true;
		
		int[] di = {1, 0, -1, 0};
		int[] dj = {0, 1, 0, -1};
		
		int ni, nj;
		for(int k = 0; k < 4; k++) {
			ni = i + di[k];
			nj = j + dj[k];
			
			if(arr[i][j] > day && 0 <= ni && ni <N && 0 <= nj && nj <N && !visited[ni][nj]) {
				dfs(ni, nj, day);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int maxVal = 0;
		int numOfChunk = 0;
		
		for (int loop = 1; loop <= TC; loop++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j ++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxVal = 1;
			
			for(int day = 0; day <= 100; day++) {
				numOfChunk = 0;
				visited = new boolean[N][N];
				
				for (int i = 0; i < N; i++) { 
					for (int j = 0; j < N; j++) {
						if (visited[i][j]) continue;
						if (arr[i][j] <= day) {
							visited[i][j] = true;
							continue;
						}
						
						dfs(i, j, day);
						numOfChunk++;
					}
				}
				if(numOfChunk == 0) break;
				if(maxVal < numOfChunk) maxVal = numOfChunk; 
			}
			sb.append("#").append(loop).append(" ").append(maxVal).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
