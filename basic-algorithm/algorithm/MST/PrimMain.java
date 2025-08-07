package algorithm.MST;

import java.io.*;
import java.util.*;

public class PrimMain {
	public static void main(String[] args)throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<int[]>[] g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		boolean[] v = new boolean[N];
		int[] prim = new int[N]; // 프림 minEdge
		Arrays.fill(prim, Integer.MAX_VALUE);
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++) {
				int c = sc.nextInt(); // 가중치 cost
				if(c==0) continue;
				g[i].add(new int[] {j, c}); // 현재 정점, 비용 (i->j, cost)
			}
		}
		int mst=0, cnt=0;
		prim[0]=0; // 비밀장부! 🌟🌟🌟
		for(int i=0; i<N; i++) {
			int minVertex = -1; // 최소간선(최소비용)에 해당하는 정점
			int min=Integer.MAX_VALUE;
			
			// prim 비밀장부에서 최소비용에 해당하는 값min, 정점 step1
			for(int j=0; j<N; j++) { 
				if(!v[j] && min>prim[j]){ //prim[j]<min) { -> 방문처리를 통해 순환참조 방지?
					minVertex=j;
					min = prim[j];
				}
			}
			
			v[minVertex] = true;
			mst += min;
			if(cnt++==N-1) break; // mst 완성되면 그만하기(시간초과 방지)
			
			// prim 비밀장부를 갱신 step3
			for(int[] jc: g[minVertex]) {
				if(!v[jc[0]] && prim[jc[0]] > jc[1]) {
					prim[jc[0]]=jc[1];
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
