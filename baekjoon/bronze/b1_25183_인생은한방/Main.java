package bronze.b1_25183_인생은한방;

import java.io.*;

public class Main {
	static int N;
	static String str;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("solving/bronze/b1_25183_인생은한방/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		br.close();

		int prevNum = str.charAt(0);
		int charNum, count = 1;

		for (int i = 1; i < N; i++) {
			charNum = str.charAt(i);

			if (Math.abs(charNum - prevNum) == 1) {
				count++;
				if (count >= 5) {
					System.out.println("YES");
					return;
				}
			} else {
				count = 1;
			}

			prevNum = charNum;
		}

		System.out.println("NO");
		return;
	}
}
