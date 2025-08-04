package d3_9229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d3_9229/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int result = -1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
			for (int i = 0; i < N; i++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}

			int a = pq.poll();
			int b = pq.poll();

			for (int i = 0; i < N - 1; i++) {
				if ((a + b) <= M) {
					result = a + b;
					break;
				}

				a = b;
				if(!pq.isEmpty()) b = pq.poll();
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
