package gold.g5_1484_다이어트;

import java.util.*;
import java.io.*;

// 예시) input : 15
// output : 4, 8 : 64-49, 16-1
public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g5_1484/input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		StringBuilder sb = new StringBuilder();

		int front = 1, rear = 2, count = 0;
		double num;

		while (front < rear) {
//			System.out.println(front + " : " + rear);
			num = Math.pow(rear, 2) - Math.pow(front, 2);
			if (num < N)
				rear++;
			else if (num > N)
				front++;
			else {
				sb.append(rear).append("\n");
				count++;
				front++;
			}
		}

		System.out.println(count == 0 ? -1 : sb.toString());
		sc.close();
	}
}
