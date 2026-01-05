package gold.g4_3055_탈출;

import java.util.*;
import java.io.*;

public class Main {
	static int W, H, startY, startX, destY, destX;
	static int[][] water, map;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static boolean inBound(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W;
	}
	
	static boolean isDest(int y, int x) {
		return destY == y && destX == x;
	}
	
	public static void makeWaterMap() {
		int[] cur = null;
		int ny, nx, d;
		while(!queue.isEmpty()) {
			cur = queue.poll(); // [y, x, depth]
			
			for(d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				
				if(inBound(ny, nx) && map[ny][nx] != -1 && water[ny][nx] == 0 && !isDest(ny, nx)) {
					water[ny][nx] = cur[2] + 1;
					queue.offer(new int[] {ny, nx, cur[2] + 1});
				}
			}
		}
	}
	
	public static void move() {
		queue.clear();
		queue.add(new int[] {startY, startX, 1});
		map[startY][startX] = 1;
		
		int[] cur = null;
		int ny, nx, d, depth;
		while(!queue.isEmpty()) {
			cur = queue.poll(); // [y, x, depth]
//			System.out.println(Arrays.toString(cur));
			for(d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				depth = cur[2] + 1;
				if(isDest(ny, nx)) {
					System.out.println(depth - 1);
					return;
				}
				
				if(inBound(ny, nx) && map[ny][nx] == 0 && (water[ny][nx] == 0 || water[ny][nx] > depth)) {
					map[ny][nx] = depth;
					queue.offer(new int[] {ny, nx, depth});
				}
			}
			
//			for(int[] m: map) System.out.println(Arrays.toString(m));
//			System.out.println("========================");
		}
		
		System.out.println("KAKTUS");
	}
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("./solving/gold/g4_3055_탈출/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		water = new int[H][W];
		map = new int[H][W];
		
		String str = null;
		char c;
		for(int i = 0; i < H; i++) {
			str = br.readLine();
			
			for(int j = 0; j < W; j++) {
				c = str.charAt(j);
				
				if(c == 'S') {
					startY = i;
					startX = j;
				} else if (c == 'D') {
					destY = i;
					destX = j;
				} else if (c == 'X') {
					map[i][j] = -1;
				} else if (c == '*') {
					queue.offer(new int [] {i, j, 1});
					water[i][j] = 1;
				}
			}
		}
		
		makeWaterMap();
		
//		for(int[] m: map) System.out.println(Arrays.toString(m));
//		System.out.println("========================");
//		for(int[] w: water) System.out.println(Arrays.toString(w));
//		System.out.println("########################");
		
		move();

		return;
	}
}
