package d123.d2_1926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(br.readLine());

		ArrayList<String> arr = new ArrayList<>();
		int n;
		String s = "";

		for (int i = 1; i <= N; i++) {
			n = i;
			s = "";
			while (n > 0) {
				if (n % 10 == 3 || n % 10 == 6 || n % 10 == 9) {
					s += "-";
				}
				n /= 10;
			}
			if (s != "")
				arr.add(s);
			else
				arr.add(Integer.toString(i));
		}

		System.out.println(String.join(" ", arr));
	}
}
