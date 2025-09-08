package gold.g5_9251;

import java.util.*;
import java.io.*;

public class Main {
	static char[] char1, char2;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_9251/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char1 = br.readLine().toCharArray();
		char2 = br.readLine().toCharArray();

		dp = new int[char1.length + 1][char2.length + 1];
		int val;
		for (int i = 1; i <= char1.length; i++) {
			for (int j = 1; j <= char2.length; j++) {
				if (char1[i - 1] == char2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					val = Math.max(dp[i - 1][j], dp[i][j - 1]);
					val = Math.max(val, dp[i - 1][j - 1]);
					dp[i][j] = val;
				}
			}
		}

//		for (int[] d : dp) System.out.println(Arrays.toString(d));

		System.out.println(dp[char1.length][char2.length]);
		br.close();
	}
}
