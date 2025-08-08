package algorithm.MST;

import java.io.*;
import java.util.*;

public class PrimPQMain {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		/* 선언부 */
		List<int[]>[] g = new List[N];
		for (int i = 0; i < N; i++)
			g[i] = new ArrayList<>();
		
		boolean[] v = new boolean[N];
		int[] prim = new int[N]; // 정점별 minEdge 
		Arrays.fill(prim, Integer.MAX_VALUE);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int c = sc.nextInt(); // 가중치 cost
				if (c == 0) continue;
				
				g[i].add(new int[] { j, c }); // 현재 정점, 비용 (i -> j, cost)
			}
		}
		
		/* 동작부 */
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int mst = 0, // 총 경로 길이
			cnt = 0; // 선택한 간선 개수
		prim[0] = 0; // 시작정점의 간선 = 0
		pq.offer(new int[] { 0, prim[0] });
		
		while(!pq.isEmpty()) {
			/* 간선 비용이 최소인 정점 선택 */
			int[] vc = pq.poll();
			int minVertex = vc[0]; // 최소간선(최소비용)에 해당하는 정점
			int min = vc[1];
			
			if(min>prim[minVertex]) continue;
			if(v[minVertex]) continue;

			/* 방문 처리 */
			v[minVertex] = true;
			mst += min;
			if (cnt++ == N - 1) break; // 간선 N-1개 선택 후 종료

			/* 연결 간선 최신화 */
			for (int[] jc : g[minVertex]) { // 선택한 정점의 인접 간선 중에서
				if (!v[jc[0]] && prim[jc[0]] > jc[1]) {  // 방문하지 않은 정점 중, 최신화할 최소 간선이 있으면 업데이트 
					prim[jc[0]] = jc[1];
					pq.offer(new int[] { jc[0], prim[jc[0]] });
				}
			}
		}
		
		System.out.println(mst);
		sc.close();
	}
}

/*
5
0 5 10 8 7 
5 0 5 3 6
10 5 0 1 3
8 3 1 0 1
7 6 3 1 0

10
*/
