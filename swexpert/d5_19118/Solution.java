package d5_19118;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N = 0;
	static int[] arr = null;
	static int result = 0;

	static int crashedBuildings() {
		int[] count = new int[N];
		count[0] = 1;
		int flag;
		
		for (int i = 1; i < N; i++) {
			flag = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[i] <= arr[j]) continue;
				
				flag = 1;
				if(count[i] < count[j] + 1)
					count[i] = count[j] + 1;
			}
			if(flag == 0) count[i] = 1;
		}
		
//		System.out.println(Arrays.toString(count));
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			if(max < count[i]) max = count[i]; 
		}

		return N - max;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			result = crashedBuildings();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
