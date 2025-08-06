package d4_1210;

import java.io.*;
import java.util.*;

public class Solution {
	static int result, start;
	static int[][] ladder;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./solution/d4_1210/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			ladder = new int[100][100];
			br.readLine();
			
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if(ladder[i][j] == 2) start = ladder[i][j];
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.print(sb);
		br.close();
	}

}
