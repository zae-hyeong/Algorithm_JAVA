package algorithm;

public class UnionFind {
	static int N;
	static int[][] g;
	static int[] p;

	static void make() {
		p = new int[N];
		for (int i = 0; i < N; i++) p[i] = i;
	}

	static int find(int a) {
		if (p[a] == a)
			return a;
		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) return false;
		
		p[bRoot] = aRoot;

		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
