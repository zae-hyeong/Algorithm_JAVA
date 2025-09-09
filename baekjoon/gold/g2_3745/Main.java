package gold.g2_3745;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_3745/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String input;
		int N;
		int[] arr;

		StringBuilder sb = new StringBuilder();

		int[] LIS;
		int max = 0;

		while ((input = br.readLine()) != null) {
			if (input.trim().isEmpty()) {
		        continue; // 빈 줄이면 다음 줄로 넘어감
		    }
			N = Integer.parseInt(input.trim());
			arr = new int[N];
			LIS = new int[N];
			max = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

				int pos = Arrays.binarySearch(LIS, 0, max, arr[i]);
				if (pos < 0) pos = -(pos + 1); // return -(low + 1);
				
				LIS[pos] = arr[i];
				if (max == pos) max++;
			}

			sb.append(max).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
