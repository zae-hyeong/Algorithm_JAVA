package d456.d4_4796;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, result;
	static int[] arr;
	static ArrayList<Integer> tops;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d45/d4_4796/input.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		final int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			arr = new int[N];
			tops = new ArrayList<>();
			result = 0;

			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
				if (i > 1 && arr[i] < arr[i - 1] && arr[i - 1] > arr[i - 2])
					tops.add(i - 1);
			}

			int left, right;
			for (int top : tops) {
				left = right = top;

				while (right + 1 < N && arr[right] > arr[right + 1])
					right++;

				while (left - 1 >= 0 && arr[left - 1] < arr[left])
					left--;

				result += (top - left) * (right - top);
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}
}