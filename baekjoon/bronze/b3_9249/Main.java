package bronze.b3_9249;

import java.util.*;
import java.io.*;

public class Main {
	static char[] str1, str2, cache;
	static String[][] dp;

	static String lcs(int i, int j) {
		if (i < 0 || j < 0 || str1[i] != str2[j])
			return "";

		if (dp[i][j] != null) {
			return dp[i][j];
		}

		dp[i][j] = Character.toString(str1[i]);
		return lcs(i - 1, j - 1) + str1[i];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_9249/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();

		dp = new String[str1.length][str2.length];

		String maxStr = "";

		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
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
