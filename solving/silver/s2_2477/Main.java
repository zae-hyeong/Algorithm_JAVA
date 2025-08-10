package silver.s2_2477;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] inputArr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/silver/s2_2477/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		inputArr = new int[6][2];

		StringTokenizer st;
		int num, dis;
		int[] size = new int[5];
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());

			num = Integer.parseInt(st.nextToken());
			dis = Integer.parseInt(st.nextToken());
			inputArr[i] = new int[] {num, dis};
			size[num]++;
		}

		int bigSize = 1, smallSize = 1;
		int a, b;
		for (int i = 0; i < 6; i++) {
			if(size[inputArr[i][0]] == 1) {
				bigSize *= inputArr[i][1];
				
				if(i ==0) a = inputArr[5][1];
				else a = inputArr[i-1][1];
				
				if( i == 5) b = inputArr[0][1];
				else b=inputArr[i+1][1];
				
				smallSize *= (Math.abs(a - b));
			}
		}

		System.out.println(N * (bigSize - smallSize));

		br.close();
	}
}
