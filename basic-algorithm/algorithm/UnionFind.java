package algorithm;

import java.util.Arrays;

class DisjointSet {
		private int[] arr;
		
		DisjointSet(int N) {
			this.arr = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				arr[i] = i;
			}
		}
		
		boolean union(int a, int b) {
			int rootA = this.find(a);
			int rootB = this.find(b);
			
			if(rootA == rootB) return false;
			
			this.arr[rootB] = rootA;
			
			return true;
		}
		
		int find(int target) {
			if(arr[target] == target) return target;
			
			return arr[target] = find(arr[target]);
		}
		
		public String toString() {
			return Arrays.toString(arr);
		}
	}

public class UnionFind {
	
	public static void main(String[] args) {
		DisjointSet set = new DisjointSet(6);
		
		set.union(1, 2);
		set.union(2, 3);
		set.union(1, 4);
		set.union(5, 6);
		System.out.println(set.toString());
	}
	/*
	 * 1 - 2 - 3
	 *  \           5-6
	 *   4
	 */
}