package d5_19118;

import java.util.*;
import java.io.*;

public class Solution {
	static int N = 0;
	static int[] arr = null;
	static int result = 0;

	static int crashBuildings(int curIdx, int nextIdx, int crashed) {
		if (nextIdx == N)
			return crashed;

		if (arr[curIdx] < arr[nextIdx])
			return crashBuildings(nextIdx, nextIdx + 1, crashed);

		int val1 = -1;
		// 나를 부순다
		if (curIdx >= 1)
			val1 = crashBuildings(curIdx - 1, nextIdx, crashed + 1);

		// 다음걸 부순다
		int val2 = crashBuildings(curIdx, nextIdx + 1, crashed + 1);

		return val1 == -1 ? val2 : Math.min(val1, val2);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			result = crashBuildings(0, 1, 0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
