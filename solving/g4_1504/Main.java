package g4_1504;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Graph {
	ArrayList<ArrayList<int[]>> arr;

	Graph(int N) {
		arr = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			arr.add(new ArrayList<>());
		}
	}

	void connect(int a, int b, int w) {
		arr.get(a).add(new int[] { b, w });
		arr.get(b).add(new int[] { a, w });
	}

	ArrayList<int[]> get(int node) {
		return arr.get(node);
	}
}

public class Main {
	static int N, E;
	static Graph g;

	static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N + 1];
		int[] minDistance = new int[N + 1];
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> minDistance[v]));

		pq.add(start);
		minDistance[start] = 0;

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			if (cur == end)
				break;
			if (visited[cur])
				continue;
			
			visited[cur] = true;

			for (int[] nextInfo : g.get(cur)) {
				int next = nextInfo[0];
				
				int weight = nextInfo[1];

				if (minDistance[cur] + weight < minDistance[next]) {
					minDistance[next] = minDistance[cur] + weight;
					pq.add(next);
				}
			}
		}
//		System.out.println(Arrays.toString(minDistance));
		return minDistance[end];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/g4_1504/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		g = new Graph(N);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			g.connect(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		int result1 = 0, result2 = 0;

		st = new StringTokenizer(br.readLine());
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int tmp = dijkstra(1, u);
		if (tmp == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		result1 += tmp;
		
		tmp = dijkstra(u, v);
		if (tmp == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		result1 += tmp;
		
		tmp = dijkstra(v, N);
		if (tmp == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		result1 += tmp;
		
		tmp =0;
		tmp = dijkstra(1, v);
		if (tmp == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		result2 += tmp;
		
		tmp = dijkstra(v, u);
		if (tmp == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		result2 += tmp;
		
		tmp = dijkstra(u, N);
		if (tmp == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		result2 += tmp;
		
		System.out.println(Math.min(result1, result2));
		br.close();
	}
}