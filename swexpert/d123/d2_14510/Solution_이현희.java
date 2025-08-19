package d123.d2_14510;

import java.util.*;
import java.io.*;

// 어떤 나무에 1을 줄 지, 2를 줄 지는 크게 중요하지 않음
// 따라서 줘야하는 물의 총량을 가지고 판단해야 함
// 1. 최대 크기의 나무를 찾는다
// 2. 모든 나무들에 대해서, 최대 크기의 나무에 비해 얼마나 더 커야 하는 지 확인한다
// *2-1. 크기 차이(=성장필요량)를 메꾸기 위해 최대한 2를 많이 쓰면 몇 번 2를 줘야하는지 구한다. 만약 홀수라면, 1을 한 번 줘야하고 짝수라면, 1을 한 번도 안줘도 된다.
// *2-2. 결과적으로 모든 나무들에 대해 필수적으로 1을 줘야하는 횟수와, 2를 줘야하는 횟수(1을 2번 주는 것으로 변경 가능)를 구할 수 있다.
// 3. 이제 문제는, 얼마나 2를 1로 변환해서, 최대한 1과 2가 비슷한 형태를 띠게 만드느냐가 된다. 그래야 가장 빨리 끝나기 때문.
// 4. 1 횟수가 2 횟수보다 큰 경우, 1을 2로 변환하는 것은 불가능 하므로, (1 횟수 - 1) * 2 + 1이 답이 된다.(1을 먼저 주기 때문)
// 4-1. 2 횟수가 1 횟수보다 큰 경우, 2를 1로 변환해야 한다. 이는 규칙이 있는데, 차이가 2~4인 경우 1개만 변환하면 최적이고, 차이가 5~7인 경우 2개만 변환하면 최적이다. 즉, (2 횟수 + 1)/3 만큼 변환하면 된다.
// 4-2. 변환을 마친 뒤에 1 횟수가 2 횟수보다 큰 경우, 위와 같은 식으로 계산한다.
// 4-3. 변환을 마친 뒤에 2 횟수가 1 횟수보다 큰 경우, 2 횟수 * 2가 답이 된다.
// 결국 1과 2를 어떻게 배치할 것인가의 문제
public class Solution_이현희 {

	static int N, T;
	static int[] trees;
	static int max, oneCnt, twoCnt, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			// 1. 최대 크기를 찾는다.
			trees = new int[N];
			st = new StringTokenizer(br.readLine());

			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, trees[i]);
			}

			// 2. 모든 나무에 비해 무조건 1을 줘야하는 횟수와 2를 줘야하는 횟수를 구한다.
			oneCnt = 0;
			twoCnt = 0;
			for (int i = 0; i < N; i++) {
				int r = max - trees[i];
				oneCnt += r % 2;
				twoCnt += r / 2;
			}

			System.out.println(oneCnt + " " + twoCnt);

			// 4-1. 2 횟수가 1보다 큰 경우 변환해야할 개수를 구하고, 변환한다.
			int diff = twoCnt - oneCnt;
			if (diff >= 2) {
				int change = (diff + 1) / 3;
				twoCnt -= change;
				oneCnt += 2 * change;
			}

			// 4. 2 횟수와 1 횟수의 관계에 따라 답을 구한다.
			if (twoCnt >= oneCnt) {
				ans = twoCnt * 2;
			} else if (twoCnt < oneCnt) {
				ans = oneCnt * 2 - 1;
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}