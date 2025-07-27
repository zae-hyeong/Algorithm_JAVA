package s2_1260;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[] temp;
	static ArrayList<int[]> comb = new ArrayList<>();
	static int result = 0;
	
	public static void makeComb(int[] targetArr, int cnt, int start) {
		if(cnt == M) {
			comb.add(Arrays.copyOf(temp, M));
			return;
		}
		
		for (int i = start; i < N; i++) {
			temp[cnt] = targetArr[i];
			makeComb(int[] targetArr, cnt + 1, i + 1);
		}
		return;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] comb = makeComb(N, M);
		
		
		System.out.println(result);
		br.close();
	}
}
