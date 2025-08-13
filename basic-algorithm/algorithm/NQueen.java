package algorithm;

public class NQueen {
	static int N, ans, col[];

	static boolean able(int i) {
		for (int j = 0; j < i; j++) {
			if (col[i] == col[j] ||  // 같은 행 확인 
				Math.abs(col[i] - col[j]) == i - j)  // 대각선 확인
				return false;
		}
		return true;
	}

	static void nqueen(int i) {
		if (i == N) {
			ans++;
			return;
		}
		
		for (int j = 0; j < N; j++) {
			col[i] = j;
			if (able(i)) nqueen(i + 1);
		}
	}
	
	public static void main(String[] args) {
		N = 4;
		col = new int[N];
		nqueen(0);
		System.out.println(ans);
	}
}
