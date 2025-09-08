package silver.s1_2529;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static long min, max;
	static String maxStr, minStr;
	static int[] perm;
	static boolean[] v;
	static boolean[] isBig;

	static boolean check() {
		int a = perm[0];
		int b = perm[1];

		for (int i = 0; i < N; i++) {
			a = perm[i];
			b = perm[i + 1];

			if (a > b && isBig[i])
				continue;
			if (a < b && !isBig[i])
				continue;

//			System.out.println(a + " : " + b + " : " + isBig[i]);
			return false;
		}

		return true;
	}

	static void perm(int i) {
		if (i >= N + 1) {
//			System.out.println(Arrays.toString(perm));
			if (check()) {
				long val = 0;
				String str = "";
				for (int j = 0; j <= N; j++) {
					val *= 10;
					val += perm[j];
					str += perm[j];
				}

//				System.out.println(val);
				if (min > val) {
					min = val;
					minStr = str;
				}
				if (max < val) {
					max = val;
					maxStr = str;
				}
			}

			return;
		}

		for (int j = 0; j < 10; j++) {
			if (v[j])
				continue;

			v[j] = true;
			perm[i] = j;
			perm(i + 1);
			v[j] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_2529/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		isBig = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			if (st.nextToken().equals(">")) {
				isBig[i] = true;
			}
		}

		v = new boolean[10];
		perm = new int[N + 1];
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		minStr = "";
		maxStr = "";
		perm(0);

		System.out.println(maxStr);
		System.out.println(minStr);
		br.close();
	}
}
