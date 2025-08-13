package algorithm;

import java.util.Arrays;

public class NextComb {
	static int N = 4, C = 0;
	static int[] a = { 1, 0, 1, 0 };
	static char[] b = { 'A', 'B', 'C', 'D' };

	static boolean nPn() {
		int i = N - 1; // 1. i 교환위치 찾기
		while (i > 0 && a[i - 1] >= a[i]) i--;
		
		if (i == 0) return false;

		int j = N - 1; // 2. j 교환할 값 찾기
		while (a[i - 1] >= a[j]) j--;
		swap(i - 1, j);

		int k = N - 1; // 3. k 오름차순 정렬
		while (i < k) swap(i++, k--);

		return true;
	}

	static void swap(int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) {
		C = 0;
		Arrays.sort(a);
		do {
			System.out.print(Arrays.toString(a)); 
			C++;
			for (int i = 0; i < N; i++) 
				System.out.print(a[i] != 0 ? b[i] : "");
			
			System.out.println();
		} while(nPn());
	}
}
