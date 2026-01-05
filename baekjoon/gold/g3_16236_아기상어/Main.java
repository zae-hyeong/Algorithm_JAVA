package gold.g3_16236_아기상어;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] v;
	
	static int[] dy = {-1, 0, 0, 1};
	static int[] dx = {0, -1, 1, 0};
	
	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static class Shark {
		int y, x;
		int size = 2;
		int count = 0;
		int totalDist = 0;
	}
	
	static class Node implements Comparable<Main.Node> {
		int y, x, dist;
		
		Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.dist != o.dist) 
				return this.dist - o.dist;
			
			if(this.y != o.y) 
				return this.y - o.y;
			
			return this.x - o.x;
		}
		
		@Override
		public String toString() {
			return String.format("y : %d, x : %d, dist : %d", this.y, this.x, this.dist);
		}
	}
	
	static Node bfsFrom(Shark shark) {
		for(int i = 0; i < N; i++) Arrays.fill(v[i], false);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(shark.y, shark.x, 0)); //y, x, dist
		v[shark.y][shark.x] = true;				
		
		int ny, nx;
		Node cur;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			if(0 < map[cur.y][cur.x] && map[cur.y][cur.x] < shark.size) // 먹을 수 있음 
				return cur;
			
			for(int d = 0; d < 4; d++) {
				ny = cur.y + dy[d];
				nx = cur.x + dx[d];
				
				if(inBound(ny, nx) && !v[ny][nx] && map[ny][nx] <= shark.size) {
					v[ny][nx] = true;
					queue.add(new Node(ny, nx, cur.dist + 1));
				}
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_16236_아기상어/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		v = new boolean[N][N];
		
		Shark shark = new Shark();
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark.y = i;
					shark.x = j;
					map[i][j] = 0;
				}
			}
		}
		
		Node result = null;
		while(true) {
			result = bfsFrom(shark); // [y, x, dist]
			if(result == null) break;
//			System.out.println("RESULT :::" + result.toString());
			
			// 고기 먹기
			map[result.y][result.x] = 0;
			
//			for(int[] m : map) System.out.println(Arrays.toString(m));
//			System.out.println("====================");
			shark.y = result.y;
			shark.x = result.x;
			shark.totalDist += result.dist;
			shark.count++;
			
			if(shark.count == shark.size) {
				shark.size++;
				shark.count = 0;
			}
		}
		
		System.out.println(shark.totalDist);
		br.close();
	}
}
