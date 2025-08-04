package d4_1233;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d4_1233/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		for (int tc = 1; tc <= 10; tc++) {
			int result = -1;
			N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
			}

			

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}