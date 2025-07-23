package d3_24524;

import java.util.*;
import java.io.*;

public class Solution {
	static int N = 0;
	static int[] arr = {};

	public static int getMinDistance() {

		int initVal = 0;

		for (int i = 1; i < N; i++) {
			initVal += Math.abs(arr[i] - arr[i - 1]);
		}
		int minVal = initVal;
		int val = initVal;

		for (int skip = 1; skip < N - 1; skip++) {
			val = initVal;
			val -= (Math.abs(arr[skip] - arr[skip - 1]));
			val -= (Math.abs(arr[skip + 1] - arr[skip]));
			val += Math.abs(arr[skip + 1] - arr[skip - 1]);

			minVal = Math.min(minVal, val);
		}

		return minVal;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();

		final int TC = Integer.parseInt(input);
		StringTokenizer st = null;

		int result = 0;

		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			result = getMinDistance();

			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
