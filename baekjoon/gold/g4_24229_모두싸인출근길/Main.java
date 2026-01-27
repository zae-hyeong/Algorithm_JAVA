package gold.g4_24229_모두싸인출근길;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] lines;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g4_24229_모두싸인출근길/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		lines = new int[N][2];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(lines, (o1, o2) -> o1[0] - o2[0]);
		
		int start = lines[0][0], end = lines[0][1], maxJump = end + end - start;
		for(int i = 1; i < N; i++) {
//			System.out.println(String.format("(i = %d)[start: %d, end: %d] = maxJump: %d", i, start, end, maxJump));
//			System.out.format("%d < %d = %b\n", maxJump, lines[i][0], maxJump < lines[i][0]);
			if(maxJump < lines[i][0]) break;
			
			if(end < lines[i][0]) { // 겹치지 않는 경우
				start = lines[i][0];
			}
			
			end = Math.max(end, lines[i][1]);
			maxJump = Math.max(maxJump, end + end - start);
			
//			System.out.println(String.format("(i = %d)[start: %d, end: %d] = maxJump: %d", i, start, end, maxJump));
//			System.out.println("==============");
		}

		System.out.println(end);
		br.close();
	}
}
