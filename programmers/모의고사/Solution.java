package 모의고사;

import java.util.*;

public class Solution {

	final static int[][] testTaker = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 },
			{ 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 }, };

	static int[] scores = { 0, 0, 0 };

	public int[] solution(int[] answers) {

		for (int i = 0; i < answers.length; i++) {
			for(int j = 0; j < 3; j++) {
				if (testTaker[j][i % testTaker[j].length] == answers[i])
					scores[j]++;
			}
		}
		
		int maxVal = Arrays.stream(scores).max().getAsInt();
		
		ArrayList<Integer> answer = new ArrayList<>();
		for(int j = 0; j < 3; j++) {
			if (maxVal == scores[j])
				answer.add(j + 1);
		}
		
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
