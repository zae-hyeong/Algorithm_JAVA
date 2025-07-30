package s2_24479;

import java.util.*;

class Graph {
	HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
	
	public Graph(int N) {
		for(int i = 1; i <= N; i++) {
			this.hm.put(i, new ArrayList<>());
		}
	}
	
	public void connect(int a, int b) {
		this.hm.get(a).add(b);
		this.hm.get(b).add(a);
	}
	
	public void sort() {
		for(int i = 1; i <= this.hm.size(); i++) {
			Collections.sort(this.hm.get(i));
		}
	}
	
	public void print() {
		for(int i = 1; i <= this.hm.size(); i++) {
			System.out.printf("%d :: %s\n", i, hm.get(i).toString());
			
		}
	}
}
