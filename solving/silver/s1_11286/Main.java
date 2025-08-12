package silver.s1_11286;

import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/silver/s1_11286/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>(
				(o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)) == 0 ? o1 - o2 : Math.abs(o1) - Math.abs(o2));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num != 0) {
				pq.add(num);
				continue;
			}

			if (pq.isEmpty())
				sb.append(0).append("\n");
			else
				sb.append(pq.poll()).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
