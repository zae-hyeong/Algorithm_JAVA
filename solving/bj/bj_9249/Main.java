package bj.bj_9249;

import java.util.*;
import java.io.*;

public class Main {
	static char[] str1, str2, cache;
	static String[][] dp;

	static String lcs(int i, int j) {
		if (i == 0 || j == 0 || str1[i - 1] != str2[j - 1])
			return "";

		if (dp[i - 1][j - 1] != null) {
			return dp[i - 1][j - 1]+ str1[i - 1];
		}

		return dp[i][j] = lcs(i - 1, j - 1) + str1[i - 1];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_9249/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();

		dp = new String[str1.length + 1][str2.length + 1];

		String maxStr = "";

		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					String s = lcs(i, j);

					if (maxStr.length() < s.length()) {
						maxStr = s;
					}
				}
			}
		}

//		for (String[] d : dp) System.out.println(Arrays.toString(d));

		System.out.println(maxStr);
		br.close();
	}
}
