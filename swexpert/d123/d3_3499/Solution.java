package d123.d3_3499;

import java.util.*;
import java.io.*;

public class Solution {
	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d3_3499/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			ArrayDeque<String> left = new ArrayDeque<>();
			ArrayDeque<String> right = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < (N + 1) / 2; i++)
				left.add(st.nextToken());

			for (int i = 0; i < N / 2; i++)
				right.add(st.nextToken());

			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < (N + 1) / 2; i++) {
				if (!left.isEmpty())
					sb.append(left.poll()).append(" ");
				if (!right.isEmpty())
					sb.append(right.poll()).append(" ");
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
