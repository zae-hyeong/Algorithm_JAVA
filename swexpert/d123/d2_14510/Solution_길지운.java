package d123.d2_14510;

import java.io.*;
import java.util.*;

class Solution_길지운 {

	private static int N, target, tree, a, b, k, total_needs, answer;
	private static int[] trees;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			trees = new int[N];
			target = 0;

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				tree = Integer.parseInt(st.nextToken());
				trees[n] = tree;
				if (target < tree)
					target = tree;
			}

			a = 0;
			b = 0;
			for (int i = 0; i < N; i++) {
				if (trees[i] < target) {
					if ((target - trees[i]) % 2 > 0)
						b++;
					a += (target - trees[i]) / 2;
				}
			}

			if (b > a)
				answer = 2 * b - 1;
			else if (b == a)
				answer = 2 * a;
			else {
				k = a - b;
				answer = 2 * b;
				if ((2 * k) % 3 == 1)
					answer += 2 * (2 * k / 3 + 1) - 1;
				else if ((2 * k) % 3 == 2)
					answer += 2 * (2 * k / 3 + 1);
				else
					answer += 2 * (2 * k / 3);
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}