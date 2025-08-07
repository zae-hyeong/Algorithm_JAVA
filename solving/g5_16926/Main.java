package g5_16926;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d4_1233/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
