package algorithm.MST;

import java.io.*;
import java.util.*;

// 프림, 다익스트라는 정점 중심 
// 크루스칼은 간선 중심, 무향 그래프, 그리디 방법
// 간선이 적으면 크루스칼, 간선이 많으면 프림 (MST, 최소신장트리)
// 순환이 되면 안된다? -> union-find
// 크루스칼 mst는 순환이 되지 않아야 하기 때문에 내부적으로 union-find를 사용함
public class KruskalMain {	
	static int N;
	static int[][] g; // 인접행렬, 인접리스트말고 간선정보! 간선에 해당되는 1차원 Edge 배열 대신 모두 int라서 클래스 대신 배열 
	static int[] p; // parent 배열, union-find
	
	// 1 자신의 부모는 자신, i-정점 번호, 값-짱 번호
	static void make() {
		p=new int[N];
		for(int i=0; i<N; i++) p[i] = i;
	}
	
	// 2
	static int find(int a) {
		if(p[a]==a) return a; // 내가 짱이면 짱
		return p[a] = find(p[a]); // 짱 아니면 짱 찾아서 리턴, 메모이제이션
	}
	
	// 3
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false; // 짱이 같으면 
		p[bRoot]=aRoot; // 앞에 있는걸로 통합(상관없음)
		return true;
	}
	
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		
		g = new int[E][3]; // from, to, cost  // new Edge[E];
		for(int i=0; i<E; i++) {
			//int from = sc.nextInt();
			//int toto = sc.nextInt();
			//int cost = sc.nextInt();
			// g[i] = new int[]{from, toto, cost}
			g[i] = new int[] {sc.nextInt(), sc.nextInt(), sc.nextInt()};
		}
		Arrays.sort(g, (o1, o2) -> Integer.compare(o1[2], o2[2])); // 오름차순 정렬
		//Arrays.sort(g, (o1, o2) -> -Integer.compare(o1[2], o2[2])); // 내림차순 정렬
		make();
		int mst=0, cnt=0;
		
		for(int[] edge: g) {
			if(union(edge[0], edge[1])) {
				mst+=edge[2];
				if(++cnt==N-1) break;
			}
		}
		System.out.println(mst); // 최소 spanning tree
		sc.close();
	}
}
/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1

output==>10
*/