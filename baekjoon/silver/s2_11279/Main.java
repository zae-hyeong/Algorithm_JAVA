package silver.s2_11279;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> ad;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/silver/s2_11279/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		ad = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

		int num;
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());

			if (num == 0) {
				if (!ad.isEmpty())
					sb.append(ad.poll()).append("\n");
				else
					sb.append(0).append("\n");
			} else
				ad.offer(num);
		}

		System.out.println(sb.toString());
		br.close();
	}
}
