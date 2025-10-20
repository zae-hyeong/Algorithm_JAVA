package gold.g5_20157_화살을쏘자;

import java.util.*;
import java.io.*;

public class Main {
	static int N;

	static int getGCD(int a, int b) {
		if (a % b == 0) {
			return b;
		}

		return getGCD(b, a % b);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g5_20157_화살을쏘자/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		HashMap<String, Integer> map = new HashMap<>();

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(x == 0 || y == 0) {
				if (x == 0) y = y > 0 ? 1 : -1;
				if (y == 0) x = x > 0 ? 1 : -1;
			} else {
				int GCD = getGCD(Math.max(x, y), Math.min(x, y));
				if (GCD < 0) GCD *= -1;
				x /= GCD;
				y /= GCD;
			}
			
			String keyStr = String.format("%d,%d", x, y);
			if(map.containsKey(keyStr)) {
				map.put(keyStr, map.get(keyStr) + 1);
			} else {
				map.put(keyStr, 1);
			}
		}
		
		int maxVal = 0;
		for(String key: map.keySet()) {
			maxVal = Math.max(maxVal, map.get(key));
		}

		System.out.println(maxVal);
		br.close();
	}
}
