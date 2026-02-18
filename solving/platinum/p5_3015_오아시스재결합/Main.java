package platinum.p5_3015_오아시스재결합;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/platinum/p5_3015_오아시스재결합/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		int count = 0;
		
		int num;
		for(int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());
			
			if(stack.isEmpty()) stack.push(num);
			
			if(num < stack.peekLast()) {
				count++;
				stack.push(num);
				continue;
			}
			
			while(!stack.isEmpty() && num >= stack.peekLast()) {
				count++;
				stack.pop();
			}
			
			stack.push(num);
		}
		
		System.out.println(count);
		br.close();
	}
}
