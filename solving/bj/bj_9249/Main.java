package bj.bj_9249;

import java.util.*;
import java.io.*;

public class Main {
	static char[] str1, str2;
	static String[][] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_9249/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();

		dp = new String[str1.length + 1][str2.length + 1];
		for (int i = 0; i <= str1.length; i++) {
			for (int j = 0; j <= str2.length; j++) {
				dp[i][j] = "";
			}
		}

		int maxSize = 0;
		String result = null;
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + str1[i - 1];
					if(maxSize < dp[i][j].length()) {
						maxSize = dp[i][j].length();
						result = dp[i][j];
					}
				}
			}
		}

//		for (String[] d : dp) System.out.println(Arrays.toString(d));
		System.out.println(maxSize);
		System.out.println(result);
		br.close();
	}
}