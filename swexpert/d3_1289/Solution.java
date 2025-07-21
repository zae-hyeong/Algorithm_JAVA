package swexpert.d3_1289;

import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		String input;
		int result;
		char lastVal, c;

		for (int loop = 1; loop <= N; loop++) {
			input = br.readLine();
			result = 0;
			lastVal = '0';
			
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				
				if(lastVal != c) {
					lastVal = c;
					result++;
				}
			}
			
			sb.append("#").append(loop).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
