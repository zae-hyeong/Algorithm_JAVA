package gold.g4_13701_중복제거;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_13701_중복제거/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Long> set = new HashSet<>();
		
		StringBuilder sb = new StringBuilder();
		
		long tmp;
		while(st.hasMoreTokens()) {
			tmp = Long.parseLong(st.nextToken());
			if(!set.contains(tmp)) {
				sb.append(tmp).append(" ");
				set.add(tmp);
			}
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
}
