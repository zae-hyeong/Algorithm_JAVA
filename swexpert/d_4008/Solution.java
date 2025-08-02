package d_4008;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int sumOpers = 0;
	static int[] numOfOpers;
	static char[] operators = { '+', '-', '*', '/' };

	static ArrayList<Integer> operStack = new ArrayList<>();
	static ArrayDeque<Integer> stack;
	static ArrayList<Integer> resultArr;

	public static void calc() {
		int a, b;
		ArrayDeque<Integer> copyStack = new ArrayDeque<>();
		
		for (int t: stack) {
			copyStack.add(t);
		}
		
		for (int i = 0; i < sumOpers; i++) {
			a = copyStack.pop();
			b = copyStack.pop();

			switch (operStack.get(i)) {
			case 0:
				copyStack.push(a + b);
				break;
			case 1:
				copyStack.push(a - b);
				break;
			case 2:
				copyStack.push(a * b);
				break;
			case 3:
				copyStack.push(a / b);
				break;
			default:
				break;
			}
		}

		resultArr.add(copyStack.pop());
	}

	public static void makeOperComb(int depth) {
		if (depth == 0) {
			calc();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (numOfOpers[i] <= 0)
				continue;

			operStack.add(i);
			numOfOpers[i]--;
			makeOperComb(depth - 1);
			operStack.remove(operStack.size() - 1);
			numOfOpers[i]++;
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./swexpert/d_4008/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			numOfOpers = new int[4];
			sumOpers = 0;
			resultArr = new ArrayList<>();
			stack = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				numOfOpers[i] = Integer.parseInt(st.nextToken());
				sumOpers += numOfOpers[i];
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				stack.add(Integer.parseInt(st.nextToken()));
			}

			makeOperComb(sumOpers);
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			
			for(int r: resultArr) {
				max = Math.max(max, r);
				min = Math.min(min, r);
			}

			sb.append("#").append(tc).append(" ").append(max-min).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
