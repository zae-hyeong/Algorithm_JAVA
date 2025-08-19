package d123.d2_14510;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, max;
	static int maxDay;
	static int[] arr;
	static PriorityQueue<Integer> pq;

	static void calc() {
		int tree;

		int day = 0;

		while (!pq.isEmpty()) {
			tree = pq.poll();
			if (tree == max)
				continue;
			day++;
			System.out.println("day " + day + " : " + "tree" + tree);

			if (tree > max) {
				System.out.println("STOP");
				maxDay = day;
				return;
			}

			if (day % 2 == 1 && tree + 2 == max) {
				pq.offer(tree);
			} else if (day % 2 == 1) {
				tree++;
				if (tree < max)
					pq.offer(tree);
			} else if (day % 2 == 0 && tree + 2 > max) {
				pq.offer(tree);
			} else if (day % 2 == 0) {
				tree += 2;
				if (tree != max) {
					System.out.println("asdfasf" + tree);
					pq.offer(tree);
				}
			}
			maxDay = day;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d2_14510/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			maxDay = 0;
			max = 0;

			pq = new PriorityQueue<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				pq.offer(arr[i]);
				max = Math.max(max, arr[i]);
			}

			calc();

			sb.append("#").append(tc).append(" ").append(maxDay).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
