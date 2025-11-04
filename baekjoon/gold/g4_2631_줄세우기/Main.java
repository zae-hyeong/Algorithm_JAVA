package gold.g4_2631_줄세우기;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr, len;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/gold/g4_2631_줄세우기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int [N + 1];
		len = new int [N + 1];
		arr[0] = 0;
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = i - 1; j >= 0; j--) {
				if(arr[j] < arr[i]) {
					len[i] = Math.max(len[i], len[j] + 1);
				}
			}
			max = Math.max(max, len[i]);
		}
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(len));
		
		System.out.println(N - max);
		br.close();
	}

}
