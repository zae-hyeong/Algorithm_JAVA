package silver.s2_2477;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/silver/s2_2477/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[4];
		for (int i = 0; i < 4; i++)
			arr[i] = new ArrayList<>();

		StringTokenizer st;
		int num, dis;
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());

			num = Integer.parseInt(st.nextToken());
			dis = Integer.parseInt(st.nextToken());
			arr[num - 1].add(dis);
		}

		int[] order = { 0, 2, 1, 3 };
		int bigSize = 1, smallSize = 1, oo = 1;
		for (int i = 0; i < 4; i++) {
			System.out.println(arr[order[i]].toString());
			if (arr[order[i]].size() == 1) {
				bigSize *= arr[order[i]].get(0);

				if (i > 0 && arr[order[i - 1]].size() == 2) {
					smallSize *= arr[order[i - 1]].get(0);
				}
				if (i == 0 && arr[order[3]].size() == 2) {
					smallSize *= arr[order[3]].get(0);
				}

				if (i < 3 && arr[order[i + 1]].size() == 2) {
					smallSize *= arr[order[i + 1]].get(1);
				}
				if (i == 3 && arr[order[0]].size() == 2) {
					smallSize *= arr[order[0]].get(1);
				}
			}
		}

		System.out.println(N * (bigSize - smallSize));

		br.close();
	}
}
