package gold.g5_34763_땅따먹기;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	
	static boolean sol() {
		int n = (int) Math.sqrt(K);
		
		int j;
		for(int i = 1; i <= n; i++) {
			if(K % i != 0) continue;

			j = K / i;
			
			if(i + j == N + 1) return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g5_34763_땅따먹기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			System.out.println(sol() ? "YES" : "NO");
		}
		br.close();
	}

}
