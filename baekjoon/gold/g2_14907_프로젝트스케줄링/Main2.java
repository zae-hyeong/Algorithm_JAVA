package gold.g2_14907_프로젝트스케줄링;

import java.util.*;
import java.io.*;

public class Main2 {
	static int N = 0;
	static HashMap<Character, Set<Character>> required, next;
	static HashSet<Character> visited;
	static HashMap<Character, Integer> cost, endTime;
	
	static void log(Object str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g2_14907_프로젝트스케줄링/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = null;
		StringTokenizer st = null;
		
		required = new HashMap<>();
		next = new HashMap<>();
		cost = new HashMap<>();
		endTime = new HashMap<>();
		visited = new HashSet<>();
		
		ArrayDeque<Character> queue = new ArrayDeque<>();
		
		char curNode, nextNode;
		int c;
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			N++;
			
			curNode = st.nextToken().charAt(0);
			c = Integer.parseInt(st.nextToken());
			
			cost.put(curNode, c);
			endTime.put(curNode, 0);
			
			if(!st.hasMoreTokens()) {
				queue.add(curNode);
				endTime.put(curNode, c);
				continue;
			}
				
			input = st.nextToken();
			for(int i = 0; i < input.length(); i++) {
				nextNode = input.charAt(i);
				
				next.computeIfAbsent(nextNode, k -> new HashSet<>()).add(curNode);
				required.computeIfAbsent(curNode, k -> new HashSet<>()).add(nextNode);
			}
		}
		
//		log(queue.toString());
//		log(cost.toString());
//		log(required.toString());
//		log(next.toString());
		
		int result = 0;
		
		while(!queue.isEmpty()) {
			curNode = queue.poll();
			
			visited.add(curNode);
			c = endTime.get(curNode);
			result = Math.max(result, c);
			
			for(char nNode : next.getOrDefault(curNode, new HashSet<>())) {
				if(visited.contains(nNode)) continue;
				
				endTime.put(nNode, Math.max(endTime.get(nNode), c + cost.get(nNode)));
				required.get(nNode).remove(curNode);
				
				if(required.get(nNode).isEmpty()) {
					queue.add(nNode);
				}
			}
		}

		System.out.println(result);
		br.close();
	}
}