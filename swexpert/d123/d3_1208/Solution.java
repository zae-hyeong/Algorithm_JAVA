package d123.d3_1208;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int numOfDump;
	static int[] arr;
	static PriorityQueue<Integer> minPq;
	static PriorityQueue<Integer> maxPq;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d3_1208/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			int minDiff = Integer.MAX_VALUE;
			minPq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
			maxPq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
			numOfDump = Integer.parseInt(br.readLine());
			arr = new int[100];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				minPq.offer(arr[i]);
				maxPq.offer(arr[i]);
			}
			
			int big, small;
			for (int i = 0; i < numOfDump; i++) {
				big = maxPq.poll();
				small = minPq.poll();
				
				if(big -1 <= small + 1) {
					minDiff = big - small;
					break;
				}
				
				minPq.offer(small + 1);
				maxPq.offer(big - 1);
			}
			minDiff = maxPq.poll() - minPq.poll();
			
			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
