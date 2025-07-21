package swexpert.d2_21425;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		final int N = Integer.parseInt(input);

		int count, a, b, n;

		for (int loop = 1; loop <= N; loop++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			count = 0;
			a = arr[0] > arr[1] ? arr[0] : arr[1];
			b = arr[0] <= arr[1] ? arr[0] : arr[1];
			n = arr[2];

			while (true) {
				if (a > n) break;
				
				b = a + b;
				count++;
				if (b > n) break;
				
				a = a + b;
				count++;
				if (a > n) break;
			}
			System.out.println(count);
		}
		br.close();
	}
}
