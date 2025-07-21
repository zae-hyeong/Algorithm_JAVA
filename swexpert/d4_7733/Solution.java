package d4_7733;

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int maxVal = 0;
		for (int loop = 1; loop <= TC; loop++) {
			int N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j ++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for(int[] a : arr) System.out.println(Arrays.toString(a));

			sb.append("#").append(loop).append(" ").append(maxVal);
		}
	}
}
