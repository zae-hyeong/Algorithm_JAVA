package gold.g2_10775;

import java.io.*;

public class Main {
	static int G, P;
	static int[] nearGate;
	static int count;

	static int find(int n) {
		if (nearGate[n] == n) {
			nearGate[n] = n - 1;
			return n;
		}

		return nearGate[n] = find(nearGate[n]);
	}

	static boolean push(int n) {
		if (find(n) == 0) return false;

		count++;
		return true;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/gold/g2_10775/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		nearGate = new int[G + 1];
		for (int i = 1; i <= G; i++)
			nearGate[i] = i;

		count = 0;
		for (int i = 0; i < P; i++) {
//			System.out.println(Arrays.toString(nearGate));
			if (!push(Integer.parseInt(br.readLine()))) break;
		}
		
		System.out.println(count);
		br.close();
	}
}
