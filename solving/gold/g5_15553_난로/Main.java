package gold.g5_15553_난로;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g5_15553_난로/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		
		int prevNum, curNum, firstNum;
		
		curNum = firstNum = Integer.parseInt(br.readLine());
		prevNum = curNum + 1;

		for(int i = 1; i < N; i++) {
			curNum = Integer.parseInt(br.readLine());
//			System.out.println("term : " + (curNum - prevNum));
			pq.add(curNum - prevNum);
			prevNum = curNum + 1;
		}
		
//		System.out.println("prevNum :" + prevNum + " / firstNum : " + firstNum);
		int totalTime = prevNum - firstNum;
		
		for(int i  = 1; i < K; i++) totalTime -= pq.poll();
		
		System.out.println(totalTime);
		
		br.close();
	}
}
