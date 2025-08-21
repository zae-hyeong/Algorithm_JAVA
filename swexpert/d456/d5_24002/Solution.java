package d456.d5_24002;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N = 0;
	static int[] arr = {};

	public static int getCount() {
		int smallest = arr[0];

		int totalCount = 0;

		int count = 0;

		while (true) {
			
		}

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

			arr = Arrays.stream(arr).sorted().toArray();

//			System.out.println(N);
//			System.out.println(Arrays.toString(arr));

			result = getCount();

			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
