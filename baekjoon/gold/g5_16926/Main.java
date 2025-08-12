package gold.g5_16926;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g5_16926/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int loop = (Math.min(N, M) + 1) / 2;
		ArrayDeque<Integer>[] ads = new ArrayDeque[loop];

		for (int i = 0; i < loop; i++) {
			ads[i] = new ArrayDeque<>();
			for (int y = i; y < N - i; y++) 
				ads[i].offer(arr[y][i]);
			
			for (int x = i + 1; x < M - i; x++) 
				ads[i].offer(arr[N - i - 1][x]);
			
			for (int y = N - i - 2; y >= i; y--) 
				ads[i].offer(arr[y][M - i - 1]);
			
			for (int x = M - i - 2; x > i; x--) 
				ads[i].offer(arr[i][x]);
			
		}

		// for (int i = 0; i < loop; i++) System.out.println(ads[i].toString());

		for (int i = 0; i < R; i++)
			for (int j = 0; j < loop; j++)
				ads[j].addFirst(ads[j].pollLast());

		// for (int i = 0; i < loop; i++) System.out.println(ads[i].toString());

		for (int i = 0; i < loop; i++) {
			for (int y = i; y < N - i; y++)
				arr[y][i] = ads[i].pollFirst();

			for (int x = i + 1; x < M - i; x++)
				arr[N - i - 1][x] = ads[i].pollFirst();

			for (int y = N - i - 2; y >= i; y--)
				arr[y][M - i - 1] = ads[i].pollFirst();

			for (int x = M - i - 2; x > i; x--)
				arr[i][x] = ads[i].pollFirst();

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
