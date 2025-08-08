package algorithm.MST;

import java.io.*;
import java.util.*;

public class DijkstraMain {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		/* 선언부 */
		List<int[]>[] g = new List[N];
		for (int i = 0; i < N; i++)
			g[i] = new ArrayList<>();
		
		boolean[] v = new boolean[N];
		int[] dist = new int[N]; // 각 정점별 최단경로 
		Arrays.fill(dist, Integer.MAX_VALUE);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int c = sc.nextInt(); // 가중치 cost
				if (c == 0) continue;
				
				g[i].add(new int[] { j, c }); // 현재 정점, 비용 (i -> j, cost)
			}
		}
		
		/* 동작부 */
		dist[0] = 0;  // 시작정점의 간선 = 0
		
		for (int i = 0; i < N; i++) {
			/* 최소 정점, 간선 선택 */
			int minVertex = -1; // 최소간선(최소비용)에 해당하는 정점
			int min = Integer.MAX_VALUE;

			for (int j = 0; j < N; j++) {  // 현재까지 연결된 정점 집합과 연결 가능한 가장 가중치가 작은 간선 찾기
				if (!v[j] && min > dist[j]) { 
					minVertex = j;
					min = dist[j];
				}
			}

			/* 방문 처리 */
			v[minVertex] = true;
			//if (minVertex == E) break; // 만약 특정 정점까지의 거리만 알면 되면 중간에 break;

			/* 연결 간선 최신화 */
			for (int[] jc : g[minVertex]) { // 선택한 정점의 인접 간선 중에서
				if (!v[jc[0]] && dist[jc[0]] > min + jc[1]) {  // 방문하지 않은 정점 중, 최신화할 최소 간선이 있으면 업데이트 
					dist[jc[0]] = min + jc[1];
				}
			}
		}
		
		System.out.println(Arrays.toString(dist));
		sc.close();
	}
}

/*
입력 : 
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

출력 : [0, 2, 2, 5, 8]

     9
0 ------> 4 : 직항 
  2    6
0 -> 2 -> 4 : 경우
*/
