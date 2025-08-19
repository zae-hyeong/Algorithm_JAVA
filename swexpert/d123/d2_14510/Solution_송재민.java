package d123.d2_14510;

import java.util.*;
import java.io.*;

public class Solution_송재민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int TC;

	static int N; // 2 <= N <= 100
	static int[] heights;
	static int answer;

	static void execute() {
		int maxHeight = -1;
		for (int i = 0; i < N; i++) {
			maxHeight = Math.max(maxHeight, heights[i]);
		}

		int oddDay = 0;
		int evenDay = 0;

		for (int i = 0; i < N; i++) {
			oddDay += (maxHeight - heights[i]) % 2;
			evenDay += (maxHeight - heights[i]) / 2;
		}

		if (oddDay == evenDay) {
			answer = oddDay + evenDay;
		} else if (oddDay > evenDay) {
			int duplicateCnt = evenDay;
			answer = duplicateCnt * 2;
			answer += (oddDay - evenDay) * 2 - 1;
		} else if (oddDay < evenDay) {
			int duplicateCnt = oddDay;
			answer = duplicateCnt * 2;

			int remainedEvenDay = evenDay - duplicateCnt;
			while (remainedEvenDay >= 3) {
				answer += 4;
				remainedEvenDay -= 3;
			}
			if (remainedEvenDay == 1) {
				answer += 2;
			} else if (remainedEvenDay == 2) {
				answer += 3;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		setUp();
		for (int tc = 1; tc <= TC; tc++) {
			init();
			execute();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void setUp() throws Exception {
		// System.setIn(new FileInputStream("res/input1.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
	}

	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		answer = -1;
	}

}

/*
 * 
 * O(N)을 돌면서 해당 나무를 키우기 위해서 얼마나 일자가 필요한지 계산한다.
 * 
 * if(짝수일이 더 많을 경우)
 * 
 * 4 9 3 2 1 1 1 4 6 5 4 를 봤을때 5 0 6 7 8 8 8 5 3 4 5 의 일자가 필요하다 그럼 최대한 그리디적으로
 * 생각해본다. 1일과2일 몇개가 필요할까?
 * 
 * (1,2) (0,0) (0,3) (1,3) (0,4) (0,4) (0,4) (1,2) (1,1) (0,2)
 * 
 * 계산 결과 홀수일 4개, 짝수일 25개가 필요하다.
 * 
 * 그러면 우선 홀수일 4개 짝수일 4개로 8일이 필요하고,
 * 
 * 짝수일을 하루로 최대한 분해해야 한다. 짝수일은 21개가 남았고 홀수일 두개로 짝수일을 넣을 수 있다.
 * 
 * 4일이 지나면 곧 짝수일 3개가 된다. 이를 이용한다.
 * 
 * 만약 짝수일이 3의 배수가 아닐 수가 있다 22일 이라면? 결국 짝수일 하나가 필요하므로 2일이 필요하다.
 * 
 * 23일 이라면? 짝수일 두개가 필요하므로 3일이 필요하다.
 * 
 * if(홀수일이 더 많을 경우)
 * 
 * 8 9 8 6 4 8 8 8 8 8 1 0 1 3 5 1 1 1 1 1 5 의 일자가 필요하다 그럼 최대한 그리디적으로 생각해본다.
 * 1일과2일 몇개가 필요할까?
 * 
 * (1,0) (0,0) (1,0) (1,1) (1,2) (1,0) (1,0) (1,0) (1,0) (1,0)
 * 
 * 계산 결과 홀수일 9개, 짝수일 3개가 필요하다.
 * 
 * 그러면 우선 헐스일 3개 짝수일 3개로 3일이 필요하고,
 * 
 * 홀수일을 짝수일로 최대한 분해해야 한다. 홀수일은 6개가 남았고 홀수일 두개를 짝수일로 대체할 수 없다. 무조건 홀수일 을 해야한다.
 * 
 * 홀수일 6개가 남았으니 12일이 더 필요하다.
 * 
 */

/*
 * 
 * [문제 이해하기] 홀수번쨰 키+1, 짝수 번째 키+2
 * 
 * 모든 나무의 키가 처음에 ㄱ장 키가 컸던 나무와 같아지도록 하는 최소 날짜 수는?
 * 
 * 물을 안 줘도 된다!
 * 
 * 1 2 4 2
 * 
 * 1 2 4 4
 * 
 * 이렇게 둘째 날에 물을 주어도 됨!
 * 
 * [알고리즘 분석하기]
 * 
 * N 작으니까 브루트 포스 될 것 같고
 * 
 * 우선 가장 큰 키의 나무를 찾는다. O(100)
 * 
 * 4 9 3 2 1 1 1 4 6 5 4 라고 치자.
 * 
 * 그럼 우선 물을 계속 주는게 가장 좋을테다.
 * 
 * -----------
 * 
 * 큰 키의 나무의 키가 홀수라면
 * 
 * 당연히 짝수인 친구는 1을 주어야 할 것 이다.
 * 
 * 그렇게 준다면 최종적으로 마지막에 선택의 경우의 수는 2가지 밖에 없을 것이다.
 * 
 * 1,2을 주던지 안주던지,, 그때만 잘 선택하면된다.
 * 
 * ------ 위의 알고리즘이 맞는 것 같은데 ------
 * 
 * 시간 복잡도 계산
 * 
 * 반복한다 -> 120 * 100 = 12000 넣을 친구 찾는다 -> 100
 * 
 * 충분하지 않냐
 * 
 * 
 * 이차원 디피인가..?
 * 
 * 우선 그냥 물을 계속 준다. 마구마구 준다.
 * 
 * 다른 알고리즘을 5분만 생각하고 진행해본다.
 * 
 * -------------
 * 
 * O(N)을 돌면서 해당 나무를 키우기 위해서 얼마나 일자가 필요한지 계산한다.
 * 
 * if(2일이 더 많을 경우)
 * 
 * 4 9 3 2 1 1 1 4 6 5 4 를 봤을때 5 0 6 7 8 8 8 5 3 4 5 의 일자가 필요하다 그럼 최대한 그리디적으로
 * 생각해본다. 1일과2일 몇개가 필요할까?
 * 
 * (1,2) (0,0) (0,3) (1,3) (0,4) (0,4) (0,4) (1,2) (1,1) (0,2)
 * 
 * 계산 결과 홀수일 4개, 짝수일 25개가 필요하다.
 * 
 * 그러면 우선 홀수일 4개 짝수일 4개로 8일이 필요하고,
 * 
 * 짝수일을 하루로 최대한 분해해야 한다. 짝수일은 21개가 남았고 홀수일 두개로 짝수일을 넣을 수 있다.
 * 
 * 4일이 지나면 곧 짝수일 3개가 된다. 이를 이용한다.
 * 
 * 만약 짝수일이 3의 배수가 아닐 수가 있다 22일 이라면? 결국 짝수일 하나가 필요하므로 2일이 필요하다.
 * 
 * 23일 이라면? 짝수일 두개가 필요하므로 3일이 필요하다.
 * 
 * if(1일이 더 많을 경우)
 * 
 * 8 9 8 6 4 8 8 8 8 8 1 0 1 3 5 1 1 1 1 1 5 의 일자가 필요하다 그럼 최대한 그리디적으로 생각해본다.
 * 1일과2일 몇개가 필요할까?
 * 
 * (1,0) (0,0) (1,0) (1,1) (1,2) (1,0) (1,0) (1,0) (1,0) (1,0)
 * 
 * 계산 결과 홀수일 9개, 짝수일 3개가 필요하다.
 * 
 * 그러면 우선 헐스일 3개 짝수일 3개로 3일이 필요하고,
 * 
 * 홀수일을 짝수일로 최대한 분해해야 한다. 홀수일은 6개가 남았고 홀수일 두개를 짝수일로 대체할 수 없다. 무조건 홀수일 을 해야한다.
 * 
 * 홀수일 6개가 남았으니 12일이 더 필요하다.
 * 
 * --------- 아니면 디피인가? -----------
 * 
 */
