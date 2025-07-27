package algorithm;

import java.util.Arrays;

public class Combination {
	static int N = 4, R = 3, C = 0;
	static int[] a = { 1, 2, 3, 4 }, b = new int[R];

	static void comb(int cnt, int start) {
		if (cnt == R) {
			System.out.println(Arrays.toString(b));
			C++;
			return;
		}

		for (int i = start; i < N; i++) {
			b[cnt] = a[i];
			comb(cnt + 1, i + 1); // 중복조합 : comb(cnt + 1, i);
		}
	}

	public static void main(String[] args) throws Exception {
		C = 0;
		comb(0, 0);
		System.out.println(C);
	}
}
