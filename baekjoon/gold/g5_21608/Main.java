package gold.g5_21608;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int N, result;
	static int numOfStudent;
	static HashMap<Integer, HashSet<Integer>> favor = new HashMap<>();

	static int[][] emptyArr;

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static int calcPoint(int id, int y, int x) {

		Set<Integer> set = favor.get(id);

		int ny, nx, count = 0, point = 0;

		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];

			if (0 <= ny && ny < N && 0 <= nx && nx < N && set.contains(arr[ny][nx]))
				count++;
		}

		point = count > 0 ? 1 : 0;

		for (int i = 1; i < count; i++) {
			point *= 10;
		}

		return point;
	}

	public static void putStudent(int id) {
		int max = 0;
		int x = 0, y = 0, point = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] > 0)
					continue;
				
				point = calcPoint(id, i, j);
				if (point > max) {
					x = j;
					y = i;
					max = point;
				} else if (point == max && emptyArr[i][j] > emptyArr[y][x]) {
					x = j;
					y = i;
				} else if (y == 0 && x == 0 && arr[0][0] > 0) {
					x = j;
					y = i;
				}
			}
		}

		arr[y][x] = id;

		int ny, nx;
		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];

			if (0 <= ny && ny < N && 0 <= nx && nx < N)
				emptyArr[ny][nx]--;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./baekjoon/g5_21608/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		emptyArr = new int[N][N];
		numOfStudent = N * N;

		int nearEmpty = 4;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nearEmpty = 4;
				if (i == 0 || i == N - 1)
					nearEmpty--;
				if (j == 0 || j == N - 1)
					nearEmpty--;

				emptyArr[i][j] = nearEmpty;
			}
		}
		int totalPoint = 0;

		int id;
		String input = "";
		favor.put(0, new HashSet<>());
		for (int i = 1; i <= numOfStudent; i++) {
			input = br.readLine();
			if (input == null)
				break;

			StringTokenizer st = new StringTokenizer(input, " ");
			id = Integer.parseInt(st.nextToken());

			favor.put(id, new HashSet<>());
			while (st.hasMoreTokens()) {
				favor.get(id).add(Integer.parseInt(st.nextToken()));
			}
			putStudent(id);
//			for (int[] a : arr) System.out.println(Arrays.toString(a));
//			System.out.println("-----------------------------------");
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				totalPoint += calcPoint(arr[i][j], i, j);
			}
		}

		System.out.println(totalPoint);
//		for (HashMap.Entry<Integer, HashSet<Integer>> h : favor.entrySet()) System.out.println(h.toString());
		for (int[] a : arr) System.out.println(Arrays.toString(a));

		br.close();
	}
}
