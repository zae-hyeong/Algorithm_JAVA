package d123.d2_14510;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, maxH, result;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./swexpert/d123/d2_14510/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			maxH = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, arr[i]);
			}

			int numOf2 = 0, numOf1 = 0;
			for (int i = 0; i < N; i++) {
				numOf2 += (maxH - arr[i]) / 2;
				numOf1 += (maxH - arr[i]) % 2;
			}

			result = 0;
			if (numOf1 > numOf2)
				result = 2 * numOf1 - 1;
			else if (numOf1 == numOf2)
				result = 2 * numOf1;
			else {
				int diff = ((numOf2 - numOf1 + 1) / 3);

				numOf2 = numOf2 - diff;
				numOf1 = numOf1 + diff * 2;

				System.out.println("numOf1 : " + numOf1 + " / numOf2 : " + numOf2);
				result = numOf1 + numOf2;
				if (numOf2 > numOf1) {
					result += 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
