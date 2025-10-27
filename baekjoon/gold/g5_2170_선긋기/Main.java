package gold.g5_2170_선긋기;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] lines;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g5_2170_선긋기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		lines = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(lines, (o1, o2) -> {
			return o1[0] - o2[0];
		});

		int start = lines[0][0];
		int end = lines[0][1];

		int sum = 0;
		for (int[] line : lines) {
			if (end >= line[0]) { // 끝점만 갱신
				end = Math.max(end, line[1]);
			} else { // 기존 길이 저장, 앞,뒤 갱신
				sum += end - start;
				start = line[0];
				end = line[1];
			}
		}

		sum += end - start;

		System.out.println(sum);
		br.close();
	}
}
