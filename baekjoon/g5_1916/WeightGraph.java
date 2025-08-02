package g5_1916;

import java.util.*;

class WeightGraph {
	HashMap<Integer, ArrayList<int[]>> hm; // key : 노드 번호, value : ArrayList<[연결된 노드, 가중치]>

	WeightGraph(int N) {
		hm = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			hm.put(i, new ArrayList<>());
		}
	}

	void connect(int a, int b, int w) {
		hm.get(a).add(new int[] {b, w});
	};
	
	ArrayList<int[]> get(int n) {
		return hm.get(n);
	}
}
