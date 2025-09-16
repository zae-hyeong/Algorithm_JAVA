package platinum.p4_5670;

import java.util.*;
import java.io.*;

class Node {
	boolean isEnd;
	HashMap<Character, Node> child;

	Node() {
		this.isEnd = false;
		this.child = new HashMap<Character, Node>();
	}
	
	Node getChildNode(char c) {
		return this.child.get(c);
	}
}

class Trie {
	Node root;

	Trie() {
		this.root = new Node();
	}

	void clear() {
		this.root.child.clear();
	}

	void pushString(char[] charArr) {
		Node cur = this.root;
		for (int i = 0; i < charArr.length; i++) {
			if (cur.child.containsKey(charArr[i])) {
				cur = cur.child.get(charArr[i]);
				continue;
			}

			Node newNode = new Node();
			cur.child.put(charArr[i], newNode);
			cur = newNode;
		}

		cur.isEnd = true;
	}

	int count(char[] charArr) {
		int counter = 1;
		Node cur = this.root.getChildNode(charArr[0]);
		
		for (int i = 1; i < charArr.length; i++) {
			if (cur.child.size() != 1 || cur.isEnd) {
//				System.out.println(charArr[i - 1]);
				counter++;
			}
			
			cur = cur.getChildNode(charArr[i]);
		}
		
		return counter;
	}
}

public class Main {
	static int N;
	static Trie trie;
	static char[][] strs;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_5670/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		trie = new Trie();

		String input;
		char[] charArr;
		while ((input = br.readLine()) != null) {
			N = Integer.parseInt(input);
			strs = new char[N][];
			trie.clear();

			for (int i = 0; i < N; i++) {
				charArr = br.readLine().toCharArray();
				strs[i] = charArr;
				trie.pushString(charArr);
			}

			int sum = 0;
			int num = 0;
			for (int i = 0; i < N; i++) {
				num = trie.count(strs[i]);
//				System.out.println("num : " + num);
				sum += num;
			}
			
			double result = Math.round(((double) sum / N) * 100.0) / 100.0;
			System.out.println(String.format("%.2f", result)) ;
		}

//		StringTokenizer st;
//		Integer.parseInt(st.nextToken());

		System.out.println();
		br.close();
	}
}
