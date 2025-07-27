package 베스트앨범;

import java.util.*;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) {
		String[] strs = { "classic", "pop", "classic", "classic", "pop" };
		int[] ints = { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(solution(strs, ints)));
	}

	public static int[] solution(String[] genres, int[] plays) {
		ArrayList<Integer> answer = new ArrayList<>();

		HashMap<String, ArrayList<int[]>> category = new HashMap<>(); // value : [idx, play[idx]]
		HashMap<String, Integer> totalPlays = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {
			if (!totalPlays.containsKey(genres[i])) {
				totalPlays.put(genres[i], 0);
				category.put(genres[i], new ArrayList<>());
			}

			totalPlays.put(genres[i], totalPlays.get(genres[i]) + plays[i]);
			category.get(genres[i]).add(new int[] { i, plays[i] });
		}

		Stream<Map.Entry<String, Integer>> sortedGenre = totalPlays.entrySet().stream()
				.sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()));

		sortedGenre.forEach(entry -> {
			Stream<int[]> sortedSongs = category.get(entry.getKey()).stream()
					.sorted((a, b) -> Integer.compare(b[1], a[1])).limit(2);
			sortedSongs.forEach(song -> answer.add(song[0]));
		});

		return answer.stream().mapToInt(Integer::intValue).toArray();
	}
}
