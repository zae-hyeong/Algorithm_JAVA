package platinum.p3_5419_북서풍;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] islands;
	static int[] segtree;

	static int get(int rangeStart, int rangeEnd, int left, int right, int idx) { //나보다 작은 영역 가져오기
		if(rangeEnd < left || right < rangeStart) return 0;

		if(left <= rangeStart && rangeEnd <= right) return segtree[idx];

		int mid = (left + right) / 2;
		return get(rangeStart, rangeEnd, left, mid, idx * 2) + get(rangeStart, rangeEnd, mid + 1, right, idx * 2 + 1);
	}

	static void update(int num, int left, int right, int idx) {
		if(num < left || right < num) return;

		segtree[idx] = segtree[idx] + 1;

		if(left >= right) return;

		int mid = (left + right) / 2;
		update(num, left, mid, idx * 2);
		update(num, mid + 1, right, idx * 2 + 1);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/platinum/p3_5419_북서풍/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			islands = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				islands[i][1] = Integer.parseInt(st.nextToken());  // x 좌표
				islands[i][0] = Integer.parseInt(st.nextToken());  // y 좌표
			}

			Arrays.sort(islands, (o1, o2) -> o2[1] - o1[1]); //y축 내림차순

			int idx = 0;
			int prevVal = islands[0][1];
			for(int[] island: islands) { //y축 좌표 압축 + 역순 변환
				if(island[1] < prevVal) {
					prevVal = island[1];
					island[1] = ++idx;
				} else {
					island[1] = idx;
				}
			}
			
			Arrays.sort(islands, (o1, o2) -> {
				if(o1[0] != o2[0]) return o1[0] - o2[0];  // x축 오름차순
				else return o1[1] - o2[1];  // y축 오름차순
			});

			int M = idx + 1;
			//누적합 세그먼트 트리
			segtree = new int[4 * M];
			segtree[0] = -1;
			int result = 0;

			for(int[] island: islands) {
				result += get(0, island[1], 0, M - 1, 1);
				update(island[1], 0, M - 1, 1);
				System.out.println("result : "+ result +" / get : " + get(0, island[1], 0, M - 1, 1)+" / " +Arrays.toString(segtree));
			}

			System.out.println(result);
		}


		br.close();
	}

}
