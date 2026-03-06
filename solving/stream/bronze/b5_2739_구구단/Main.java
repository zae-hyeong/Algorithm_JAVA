package stream.bronze.b5_2739_구구단;

import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/stream/bronze/b5_2739_구구단/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		final int N = Integer.parseInt(br.readLine());
		
		StringBuffer sb = new StringBuffer();
		IntStream.rangeClosed(1, 9).forEach(j -> {
			sb.append(N).append(" * ").append(j).append(" = ").append(N * j).append("\n");
		});

		System.out.println(sb.toString());
		br.close();
	}
}
