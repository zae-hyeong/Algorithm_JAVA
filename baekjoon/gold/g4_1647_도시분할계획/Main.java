package gold.g4_1647_도시분할계획;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static HashMap<Integer, ArrayList<int[]>> g;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_1647_도시분할계획/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		g = new HashMap<>();

		int a = 0, b, c;
		HashMap<Integer, Integer> distances = new HashMap<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			g.putIfAbsent(a, new ArrayList<>());
			g.putIfAbsent(b, new ArrayList<>());

			g.get(a).add(new int[] { b, c });
			g.get(b).add(new int[] { a, c });

			distances.putIfAbsent(a, Integer.MAX_VALUE);
			distances.putIfAbsent(b, Integer.MAX_VALUE);
		}
//		for(int k: g.keySet()) System.out.println(String.format("key : %d, graph : %s", k, g.get(k).toString()));

		Set<Integer> v = new HashSet<>();
		int MST = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		pq.add(new int[] { a, 0 });
		distances.put(a, 0);

		int node, dist, maxDist = 0, count = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			node = cur[0];
			dist = cur[1];

			if (v.contains(node) || dist > distances.get(node)) continue;

			v.add(node);
			MST += dist;
			maxDist = Math.max(maxDist, dist);
			
			if (count++ >= N - 1) break;

			for (int[] nextNode : g.get(node)) {
				if (!v.contains(nextNode[0]) && distances.get(nextNode[0]) > nextNode[1]) {
					distances.put(nextNode[0], nextNode[1]);
					pq.add(new int[] { nextNode[0], nextNode[1] });
				}
			}
		}
//		for (int k : distances.keySet()) System.out.println(String.format("key : %d, dist : %d", k, distances.get(k)));

		System.out.println(MST - maxDist);
		br.close();
	}
}
