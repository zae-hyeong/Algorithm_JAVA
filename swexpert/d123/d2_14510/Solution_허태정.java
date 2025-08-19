package d123.d2_14510;

import java.io.*;
import java.util.*;

public class Solution_허태정 {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test = 1; test <= testCase; test++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] map = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(map);
			int twoCount = 0;
			int oneCount = 0;
			for (int i = 0; i < N - 1; i++) {
				map[i] = map[N - 1] - map[i];
				if (map[i] % 2 == 0) {
					twoCount += map[i] / 2;
				} else {
					twoCount += map[i] / 2;
					oneCount++;
				}
			}

			int result = 0;
			int sum = twoCount * 2 + oneCount;

			if (twoCount >= oneCount) {
				if (sum % 3 == 0) {
					result = (sum / 3) * 2;
				} else if ((sum % 3) == 1) {
					result = (sum / 3) * 2 + 1;
				} else {
					result = (sum / 3) * 2 + 2;
				}
			} else {
				result = (twoCount) * 2 + (oneCount - 1 - twoCount) * 2 + 1;
			}

			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}