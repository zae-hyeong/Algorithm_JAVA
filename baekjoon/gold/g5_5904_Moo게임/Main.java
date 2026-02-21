package gold.g5_5904_Moo게임;

import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("./solving/gold/g5_5904_Moo게임/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		int num = 3;
		int k = 0;
		while(num < N) {
			k++;
			num = num * 2 + k + 3;
		}
		
		int prevNum = (num - k - 3) / 2;
		boolean isM = false; 
		while(true) {
			// System.out.format("k=%d, num=%d, prevNum=%d, N=%d\n", k, num, prevNum, N);
			if(k <= 0) {
				isM = N == 1 ? true : false;
				break;
			}
			
			if(prevNum < N && N < prevNum + k + 3) { // 중간 zone
				isM = (N == prevNum + 1) ? true : false;
				break;
			}
			
			// 첫/끝 zone
			if(N > prevNum) N = N - prevNum - k - 3;
			
			num = prevNum;
			k--;
			prevNum = (num - k - 3) / 2;
		}
		
		System.out.println(isM ? "m" : "o");
	}
}
