package d.d_4008;

import java.io.*;
import java.util.*;

public class Solution2 {
	static int[] nums;
	static int[] chars; // + = 0, - = 1, * = 2, / = 1
	static int max, min, numOfChars;
	
	static int[] permArr;
	
	static void calc() {
		
		int prevNum = nums[0], b;
		for (int i = 0; i < numOfChars; i++) {
			b = nums[i + 1];
			switch (permArr[i]) {
			case 0:
				prevNum = prevNum + b;
				break;
			case 1: 
				prevNum = prevNum - b;
				break;
			case 2 : 
				prevNum = prevNum * b;
				break;
			case 3 : 
				prevNum = prevNum / b;
				break;
			default:
				break;
			}
		}
		
		max = Math.max(max, prevNum);
		min = Math.min(min, prevNum);
	}
	
	static void perm(int depth) {
		if(depth == numOfChars) {
			calc();
		}
		
		for (int i = 0; i < 4; i++) {
			if(chars[i] == 0) continue;
			
			chars[i]--;
			permArr[depth] = i;
			perm(depth + 1);
			chars[i]++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d/d_4008/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int TC= Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			nums = new int[N];
			chars = new int[4];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			numOfChars= 0;
			for (int i = 0; i < 4; i++) {
				chars[i] = Integer.parseInt(st.nextToken());
				numOfChars+=chars[i];
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) 
				nums[i] = Integer.parseInt(st.nextToken());
			
			permArr = new int [numOfChars];
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			perm(0);
			
			sb.append("#").append(tc).append(" ").append(max - min).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
