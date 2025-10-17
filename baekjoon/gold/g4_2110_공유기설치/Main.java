package gold.g4_2110_공유기설치;

import java.util.*;
import java.io.*;

/**
 * 아이디어 : 1 크기로 뛰었을때, C개를 선택할 수 있는가 -> 확인 2 크기로 뛰었을때, ... , 최대 크기로 뛰었을때 C개를 선택할
 * 수 있는가 이렇게 돌다가 최대 값을 찾으면 됨.
 * 
 * 하지만 이렇게 하면 20만 * 10억 가능 -> 그래서 이분탐색 하면 됨
 * 
 * 1일때 가능 -> (맨끝 - 맨 처음 = K 가정) 크기일때 불가능 -> K+1/2일때 가능한지 확인 -> 이렇게 반복하다가 최종적으로
 * 가능한 가장 큰 수를 찾으면 됨.
 * 
 * 그런데 다음 숫자는 어떻게 찾지?
 * ex) 현재 숫자 4, 점프 숫자 : 8 -> 그 다음 배열쪽에 12가 있는걸 어떻게 알아?
 * bianrySearch를 하면 찾는 범위를 지정 가능 -> idx + 1 ~ idx + 점프 범위에서만 찾으면 됨(어차피 이 안에 있어야 함)
 * 
 * 그렇게 하면 총 시간복잡도가 log(10억) * 확인과정(C) <= 10만 * 20만 -> 근데 이게 되네
 * 
 */

public class Main {
	static int N, C, maxNum;
	static int[] arr;

	static boolean checkAvailable(int K) {
		int idx = 0, num = arr[0];
		int nextIdx = 0;
		for (int count = 1; count < C; count++) {
			nextIdx = Arrays.binarySearch(arr, idx + 1, Math.min(N, idx + K + 1), num + K);

			if (nextIdx < 0)
				nextIdx = -1 * nextIdx - 1;
			
			idx = nextIdx;
			if (idx >= N || (count < C -1 && idx >= N -1))
				return false;
			
			num = arr[idx];
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_2110_공유기설치/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		br.close();

		maxNum = 1;

		int start = 1;
		int end = arr[N - 1] - arr[0];

		int mid = -1;
		while (start <= end) {
			mid = (start + end) / 2;
			if (checkAvailable(mid)) {
				maxNum = Math.max(maxNum, mid);
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		System.out.println(maxNum);

	}
}
