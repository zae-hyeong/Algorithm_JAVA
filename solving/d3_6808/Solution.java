package d3_6808;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int lose, win;
	static boolean[] isSelected;
	static int[] inputCards;
	
	private static void pickCard(int cnt, int sumWin) {
		if (cnt == 9) {
			if (sumWin > 171 - sumWin)
				++win;
			else if (sumWin < 171 - sumWin)
				++lose;
			return;
		}

		for (int i = 1; i <= 18; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;

			if (inputCards[cnt] > i)
				pickCard(cnt + 1, sumWin + i + inputCards[cnt]);
			else
				pickCard(cnt + 1, sumWin);

			isSelected[i] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			lose = 0;
			win = 0;
			inputCards = new int[9];
			isSelected = new boolean[19];

			for (int i = 0; i < 9; i++) {
				inputCards[i] = Integer.parseInt(st.nextToken());
				isSelected[inputCards[i]] = true;
			}

			pickCard(0, 0);

			sb.append(win).append(" ").append(lose).append("\n");
		}

		System.out.print(sb);
		br.close();
	}

}
