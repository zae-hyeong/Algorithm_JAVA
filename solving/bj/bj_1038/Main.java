package bj.bj_1038;

import java.util.*;
import java.io.*;

public class Main {
	static int N;

	static boolean isAvaliable(int n) {
		int a = 1, b;

		while (a > 0) {
			b = n % 10;
			n = n / 10;
			if (n == 0) return true;
			
			a = n % 10;
			
			if (a <= b) return false;
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_1038/input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		sc.close();

		int cnt = 0;
		int num = 0;

		while (true) {
			if(!isAvaliable(num)) {
				num++;
				continue;
			}
			
			if(num < 0) {
				System.out.println(-1);
				return;
			}
			
			if (cnt == N) {
				System.out.println(num);
				return;
			}
			
			num++;
			cnt++;
		}
	}
}
