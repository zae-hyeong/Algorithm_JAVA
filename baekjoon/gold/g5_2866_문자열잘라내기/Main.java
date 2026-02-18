package gold.g5_2866_문자열잘라내기;

import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g5_2866_문자열잘라내기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] strs = new char[R][C];
		
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i < R; i++) {
			strs[i] = br.readLine().toCharArray();
		}
		
		StringBuffer sb;
		String str;
		
		int start = 1, end = R;
		int mid  = (start + end) / 2;
		boolean flag;
		while(start <= end) {
			flag = false;
			
			for(int i = 0; i < C; i++) {
				sb = new StringBuffer();
				
				for(int j = mid; j < R; j++) sb.append(strs[j][i]);
				
				str = sb.toString();
				
				if(set.contains(str)) {
					flag = true;
					break;
				}
				
				set.add(str);
			}
			
			if(flag) end = mid - 1;
			else start = mid + 1;
			mid = (start + end) / 2;
			
			set.clear();
		}

		System.out.println(mid);
		br.close();
	}
}
