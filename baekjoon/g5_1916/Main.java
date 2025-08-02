package g5_1916;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static int start, end;
	static WeightGraph graph;

	public static int dijkstra(int start, int end) {
		int[] weights = new int[N + 1];
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> weights[v]));
		boolean[] visited = new boolean[N+1];

		for (int i = 1; i <= N; i++) {
			weights[i] = Integer.MAX_VALUE;
		}
		weights[start] = 0;
		pq.add(start); // node 번호, weight
		
		int curNode;
		while(!pq.isEmpty()) {
			curNode = pq.poll();
			
			if(visited[curNode]) continue;  //속도 최적화
			visited[curNode] = true;
			
			for(int[] nearNode: graph.get(curNode)) {
				if(weights[nearNode[0]] > weights[curNode] + nearNode[1]) {
					weights[nearNode[0]] = weights[curNode] + nearNode[1];
					pq.add(nearNode[0]);
				}
			}
//			System.out.println(curNode + Arrays.toString(weights));
		}

		return weights[end];
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./baekjoon/g5_1916/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new WeightGraph(N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph.connect(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(start, end));
		br.close();
	}
}
