package gold.g1_19236_청소년상어;

import java.util.*;
import java.io.*;

public class Main {
	static int N = 4, max = 0;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static void dfs(int[][] numMap, int[][] dirMap, int[][] pos, int[] sharkPos, int ateNum) {
//		for(int[] m: numMap) System.out.println(Arrays.toString(m));
//		System.out.println("================CUR SHARK : " +Arrays.toString(sharkPos)+ " CURRENT ATENUM : " + ateNum + "=============");
//		System.out.println("================MAP=============");
//		for(int[] m: numMap) System.out.println(Arrays.toString(m));
//		System.out.println("================DIR=============");
//		for(int[] m: dirMap) System.out.println(Arrays.toString(m));
//		System.out.println("================================");
		
		int sharkDir = dirMap[sharkPos[0]][sharkPos[1]]; 
		int ny = sharkPos[0] + dy[sharkDir];
		int nx = sharkPos[1] + dx[sharkDir];
		
//		if(max < ateNum) {
//			System.out.println("================MAX UPDATE : " + ateNum + "=============");
//			for(int[] m: numMap) System.out.println(Arrays.toString(m));
//		}
		max = Math.max(max, ateNum);
		
		while(inBound(ny, nx)) {
			if(numMap[ny][nx] != 0) {
				int[][] newNumMap = new int[4][4];
				int[][] newDirMap = new int[4][4];
				int[][] newPos = new int[17][2];
				
				for(int i = 0; i < 4; i++) {
					newNumMap[i] = Arrays.copyOf(numMap[i], 4);
					newDirMap[i] = Arrays.copyOf(dirMap[i], 4);
				}
				
				for(int i = 1; i <= 16; i++) newPos[i] = Arrays.copyOf(pos[i], 2);
				
				// 물고기 먹기
				int[] newSharkPos = {ny, nx};
				int fishNum = newNumMap[ny][nx];
				newNumMap[ny][nx] = 0;
				newPos[fishNum][0] = newPos[fishNum][1] = -1; 
				newDirMap[sharkPos[0]][sharkPos[1]] = -1;
				
//				System.out.println("================EAT FISH / ATENUM : " + (ateNum + fishNum) + "=============");
//				for(int[] m: newNumMap) System.out.println(Arrays.toString(m));
//				System.out.println("================DIR=============");
//				for(int[] m: newDirMap) System.out.println(Arrays.toString(m));
				
				//물고기 움직이기
//				System.out.println("================MOVE FISH=============");
				moveFish(newNumMap, newDirMap, newPos, newSharkPos);			
//				for(int[] m: newNumMap) System.out.println(Arrays.toString(m));
				
				dfs(newNumMap, newDirMap, newPos, newSharkPos, ateNum + fishNum);
			}
			
			ny = ny + dy[sharkDir];
			nx = nx + dx[sharkDir];
		}
		
//		if(!move) System.out.println("CANNOT MOVE ANYMORE!!!!!!!!!!!!!!!");
	}
	
	static boolean inBound(int y, int x)  {
		return 0 <= y && y < N && 0 <= x && x < N;  
	}
	
	static boolean meetShark(int[] sharkPos, int y, int x) {
		return sharkPos[0] == y && sharkPos[1] == x;
	}
	
	static void moveFish(int[][] numMap, int[][] dirMap, int[][] pos, int[] sharkPos) {
		int nextY = -1, nextX = -1;
		
		int d, y, x, dir;
		for(int i = 1; i <= 16; i++) {
			if(pos[i][0] == -1) continue; // 이미 먹힘 -> 패스
			
//			System.out.println("MOVE : " + i);
			dir = dirMap[pos[i][0]][pos[i][1]];
			y = pos[i][0];
			x = pos[i][1];
			
			for(d = 0; d < 8; d++) {
				nextY = y + dy[(dir + d) % 8];
				nextX = x + dx[(dir + d) % 8];
				
				// 이동 가능
				if(inBound(nextY, nextX) && !meetShark(sharkPos, nextY, nextX)) break;
			}
			
			if(d == 8) continue; // 이동할 수 없음
			
			// 이동 가능 -> 위치 바꾸기 
//			System.out.format("y : %d, x : %d, nextY : %d, nextX : %d \n", y, x, nextY, nextX);
			dirMap[y][x] = (dir + d) % 8;
			switchFishPos(numMap, dirMap, pos, y, x, nextY, nextX);
		}
	}
	
	static void switchFishPos(int[][] num, int[][] dir, int[][] pos, int y, int x, int ny, int nx) {
//		System.out.format("###switchFishPos : %d [%d, %d] <-> %d [%d, %d] /// pos : %s <-> %s \n", num[y][x], y, x, num[ny][nx], ny, nx, Arrays.toString(pos[num[y][x]]), Arrays.toString(pos[num[ny][nx]]));
		
		pos[num[y][x]][0] = ny;
		pos[num[y][x]][1] = nx;
		pos[num[ny][nx]][0] = y;
		pos[num[ny][nx]][1] = x;
		
		int tmp = num[y][x];
		num[y][x] = num[ny][nx];
		num[ny][nx] = tmp;
		
		tmp = dir[y][x];
		dir[y][x] = dir[ny][nx];
		dir[ny][nx] = tmp;
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g1_19236_청소년상어/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] pos = new int[17][2]; // 번호, 좌표
		
		StringTokenizer st = null;
		int[][] numMap = new int[4][4];
		int[][] dirMap = new int[4][4];
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				numMap[i][j] = Integer.parseInt(st.nextToken());
				dirMap[i][j] = Integer.parseInt(st.nextToken()) - 1;
				pos[numMap[i][j]][0] = i;
				pos[numMap[i][j]][1] = j;
			}
		}
		
		// (0, 0) 먹기
		max = numMap[0][0];
		pos[numMap[0][0]][0] = pos[numMap[0][0]][1] = -1;
		numMap[0][0] = 0;
		
		int[] sharkPos = {0, 0};
		
		moveFish(numMap, dirMap, pos, sharkPos);
		
		dfs(numMap, dirMap, pos, sharkPos, max);
		
		System.out.println(max);
		br.close();
	}
}
