package gold.g3_14437_준오는심술쟁이;

import java.io.*;

public class Main {
	static int[][] dp;
	
	static int topDown(int k, int n) {
		if(k == 0) return 1;
		if(k > 25 * n) return 0;
		
		if(dp[k][n] != 0) return dp[k][n];
		
		int sum = 0, loop = Math.min(k, 25);
		
		// i == 사용한 숫자의 개수
		for(int i = 0; i <= loop; i++) {
			sum += topDown(k - i, n - 1);
			sum %= 1_000_000_007;
		}
		
		return dp[k][n] = sum;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_14437_준오는심술쟁이/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		int N = br.readLine().length();
		
		dp = new int[K + 1][N + 1];
		
		System.out.println(topDown(K, N));
		br.close();
	}
}
