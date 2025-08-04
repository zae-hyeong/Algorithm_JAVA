package silver.s3_14501;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] days;
	static int[] moneys;
	static int N;

	public static int getMax(int curDay, int money) {
		if (curDay == N)
			return money;

		int val1 = 0, val2 = 0;

		// 상담
		if (curDay + days[curDay] - 1 < N)
			val1 = getMax(curDay + days[curDay], money + moneys[curDay]);

		// 상담X
		val2 = getMax(curDay + 1, money);

		return Math.max(val1, val2);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		days = new int[N];
		moneys = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			moneys[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(getMax(0, 0));
		br.close();
	}

}
