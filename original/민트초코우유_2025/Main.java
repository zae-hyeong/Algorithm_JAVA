package 민트초코우유_2025;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static int N, T;
	static String[][] map;
	static int[][] beliefs, group;
	static String[] president;

	static int idxMapper(String input) {
		if (input.equals("TCM"))
			return 0;
		else if (input.equals("TC"))
			return 1;
		else if (input.equals("TM"))
			return 2;
		else if (input.equals("CM"))
			return 3;
		else if (input.equals("M"))
			return 4;
		else if (input.equals("C"))
			return 5;
		else if (input.equals("T"))
			return 6;
		else
			return -1;
	}

	static String stringCombinator(String aa, String bb) {
		HashSet<Character> set = new HashSet<>();
		for (int i = 0; i < aa.length(); i++)
			set.add(aa.charAt(i));

		for (int i = 0; i < bb.length(); i++)
			set.add(bb.charAt(i));

		String[] arr = new String[3];
		for (Iterator<Character> it = set.iterator(); it.hasNext();) {
			Character c = (Character) it.next();
			int num = idxMapper(Character.toString(c));
			arr[num - 4] = Character.toString(c);
		}

		String result = "";
		for (int i = 2; i >= 0; i--) {
			if (arr[i] == null)
				continue;
			result += arr[i];
		}

		return result;
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static boolean[][] v;

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void bfsFrom(int i, int j, int groupId) {
		v[i][j] = true;
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { i, j });

		int y, x, ny, nx;
		int[] cur = null;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			y = cur[0];
			x = cur[1];
			group[y][x] = groupId;

			for (int d = 0; d < 4; d++) {
				ny = y + dy[d];
				nx = x + dx[d];

				if (inBound(ny, nx) && !v[ny][nx] && map[i][j].equals(map[ny][nx])) {
					v[ny][nx] = true;
					queue.add(new int[] { ny, nx });
				}

			}
		}
	}

	/**
	 * 신앙심 1 높이기 그룹 정하기 대표자 선정하기
	 */
	static int[][] morning() {
		v = new boolean[N][N];
		group = new int[N][N];
		int groupNum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				beliefs[i][j]++;

				if (v[i][j])
					continue;

				bfsFrom(i, j, groupNum++);
			}
		}

//		for (int[] a : group) System.out.println("group : " + Arrays.toString(a));

		int[][] president = new int[groupNum][4]; // [그룹 번호][y, x, 신앙심, 문자열 길이]
		int myGroup = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				myGroup = group[i][j];
				if (president[myGroup][2] < beliefs[i][j]) {
					president[myGroup][0] = i;
					president[myGroup][1] = j;
					president[myGroup][2] = beliefs[i][j];
					president[myGroup][3] = map[i][j].length();
				}
			}
		}

//		for (int[] p : president) System.out.println("president : " + Arrays.toString(p));
		return president;
	}

	static int[][] lunch(int[][] president) {
		int myGroupId, preY, preX;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				myGroupId = group[i][j];
				preY = president[myGroupId][0];
				preX = president[myGroupId][1];

				if (preY == i && preX == j)
					continue;

				beliefs[preY][preX]++;
				president[myGroupId][2]++;
				beliefs[i][j]--;
			}
		}

		Arrays.sort(president, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {

				if (o1[3] != o2[3]) { // 그룹 순서대로(글자수 길이 짧은 순서)
					return o1[3] - o2[3];
				} else if (o1[2] != o2[2]) { // 신앙심 높은 순
					return o2[2] - o1[2];
				} else if (o1[0] != o2[0]) { // 행 번호 작은 순
					return o1[0] - o2[0];
				} else // 열 번호 작은 순
					return o1[1] - o2[1];
			}
		});
		
		return president;

//		for (int[] p : president) System.out.println("PP : " + Arrays.toString(p));
	}
	
	static void evening(int[][] president) {
		boolean[][] changed = new boolean[N][N];
		// 저녁 -> 이제 전파하는 과정
		for (int[] pre : president) {
			int y = pre[0], x = pre[1], power = pre[2] - 1;
			
			if(changed[y][x]) continue;
			
			String myGroup = map[y][x];
			beliefs[y][x] = 1;
			int dir = pre[2] % 4;
			
			while (inBound(y, x) && power > 0) {
				y += dy[dir];
				x += dx[dir];

				if(!inBound(y, x)) break;
				
				if (myGroup.equals(map[y][x]))
					continue;
				
				changed[y][x] = true;
				if (power > beliefs[y][x]) { // 강한 전파
					power -= (beliefs[y][x] + 1);
					beliefs[y][x]++;
					map[y][x] = myGroup;
				} else { // 약한 전파
					beliefs[y][x] += power;
					power = 0;
					map[y][x] = stringCombinator(myGroup, map[y][x]);
				}
			}
			
//			for (String[] m : map) System.out.println("new map : " + Arrays.toString(m));
//			for (int[] b : beliefs) System.out.println("new beliefs : " + Arrays.toString(b));
//			System.out.println();
		}

		int myGroup = -1;
		int[] result = new int[7];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				myGroup = idxMapper(map[i][j]);
				result[myGroup] += beliefs[i][j];
			}
		}

//		for (String[] m : map) System.out.println("new map : " + Arrays.toString(m));
//		for (int[] b : beliefs) System.out.println("new beliefs : " + Arrays.toString(b));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 7; i++) 
			sb.append(result[i]).append(" ");
		
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./original/민트초코우유_2025/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		String input = null;
		map = new String[N][N];
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Character.toString(input.charAt(j));
			}
		}

		beliefs = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				beliefs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			int[][] president = morning();
			president  = lunch(president);
			evening(president);
		}

		br.close();
	}
}
