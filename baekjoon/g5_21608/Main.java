package g5_21608;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int N, result;
	static int numOfStudent;
	static HashMap<Integer, int[]> favor;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		numOfStudent = N * N;

		StringTokenizer st;
		int id;
		for (int i = 0; i < numOfStudent; i++) {
			st = new StringTokenizer(br.readLine());
			id = Integer.parseInt(st.nextToken());

			favor.put(id, new int[4]);
			for (int j = 0; j < 4; j++) {
				favor.get(id)[j] = Integer.parseInt(st.nextToken());
			}
		}

		br.close();
	}
}
