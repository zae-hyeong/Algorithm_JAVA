package s2_1260;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, V; //정점 수, 간선 수, 시작 정점
	static Graph graph;
	
	public static ArrayList<Integer> dfsFrom(int startNode) {
		ArrayList<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[N + 1]; 
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.add(startNode);
		visited[startNode] = true;
		
		result.add(startNode);
		
		int node;
		
		while(!stack.isEmpty()) {
			node = stack.pop();
			
			for(int i = 0; i < graph.hm.get(node).size(); i++) {
				if(visited[graph.hm.get(node).get(i)]) continue;
				
				visited[graph.hm.get(node).get(i)] = true;
				result.add(graph.hm.get(node).get(i));
				stack.add(graph.hm.get(node).get(i));
				break;
			}
		}
		
		return result;
	}
	
	public static ArrayList<Integer> bfsFrom(int startNode) {
		ArrayList<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[N + 1]; 
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.add(startNode);
		visited[startNode] = true;
		
		result.add(startNode);
		
		int node;
		
		while(!queue.isEmpty()) {
			node = queue.poll();
			
			for(int i = 0; i < graph.hm.get(node).size(); i++) {
				if(visited[graph.hm.get(node).get(i)]) continue;
				
				visited[graph.hm.get(node).get(i)] = true;
				result.add(graph.hm.get(node).get(i));
				queue.add(graph.hm.get(node).get(i));
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new Graph(N);
		
		int a, b;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			graph.connect(a, b);
		}
		
		graph.sort();
//		graph.print();
		
		ArrayList<Integer> dfsResult = dfsFrom(V);
		ArrayList<Integer> bfsResult = bfsFrom(V); 
		
		for(int i = 0; i < dfsResult.size(); i++) {
			sb.append(dfsResult.get(i));
			if(i < dfsResult.size() - 1)
				sb.append(" ");
		}
		sb.append("\n");
		for(int i = 0; i < bfsResult.size(); i++) {
			sb.append(bfsResult.get(i));
			if(i < bfsResult.size() - 1)
				sb.append(" ");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
