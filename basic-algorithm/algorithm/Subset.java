package algorithm;

import java.io.*;
import java.util.*;

public class Subset {
	static int N = 4, C = 0;
	static int[] a = { 1, 2, 3, 4 };

	static void subs(int cnt, String str) {
		if (cnt == N) {
			System.out.println(str);
			C++;
			return;
		}

		subs(cnt + 1, str + a[cnt]);
		subs(cnt + 1, str + ".");
	}

	public static void main(String[] args) throws Exception {
		C = 0;
		subs(0, "");
		System.out.println(C);
	}
}
