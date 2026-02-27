package gold.g4_1043_거짓말;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, numOfInitKnow;
	static boolean[] know;
	static int[] targets;
	static int[] initknow;
	
	static class DisjointSet {
		int N;
		int[] parent;
		DisjointSet(int N) {
			this.N = N;
			parent = new int[N + 1];
			for(int i = 0; i <= N; i++) parent[i] = i;
		}
		
		int find(int target) {
			if(target == parent[target]) return target;
			
			return parent[target] = find(parent[target]);
		}
		
		boolean join(int a, int b) {
			int rootA = this.find(a);
			int rootB = this.find(b);
			
			if(rootA == rootB) return false;
			
			parent[rootB] = rootA;
			return true;
		}
		
		void organize() {
			for(int i = 1; i <= N; i++) {
				parent[i] = this.find(i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./solving/gold/g4_1043_거짓말/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		know = new boolean[N + 1];
		targets = new int[M];
		
		st = new StringTokenizer(br.readLine());
		numOfInitKnow = Integer.parseInt(st.nextToken());
		initknow = new int[numOfInitKnow];
		
		for(int i = 0; i < numOfInitKnow; i++) initknow[i] = Integer.parseInt(st.nextToken());
		
		DisjointSet dSet = new DisjointSet(N);
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			
			int numOfPeople = Integer.parseInt(st.nextToken());
			
			int target = Integer.parseInt(st.nextToken()); // 첫 번째 사람
			targets[m] = target;
			
			for(int i = 1; i < numOfPeople; i++) {
				dSet.join(target, Integer.parseInt(st.nextToken()));
			}
		}
		
		dSet.organize();
		
		for(int i = 0; i < numOfInitKnow; i++) {
			know[dSet.find(initknow[i])] = true;
		}
		
		int result = 0;
		for(int m = 0; m < M; m++) {
			if(!know[dSet.find(targets[m])]) result++;
		}
		
		System.out.println(result);
		br.close();
	}
}
