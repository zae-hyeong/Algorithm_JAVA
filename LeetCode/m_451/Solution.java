package m_451;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	public static void main(String[] args) {
		System.out.println(frequencySort("tree"));
		System.out.println(frequencySort("cccaaa"));
		System.out.println(frequencySort("Aabb"));
	}

	static public String frequencySort(String s) {
		HashMap<Character, Integer> hm = new HashMap<>();
		char[] cs = s.toCharArray();
		for (char c : cs) {
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		pq.addAll(hm.entrySet());

		StringBuilder sb = new StringBuilder();
		Map.Entry<Character, Integer> tmp;
		while(!pq.isEmpty()) {
			tmp = pq.poll();
			for (int j = 0; j < tmp.getValue(); j++) 
				sb.append(tmp.getKey());
		}

		return sb.toString();
	}
}
