package platinum.p5_1786;

import java.util.*;
import java.io.*;

public class Main_Rabin_Karp {

	static String str, targetStr;
	static int[] p;

	static int makeHash(String str, int n) {
		int hash = 0;
		for (int i = 0; i < n; i++) {
			hash = hash << 1;
			hash += str.charAt(i);
		}

		return hash;
	}

	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	static void KMP(String dest, String find) {
		int fLength = find.length();
		int findHash = makeHash(find, fLength);

		int dLength = dest.length();
		int destHash = makeHash(dest, fLength);

		int loop = dLength - fLength + 1;

		for (int i = 0; i < loop; i++) {
			if (findHash == destHash) {
				count++;
				sb.append(i + 1).append(" ");
			}

//				System.out.println("i : " + i + " / destHash : " + destHash + " / findHash : " + findHash);
			destHash -= (Math.pow(2, fLength - 1) * dest.charAt(i));
			destHash = destHash << 1;
			if (i + fLength < dLength)
				destHash += dest.charAt(i + fLength);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./baekjoon/platinum/p5_1786/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		targetStr = br.readLine();

//			System.out.println(Arrays.toString(table));
		KMP(str, targetStr);

		System.out.println(count);
		System.out.println(sb);
		br.close();
	}
}
