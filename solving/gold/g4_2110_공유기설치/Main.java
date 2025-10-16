package gold.g4_2110_공유기설치;

import java.util.*;
import java.io.*;

public class Main {
	static int N, C;
	static int[] arr;
	static LinkedList<Integer> distList, sumList;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_2110_공유기설치/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		distList = new LinkedList<>();
		sumList = new LinkedList<>();
		
		arr[0] = Integer.parseInt(br.readLine());
		for (int i = 1; i < N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		br.close();
		
		if(C == 2) {
			System.out.println(arr[arr.length - 1] - arr[0]);
			return;
		}
		
		for (int i = 1; i < N; i++) {
			distList.add(arr[i] - arr[i - 1]); // distList[i] = arr[i]와 arr[i+1]의 거리
		}
		
		int minSum = Integer.MAX_VALUE, tmp;
		for (int i = 0; i < N - 2; i++) {
			tmp = distList.get(i) + distList.get(i + 1);
			sumList.add(tmp); // sumList[i] = distList[i] + distList[i+1] (두 거리의 합)
			minSum = Math.min(minSum, tmp);
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(distList.toString());
		System.out.println(sumList.toString());
		
		while(distList.size() < C -1)

		System.out.println();
		
	}
}
