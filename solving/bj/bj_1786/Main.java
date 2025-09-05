package bj.bj_1786;

import java.util.*;
import java.io.*;

public class Main {
	static String str, targetStr;
	static int[] p;

	static int[] makeTable(String str) {
		int[] table = new int[str.length()];

		int idx = 0;
		for (int i = 1; i < str.length(); i++) {
			while (idx > 0 && str.charAt(i) != str.charAt(idx))
				idx = table[idx - 1];

			if (str.charAt(i) == str.charAt(idx))
				table[i] = ++idx;

		}

		return table;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_1786/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		targetStr = br.readLine();

		int[] table = makeTable(targetStr);
		
		System.out.println(Arrays.toString(table));
		System.out.println();
		br.close();
	}
}
