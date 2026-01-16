package gold.g2_14907_프로젝트스케줄링;

import java.io.*;
import java.util.*;

public class Main {
	final static int MAX = 26;
	static int[] time, total;
	static int[] inOrder;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./solving/gold/g2_14907_프로젝트스케줄링/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		time = new int[MAX];
		total = new int[MAX];
		inOrder = new int[MAX];
		adjList = new ArrayList[MAX];
		
		for (int i = 0; i < MAX; i++) adjList[i] = new ArrayList<>();
		
		StringTokenizer st;
		String input;
		int curNode;
		while ((input = br.readLine()) != null && !input.equals("")) {
			st = new StringTokenizer(input);

			curNode = st.nextToken().charAt(0) - 'A';
			int t = Integer.parseInt(st.nextToken());

			if (st.hasMoreTokens()) {
				input = st.nextToken();
				
				for (int i = 0; i < input.length(); i++) {
					int from = input.charAt(i) - 'A';
					adjList[from].add(curNode);
					inOrder[curNode]++;
				}
			}
			
			time[curNode] = t;
		}

		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 0; i < MAX; i++) {
			if (inOrder[i] == 0) {
				q.offer(i);
				total[i] = time[i];
			}
		}

		while (!q.isEmpty()) {
			curNode = q.poll();
			
			for (int next : adjList[curNode]) {
				total[next] = Math.max(total[next], total[curNode] + time[next]);
				
				if (--inOrder[next] == 0) q.offer(next);
			}
		}

		int result = 0;
		for (int i = 0; i < MAX; i++) result = Math.max(result, total[i]);
			
		System.out.print(result);
	}
}