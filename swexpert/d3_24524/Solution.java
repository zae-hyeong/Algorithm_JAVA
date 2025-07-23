package d3_24524;

import java.util.*;
import java.io.*;

public class Solution {
	static int N = 0;
	static int[] arr = {};
	static int result = -1;

	public static void getMinDistance() {

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();

		final int TC = Integer.parseInt(input);
		StringTokenizer st = null;

		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
