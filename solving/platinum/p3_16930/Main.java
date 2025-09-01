package platinum.p3_16930;
import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int sY, sX, destY, destX;
	static char[][] map;
	static int[][] visited;

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[5], o2[5])); 
//		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sY, sX, 0, 0, K, 0 });
		visited[sY][sX] = 0;
		
		int[] cur;
		int y, x, k, sec, ny, nx;
		while (!q.isEmpty()) {
			cur = q.poll();
//			System.out.println(Arrays.toString(cur));
			y = cur[0];
			x = cur[1];

			if (y == destY && x == destX)
				return;

			for (int d = 0; d < 4; d++) {
				ny = y + dy[d];
				nx = x + dx[d];
				k = cur[4];
				sec = cur[5];

				if (inBound(ny, nx) && map[ny][nx] != '#') {
					if (k == 1 || (dy[d] != cur[2] || dx[d] != cur[3])) {
						sec += 1;
						k = K;
					} else {
						k -= 1;
					}

					if (visited[ny][nx] <= sec)
						continue;
					
					q.add(new int[] {ny, nx, dy[d], dx[d], k, sec});
					visited[ny][nx] = sec;
				}
			}
//			for(int[] m: visited) System.out.println(Arrays.toString(m));
//			System.out.println("-----------------------------");
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], Integer.MAX_VALUE);

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		st = new StringTokenizer(br.readLine());
		sY = Integer.parseInt(st.nextToken()) - 1;
		sX = Integer.parseInt(st.nextToken()) - 1;
		destY = Integer.parseInt(st.nextToken()) - 1;
		destX = Integer.parseInt(st.nextToken()) - 1;

		bfs();
//		for(int[] m: visited) System.out.println(Arrays.toString(m));
		System.out.println(visited[destY][destX] == Integer.MAX_VALUE ? -1 : visited[destY][destX]);
		br.close();
	}

}
