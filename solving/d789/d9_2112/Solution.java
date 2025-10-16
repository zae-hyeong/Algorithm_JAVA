package d789.d9_2112;

import java.io.*;
import java.util.*;

public class Solution {
	static int D, W, K, result;
	static int[][] arr;
	
	static int[] selected;
	
	static boolean check() {
		boolean[] pass = new boolean[D];
		HashSet<Integer> hs = new HashSet<>();
		for(int s: selected) hs.add(s);
		
		int prev = 0;
		int cnt = 1;
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < D; j++) {
				if(hs.contains(j)) {  //0일때
					
				}
			}
		}
		
		return true;
	}
	
	static boolean[] v;
	static void select(int idx, int start, int end) {
		if(idx == end) {
			//TODO
			System.out.println(Arrays.toString(selected));
			return;
		}
		
		for (int i = start; i < D; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			selected[idx] = i;
			select(idx + 1, i, end);
			v[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d789/d9_2112/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];
			result = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i <= D; i++) {
				v = new boolean[D];
				selected = new int[i];
				select(0, 0, i);
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
