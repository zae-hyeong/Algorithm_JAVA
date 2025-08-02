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
		PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
		int[] weights = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			weights[i] = Integer.MAX_VALUE;
		}
		weights[start] = 0;
		heap.add(new int[] {start, 0}); // node 번호, weight
		
		int [] curNode;
		while(!heap.isEmpty()) {
			curNode = heap.poll();
			
			for(int[] nearNode: graph.get(curNode[0])) {
				if(weights[nearNode[0]] > weights[curNode[0]] + nearNode[1]) {
					weights[nearNode[0]] = weights[curNode[0]] + nearNode[1];
					heap.add(nearNode);
				}
			}
			System.out.println(curNode[0] + Arrays.toString(weights));
		}

		return weights[end];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./baekjoon/g5_1916/input.txt"));
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
