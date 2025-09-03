package sol;

import java.io.*;
import java.util.*;

public class d5_1247 {
	static int N, result;
	static int[][] arr;
	static int[] start, end;
	
	static int getDistance(int[] com1, int[] com2) {
		return Math.abs(com1[0] - com2[0]) + Math.abs(com1[1] - com2[1]);
	}
	
	static int[] permArr;
	static void calc() {
		int sum = 0;
		sum +=getDistance(start, arr[permArr[0]]);
		
		for (int i = 0; i < N - 1; i++) {
			sum +=getDistance(arr[permArr[i]], arr[permArr[i + 1]]);
		}
		
		sum +=getDistance(arr[permArr[ N -1]], end);
		
		result = Math.min(result, sum);
	}

	static boolean[] v;
	static void perm(int cnt) {
		if(cnt == N) {
//			System.out.println(Arrays.toString(permArr));
			calc();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			permArr[cnt] = i;
			perm(cnt + 1);
			v[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/sol/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new int[2];
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			end = new int[2];
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			v = new boolean[N];
			permArr = new int[N];
			result = Integer.MAX_VALUE;
			perm(0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
