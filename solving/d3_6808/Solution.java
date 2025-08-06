package d3_6808;

import java.io.*;
import java.util.*;

public class Solution {
	static int[] myCards, yourCards;

	static List<int[]> perms = new ArrayList<>();
	static final int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	static boolean[] visited = new boolean[9];
	static int[] tmp = new int[9];
	
	static int myWin, yourWin;

	static void makePerms(int depth) {
		if (depth == 9) {
			perms.add(Arrays.copyOf(tmp, 9));
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			tmp[depth] = nums[i];
			makePerms(depth + 1);
			visited[i] = false;
		}
	}

	static List<int[]> combs;

	static void makeCombs() {
		int N = perms.size();

		combs = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				combs.add(new int[] { i, j });
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d3_6808/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder();  

		final int TC = Integer.parseInt(br.readLine());

		makePerms(0);
		makeCombs();
		
		System.out.println(perms.toString());
		System.out.println(combs.toString());

		StringTokenizer st;
		for (int tc = 1; tc <= TC; tc++) {
			System.out.println("-----------------------------------------");
			st = new StringTokenizer(br.readLine());
			myCards = new int[9];
			yourCards = new int[9];

			Set<Integer> s = new HashSet<>();
			for (int i = 0; i < 9; i++) {
				myCards[i] = Integer.parseInt(st.nextToken());
				s.add(myCards[i]);
			}

			for (int i = 1, index = 0; i <= 18; i++) {
				if (s.contains(i))
					continue;

				yourCards[index++] = i;
			}
			myWin = yourWin = 0;
			int my, your, myPoint, yourPoint, myTotal = 0, yourTotal =0;
			for (int i = 0; i < combs.size(); i++) {
				int[] a = combs.get(i);
				my = a[0];
				your = a[1];

				for (int j = 0; j < 9; j++) {
					myPoint = myCards[perms.get(my)[j]];
					yourPoint = yourCards[perms.get(your)[j]];
					
					if (myPoint > yourPoint) myTotal = myTotal + myPoint + yourPoint; 
					if (myPoint < yourPoint) yourTotal = yourTotal + myPoint + yourPoint; 
				}
				if(myTotal>yourTotal) myWin++;
				if(myTotal<yourTotal) yourWin++;
			}
			
			sb.append("#").append(tc).append(" ").append(myWin).append(" ").append(yourWin).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
