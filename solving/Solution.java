import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving//input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
//				Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(tc).append(" ").append("result").append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
