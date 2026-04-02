package gold.g3_1005_ACMCraft;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static List<Integer>[] next;
	static int[] count, time, myTime;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("./solving/gold/g3_1005_ACMCraft/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			next = new List[N + 1];
			count = new int[N + 1];
			time = new int[N + 1];
			myTime = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 1; i <= N; i++) {
				myTime[i] = Integer.parseInt(st.nextToken()); 
				next[i] = new ArrayList<>();
			}
			
			int a, b;
			for(int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				next[a].add(b);
				count[b]++;
			}
			
			//System.out.println(Arrays.toString(next));
			//System.out.println(Arrays.toString(count));
			
			int target = Integer.parseInt(br.readLine());
			
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			for(int i = 1; i <= N; i++) {
				if(count[i] == 0) {
					queue.add(i);
					time[i] = myTime[i];
				}
			}
			
			if(time[target] != 0) {
				sb.append(time[target]).append("\n");
				continue;
			}
			
			int cur;
			while(!queue.isEmpty()) {
				cur = queue.poll();
				
				if(cur == target) {
					break;
				}
				
				for(int n: next[cur]) {
					count[n]--;
					if(count[n] == 0) queue.add(n);
					
					time[n] = Math.max(time[n], time[cur] + myTime[n]);
				}
			}

			sb.append(time[target]).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
