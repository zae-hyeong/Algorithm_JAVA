package gold.g2_1655_가운데를말해요;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> minPq = new PriorityQueue<>((o1, o2) -> o2 - o1); // 최댓값 가져오기
	static PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1, o2) -> o1 - o2); // 최솟값 가져오기
	
	public static void main(String[] args) throws Exception {
		 //System.setIn(new FileInputStream("./solving/gold/g2_1655_가운데를말해요/input.txt"));
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringBuffer sb = new StringBuffer();
		 
		 N = Integer.parseInt(br.readLine());
		 
		 int num;
		 for(int i = 0; i < N; i++) {
			 num = Integer.parseInt(br.readLine());
			 
			 if(minPq.size() == maxPq.size()) {
				 if(minPq.isEmpty() || num <= minPq.peek()) {
					 minPq.add(num);
				 } else {
					 maxPq.add(num);
					 minPq.add(maxPq.poll());
				 }
			 } else {
				 if(num >= minPq.peek()) {
					 maxPq.add(num);
				 } else {
					 minPq.add(num);
					 maxPq.add(minPq.poll());
				 }
			 }
			 
			 //System.out.println(minPq.toString());
			 //System.out.println(maxPq.toString());
			 //System.out.println(minPq.peek());
			 //System.out.println("########################");
			 sb.append(minPq.peek()).append("\n");
		 }
		 
		 System.out.print(sb.toString());
		 br.close();
	}
}
