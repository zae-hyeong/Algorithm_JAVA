package gold.g5_15686;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N = 0, M = 0;
	static ArrayList<int[]> combs = new ArrayList<>();

	static int[] comb;

	static ArrayList<int[]> chickens = new ArrayList<>(); // 치킨집 좌표 {i, j}
	static ArrayList<int[]> houses = new ArrayList<>(); // 가정집 좌표 {i, j}

	static int[][] chickenDistances = null;

	static int[][] arr = null;

	static void makeComb(int depth, int start) {
		if (depth == M) {
			combs.add(Arrays.copyOf(comb, M));
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			comb[depth] = i;
			makeComb(depth + 1, i + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		comb = new int[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int v = Integer.parseInt(st.nextToken());

				arr[i][j] = v;

				if (v == 2)
					chickens.add(new int[] { i, j });
				else if (v == 1)
					houses.add(new int[] { i, j });
			}
		}

		makeComb(0, 0);

		chickenDistances = new int[chickens.size()][houses.size()];

		int dx, dy;
		for (int i = 0; i < chickens.size(); i++) {
//			System.out.println(Arrays.toString(combs.get(i)));

			for (int j = 0; j < houses.size(); j++) {
				dx = Math.abs(chickens.get(i)[0] - houses.get(j)[0]);
				dy = Math.abs(chickens.get(i)[1] - houses.get(j)[1]);
				chickenDistances[i][j] = dx + dy;
			}
		}

		int min = Integer.MAX_VALUE;
		int minSum = 0;
		int totalMin = Integer.MAX_VALUE;
		for (int i = 0; i < combs.size(); i++) {
			minSum = 0;
			for (int k = 0; k < houses.size(); k++) {
				min = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					min = Math.min(min, chickenDistances[combs.get(i)[j]][k]);
				}
//				System.out.println(min);
				minSum += min;
			}
			
			totalMin = Math.min(totalMin,minSum);
		}

		System.out.println(totalMin);
		br.close();
	}

}
