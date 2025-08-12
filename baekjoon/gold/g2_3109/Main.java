package gold.g2_3109;

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] arr;
	static int count;
	static int[] dy = { -1, 0, 1 };

	static boolean isValid(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C;
	}

	static boolean dfsFrom(int startY, int startX) {
		if (startX == C - 1) {
			count++;
			return true;
		}

		for (int d : dy) {
			int ny = startY + d;
			int nx = startX + 1;
			
			if (!isValid(ny, nx)) continue;
			if (arr[ny][nx] == '.') {
				arr[ny][nx] = 'x';
				if (dfsFrom(ny, nx)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_3109/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][C];

		for (int i = 0; i < R; i++)
			arr[i] = br.readLine().toCharArray();

		for (int i = 0; i < R; i++) dfsFrom(i, 0);

		System.out.println(count);
		br.close();
	}
}