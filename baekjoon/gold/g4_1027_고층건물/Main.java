package gold.g4_1027_고층건물;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr, cnt;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_1027_고층건물/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		cnt = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		for (int target = 0; target < N; target++) {
			double min = Integer.MAX_VALUE;
			double val;
			for (int near = target - 1; near >= 0; near--) {
				val = (double) (arr[target] - arr[near]) / (target - near);
				if (min > val) {
					min = val;
					cnt[target]++;
				}
			}

			min = Integer.MIN_VALUE;
			for (int near = target + 1; near < N; near++) {
				val = (double) (arr[near] - arr[target]) / (near - target);
				if (min < val) {
					min = val;
					cnt[target]++;
				}
			}
		}

		System.out.println(Arrays.toString(cnt));
		int result = -1;
		for (int c : cnt) {
			result = Math.max(c, result);
		}
		System.out.println(result);
		br.close();
	}
}
