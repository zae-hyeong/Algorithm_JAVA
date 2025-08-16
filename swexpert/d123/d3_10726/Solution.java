package d123.d3_10726;

import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d3_10726/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int mask = (1<<N) - 1;
			boolean flag = (M & mask) == mask;

			sb.append("#").append(tc).append(" ").append(flag ? "ON" : "OFF").append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
