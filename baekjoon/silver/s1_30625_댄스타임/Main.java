package silver.s1_30625_댄스타임;

import java.util.*;
import java.io.*;

public class Main {
	static final int DIV = 1000000007;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./solving/silver/s1_30625_댄스타임/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] dp = new long[2];
		dp[0] = 1; // 현재 라운드까지 모두 올바른 춤을 춘 경우의 수
		dp[1] = 0; // 현재 라운드까지 정확히 한 번 올바르지 않은 춤을 춘 경우의 수
		
		int dance;
		boolean isFront;
		long cntCorrect, cntWrong;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dance = Integer.parseInt(st.nextToken());
			isFront = Integer.parseInt(st.nextToken()) == 0;
			
			if (isFront) {
                cntCorrect = 1;      // 맞은 경우 -> 우진과 같은 춤 (1가지)
                cntWrong = M - 1;    // 틀린 경우 -> 우진과 다른 춤 (M-1가지)
            } else {
                cntCorrect = M - 1;  // 맞은 경우 -> 우진과 다른 춤 (M-1가지)
                cntWrong = 1;        // 틀린 경우 -> 우진과 같은 춤 (1가지)
            }
			
            // 1. 이번 라운드까지 한 번 틀린 경우의 수 = (이전까지 다 맞다가 이번에 틀림) + (이전까지 한 번 틀렸는데 이번엔 맞음)
            long nextOneWrong = (dp[0] * cntWrong + dp[1] * cntCorrect) % DIV;
            
            // 2. 이번 라운드도 다 맞는 경우 = (이전까지 다 맞았고 이번에도 맞음)
            long nextAllCorrect = (dp[0] * cntCorrect) % DIV;

            dp[0] = nextAllCorrect;
            dp[1] = nextOneWrong;
		}

		System.out.println((dp[0] + dp[1]) % DIV);
		br.close();
	}
}
