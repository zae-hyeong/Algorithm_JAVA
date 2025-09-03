package sol;

import java.io.*;
import java.util.*;

public class d5_3421_fail {
	static int N, M, result;
	static Set<Integer>[] gSet;
	static List<Integer> subset;
	
	static void check() {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < subset.size(); i++) {
			if(set.contains(subset.get(i))) return;
			
			set.addAll(gSet[subset.get(i)]);
		}
		
		System.out.println(subset.toString());
		result++;
		return;
	}

	static void makeSubset(int idx) {
		if (idx == N + 1) {
//			System.out.println(subset.toString());
			check();
			return;
		}

		makeSubset(idx + 1);
		subset.add(idx);
		makeSubset(idx + 1);
		subset.remove(subset.size() - 1);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/sol/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			gSet = new Set[N + 1];
			for (int i = 1; i <= N; i++)
				gSet[i] = new HashSet<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				gSet[a].add(b);
				gSet[b].add(a);
			}

			subset = new ArrayList<>();
			result = 0;
			makeSubset(1);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
