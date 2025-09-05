package d.d_2112;

import java.util.*;
import java.io.*;

public class Solution {
	static int H, W, K;
	static boolean[][] cells;

	static boolean check(ArrayList<Integer> ss, boolean[] comb) {
		int ssIdx = 0;

		boolean flag = false;
		boolean prev = false;
		int cnt = 0;

		for (int x = 0; x < W; x++) {
			flag = false;
			ssIdx = 0;
			prev = cells[0][x];
			cnt = 1;

			if (!ss.isEmpty() && ss.get(ssIdx) == 0) {
				prev = comb[ssIdx++];
			}

			for (int y = 1; y < H; y++) {
//				System.out.println(y+":"+x + "=" +cells[y][x]+ ">>" + "cnt:" +cnt +" : "   +ss.toString() +"????"+ ssIdx);
				if (!ss.isEmpty() && ssIdx < ss.size() && ss.get(ssIdx) == y) {
					if (prev == comb[ssIdx]) {
						cnt++;
						if (cnt >= K) {
							flag = true;
							break;
						}
					} else {
						prev = comb[ssIdx];
						cnt = 1;
					}
					ssIdx++;
					continue;
				}

				if (prev == cells[y][x]) {
					cnt++;
					if (cnt >= K) {
						flag = true;
						break;
					}
				} else {
					prev = cells[y][x];
					cnt = 1;
				}
			}

			if (flag == false)
				return false;
		}

		return true;
	}

	static void combCheck(int cnt, ArrayList<Integer> ss, boolean[] comb) {
		if (cnt >= ss.size()) {
			System.out.println(ss.toString() + " : " + Arrays.toString(comb));
//			System.out.println("result: >> " +check(ss, comb));
			if (ss.size() >= result)
				return;
			if (check(ss, comb)) {
				System.out.println("RESULT::::::" + result);
				result = ss.size();
			}
			return;
		}

		comb[cnt] = false;
		combCheck(cnt + 1, ss, comb);
		comb[cnt] = true;
		combCheck(cnt + 1, ss, comb);
	}

	static int result;
	static ArrayList<ArrayList<Integer>>[] ssetList;

	static void subset(int idx, ArrayList<Integer> sset) {
		if (sset.size() >= K - 1 || idx >= H) {
//			System.out.println(sset.toString());
			ssetList[sset.size()].add(new ArrayList<Integer>(sset));
			return;
		}

		subset(idx + 1, sset);
		sset.add(idx);
		subset(idx + 1, sset);
		sset.remove(sset.size() - 1);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/d/d_2112/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cells = new boolean[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				}
			}

//			for(boolean[] c : cells) System.out.println(Arrays.toString(c));

			ssetList = new ArrayList[K];
			for (int i = 0; i < K; i++)
				ssetList[i] = new ArrayList<>();

			subset(0, new ArrayList<>());
//			for (int i = 0; i <= K; i++) System.out.println(ssetList[i].toString()); 
//			if (K ==1) {
//				sb.append("#").append(tc).append(" ").append(0).append("\n");
//				continue;
//			}
			
			result = K;
			for (int i = 0; i < K; i++) {
				if (i > result)
					break;

				for (ArrayList<Integer> ss : ssetList[i]) {
					combCheck(0, ss, new boolean[i]);
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
