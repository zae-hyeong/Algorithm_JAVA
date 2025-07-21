package d2_1859;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		final int N = Integer.parseInt(input);

		long total;
		int max;

		for (int loop = 1; loop <= N; loop++) {
			input = br.readLine();
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			total = 0;
			max = arr[arr.length - 1];

			for (int i = arr.length - 2; i >= 0; i--) {
				if (arr[i] > max) {
					max = arr[i];
				} else {
					total += (max - arr[i]);
				}
			}
			System.out.format("#%d %d\n", loop, total);
		}
		br.close();
	}
}
