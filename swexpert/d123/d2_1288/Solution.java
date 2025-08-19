package d123.d2_1288;

import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d2_1288/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		Set<Character> s = new HashSet<>();
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());

			s.clear();
			int num = 1;
			char[] str;
			while (true) {
				str = Integer.toString(N * num++).toCharArray();
				for (char c : str) s.add(c);
				if (s.size() == 10) break;
			}

			sb.append("#").append(tc).append(" ").append(N * (num - 1)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
