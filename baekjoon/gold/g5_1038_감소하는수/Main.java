package gold.g5_1038_감소하는수;

import java.util.*;

public class Main {
	static int N;
	static ArrayList<Long> list = new ArrayList<>();

	static void comb(long num, int idx) {
		if (idx > 10) return;

		list.add(num);
		
		// 마지막 자릿수보다 작은 수 뒤에 붙여 새로운 수를 만들기
		for (int i = 0; i < num % 10; i++) {
			comb((num * 10) + i, idx + 1);
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/bj/bj_1038/input.txt"));

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();

		if (N > 1022) {
			System.out.println(-1);
			return;
		}

		for (int i = 0; i < 10; i++)
			comb(i, 1);

		list.sort((o1, o2) -> Long.compare(o1, o2));

//		System.out.println(list.toString());
		System.out.println(list.get(N));
	}
}
