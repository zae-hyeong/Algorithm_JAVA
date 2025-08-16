package m_374;

import java.util.*;

class Solution {
	public static void main(String[] args) {
		topKFrequent(new int[] {1,1,1,2,2,3}, 2);
	}

	public static int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		for(int n : nums) {
			hm.put(n, hm.getOrDefault(n,0)+1);
		}
		
		PriorityQueue<Map.Entry<Integer, Integer>> pq= new PriorityQueue<>((a,b)->b.getValue() - a.getValue());
        pq.addAll(hm.entrySet());
		
		int[] result = new int[k];
		for (int i = 0; i < k; i++) 
			result[i] = pq.poll().getKey();
		
		System.out.println(hm.toString());
		System.out.println(Arrays.toString(result));
		return result;
	}
}
