package gold.g5_14567;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static HashMap<Integer, HashSet<Integer>> hm;
	static ArrayDeque<Integer> ad;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_14567/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		hm = new HashMap<>();

		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			if (!hm.containsKey(from))
				hm.put(from, new HashSet<>());
			if (!hm.containsKey(to))
				hm.put(to, new HashSet<>());

			hm.get(to).add(from);
		}

		ad = new ArrayDeque<>();
		for (int key : hm.keySet()) {
			if (hm.get(key).size() == 0)
				ad.add(key); // 과목, 학기
		}

		int[] result = new int[N + 1];
		Arrays.fill(result, 1);

		while (!ad.isEmpty()) {
			int cur = ad.pollFirst();

			for (int key : hm.keySet()) {
				if (!hm.get(key).contains(cur)) continue;

				hm.get(key).remove(cur);

				if (hm.get(key).isEmpty()) {
					ad.add(key);
					result[key] = result[cur] + 1; 
				}
			}

//			System.out.println(hm.toString());
		}

		for (int i = 1; i <= N; i++) sb.append(result[i]).append(" ");
		System.out.println(sb.toString());
		br.close();
	}

}
