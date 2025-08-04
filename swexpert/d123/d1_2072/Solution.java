package d123.d1_2072;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		final int N = Integer.parseInt(input);

		long total;

		StringBuilder sb = new StringBuilder();
		
		for (int loop = 1; loop <= N; loop++) {
			br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			total = 0;
			for(int i = 0; i < 10; i++) {
				int n = Integer.parseInt(st.nextToken());
				if (n % 2 != 0) total += n;
			}
			sb.append("#").append(loop).append(" ").append(total).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}
