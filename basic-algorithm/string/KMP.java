package string;

import java.util.*;
import java.io.*;

public class KMP {
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

	static int num = 0;
	static StringBuilder sb = new StringBuilder();

	static void KMP(String dest, String find) {
		int[] table = makeTable(find);

		int j = 0;
		for (int i = 0; i < dest.length(); i++) {
			while (j > 0 && dest.charAt(i) != find.charAt(j)) // 글자가 다르면 다음 칸으로 
				j = table[j - 1];
			
			if (dest.charAt(i) == find.charAt(j)) {
				if (j == find.length() - 1) { // 찾는 문자열의 끝 == 찾음
					num++;
					sb.append(i - j + 1).append(' ');
					j++;
					j = table[j - 1];
				} else
					j++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_1786/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		targetStr = br.readLine();

//		System.out.println(Arrays.toString(table));
		KMP(str, targetStr);
		System.out.println(num);
		System.out.println(sb);
		br.close();
	}
}
