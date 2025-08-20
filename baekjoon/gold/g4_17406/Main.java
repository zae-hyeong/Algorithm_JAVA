package gold.g4_17406;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] arr;
	static int result; 

	static ArrayList<int[]> inputs;

	static int[] perms;
	static boolean[] v;

	static int rotate() {
		int[][] tmpArr = new int[N][M];
		for (int i = 0; i < N; i++) tmpArr[i] = Arrays.copyOf(arr[i], M);
		
		int r, c, s, width;
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for (int p : perms) {
			int[] input = inputs.get(p);
			r = input[0] - 1;
			c = input[1] - 1;
			s = input[2];
			for (int layer = 1; layer <= s; layer++) {
				ad.clear();
				width = 2 * layer + 1;

				for (int i = 0; i < width; i++) {
					ad.add(tmpArr[r - layer][c - layer + i]);
				}
				
				for (int i = 1; i < width; i++) {
					ad.add(tmpArr[r - layer + i][c + layer]);
				}
				
				for (int i = 1; i < width; i++) {
					ad.add(tmpArr[r + layer][c + layer - i]);
				}
				
				for (int i = 1; i < width - 1; i++) {
					ad.add(tmpArr[r + layer - i][c - layer]);
				}
				
				ad.addFirst(ad.pollLast());
				
				for (int i = 0; i < width; i++) {
					tmpArr[r - layer][c - layer + i] = ad.pollFirst();
				}
				
				for (int i = 1; i < width; i++) {
					tmpArr[r - layer + i][c + layer] = ad.pollFirst();
				}
				
				for (int i = 1; i < width; i++) {
					tmpArr[r + layer][c + layer - i] = ad.pollFirst();
				}
				
				for (int i = 1; i < width - 1; i++) {
					tmpArr[r + layer - i][c - layer] = ad.pollFirst();
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int sum;
		for (int i = 0; i < N; i++) {
			sum = 0;
			for (int j = 0; j < M; j++) {
				sum += tmpArr[i][j];
			}
			min = Math.min(min, sum);
		}
		
		return min;
	}

	static void perm(int idx) {
		if (idx == K) {
			result = Math.min(result, rotate());
			return;
		}

		for (int i = 0; i < K; i++) {
			if (v[i])
				continue;

			v[i] = true;
			perms[idx] = i;
			perm(idx + 1);
			v[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_17406/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int r, c, s;
		inputs = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			inputs.add(new int[] { r, c, s });
		}

		v = new boolean[K];
		perms = new int[K];
		result = Integer.MAX_VALUE;
		perm(0);

		System.out.println(result);
		br.close();
	}
}
