package d123.d3_5215;

import java.io.*;
import java.util.*;

public class Solution {
	static int limit;
	static int[] calories, scores;
	static int result;

	public static void getMax(int index, int totalScore, int totalCalorie) {
		if (index >= calories.length) {
			result = Math.max(totalScore, result);
			return;
		}
		
		if (totalCalorie + calories[index] <= limit) {
			getMax(index + 1, totalScore + scores[index], totalCalorie + calories[index]);
		}
		getMax(index + 1, totalScore, totalCalorie);
		
		return;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		int numOfFoods;
		StringTokenizer st;

		for (int loop = 1; loop <= N; loop++) {
			st = new StringTokenizer(br.readLine());
			numOfFoods = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			result = 0;

			calories = new int[numOfFoods];
			scores = new int[numOfFoods];

			for (int i = 0; i < numOfFoods; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			getMax(0, 0, 0);

			sb.append("#").append(loop).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
