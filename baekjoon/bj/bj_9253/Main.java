package bj.bj_9253;

import java.util.*;
import java.io.*;

public class Main {
	static char[] str1, str2, cmpStr;

	static int[] makeTable(char[] str) {
		int n = str.length;
		int[] arr = new int[n];

		int idx = 0;
		for (int i = 1; i < n; i++) {

			while (idx > 0 && str[i] != str[idx])
				idx = arr[idx - 1];

			if (str[i] == str[idx]) {
				idx++;
				arr[i] = idx;
			}
		}

		return arr;
	}

	static boolean KMP(char[] str, char[] cmp, int[] table) {
		int n = str.length;

		int idx = 0;
		for (int i = 0; i < n; i++) {
			while (idx > 0 && str[i] != cmp[idx])
				idx = table[idx - 1];

			if (str[i] == cmp[idx]) {
				if (idx == cmp.length - 1)
					return true;

				idx++;
			}
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/bj/bj_9253/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		cmpStr = br.readLine().toCharArray();

		int[] table = makeTable(cmpStr);
		boolean v1 = KMP(str1, cmpStr, table);
		boolean v2 = KMP(str2, cmpStr, table);

		System.out.println(v1 && v2 ? "YES" : "NO");
		br.close();
	}
}
