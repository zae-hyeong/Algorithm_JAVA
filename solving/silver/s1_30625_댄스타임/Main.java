package silver.s1_30625_댄스타임;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static boolean[] isFront;
	static final int DIV = 1000000007;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./solving/silver/s1_30625_댄스타임/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isFront = new boolean[N];
		
		int dance, total = 1;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dance = Integer.parseInt(st.nextToken());
			isFront[i] = Integer.parseInt(st.nextToken()) == 0;
			
			if(!isFront[i]) total = (total * (M - 1)) % DIV;
		}
		
		int result = total; // 모두 맞은 경우
		
		for(int i = 0; i < N; i++) { // 틀린 라운드
			if(isFront[i]) result = result + ((total * (M - 1)) % DIV);
			else if(total % (M - 1) == 0) result = result + ((total / (M - 1)) % DIV);
			else result += total;
			
			result = result % DIV;
		}

		System.out.println(result);
		br.close();
	}
}
