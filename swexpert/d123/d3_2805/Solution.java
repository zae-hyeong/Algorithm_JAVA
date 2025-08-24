package d123.d3_2805;

import java.io.*;

public class Solution {
	static int N, result;
	static int[][] arr;

	static void calc() {
		int mid = N / 2;

		for (int i = 0; i <= mid; i++)
			for (int j = mid - i; j <= mid + i; j++)
				result += arr[i][j];

		for (int i = mid + 1, d = N / 2 - 1; i < N; i++, d--)
			for (int j = mid - d; j <= mid + d; j++)
				result += arr[i][j];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d3_2805/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			char[] tmp;
			for (int i = 0; i < N; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) 
					arr[i][j] = Integer.parseInt(Character.toString(tmp[j]));
			}

			calc();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
