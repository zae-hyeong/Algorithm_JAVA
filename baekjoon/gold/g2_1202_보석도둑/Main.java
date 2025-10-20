package gold.g2_1202_보석도둑;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] bag, nextBag;
	static int[][] jewel;
	
	static int getParent(int idx) {
		if(idx >= K || idx == nextBag[idx])
			return idx;
		
		return nextBag[idx] = getParent(nextBag[idx]);
	}
	
	static int push(int jewelWeight, int jewelValue) { //return : 넣을 가방의 idx
		int idx = Arrays.binarySearch(bag, jewelWeight);
		if(idx < 0) idx = -1 * (idx + 1);
		
		return getParent(idx);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_1202_보석도둑/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jewel = new int[N][2];
		bag = new int[K];
		nextBag = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i][0] = Integer.parseInt(st.nextToken()); // 무게
			jewel[i][1] = Integer.parseInt(st.nextToken()); // 가치
		}

		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
			nextBag[i] = i;
		}

		Arrays.sort(jewel, (o1, o2) -> Integer.compare(o2[1], o1[1])); // 가치 기준 내림차순
		Arrays.sort(bag); // 가방 무게 한도 기준 오름차순
		
		long totalValue = 0; 
		for (int i = 0; i < N; i++) {
			int idx = push(jewel[i][0], jewel[i][0]); 
			if (idx >= K)
				continue;
			
			// 넣은 경우
			totalValue += jewel[i][1];
			nextBag[idx] = idx + 1;
		}
		
		System.out.println(totalValue);
		br.close();
	}
}
