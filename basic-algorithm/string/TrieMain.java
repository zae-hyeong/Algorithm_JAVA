package string;

import java.util.*;
import java.io.*;

class Node {
	Character c;
	boolean isEnd;
	HashMap<Character, Node> next;

	public Node() {
		this.c = null;
		this.isEnd = false;
		this.next = new HashMap<>();
	}

	public Node(char c) {
		this.c = c;
		this.isEnd = false;
		this.next = new HashMap<>();
	}
}

class Trie {
	Node root;

	Trie() {
		this.root = new Node();
	}

	void clear() {
		this.root = new Node();
	}

	boolean add(String str) {
		boolean flag = true;
		Node cur = this.root;

		for (int i = 0; i < str.length(); i++) {
			if (cur.next.containsKey(str.charAt(i))) {
				cur = cur.next.get(str.charAt(i));

				if (cur.isEnd) {
					flag = false;
				}
			} else {
				Node newNode = new Node(str.charAt(i));
				if (i == str.length() - 1)
					newNode.isEnd = true;

				cur.next.put(str.charAt(i), newNode);
				cur = newNode;
			}
		}

//		System.out.println(this.root.next.keySet().toString());

		return flag;
	}
}

public class TrieMain {
	static Trie trie;
	static String[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_5052/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		int N = 0;
		trie = new Trie();

		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new String[N];
			trie.clear();

			boolean flag = true;

			for (int i = 0; i < N; i++) {
				flag = true;
				arr[i] = br.readLine();
			}
			
			Arrays.sort(arr);

			for (int i = 0; i < N; i++) {
				if (!trie.add(arr[i])) {
					flag = false;
					break;
				}
			}

			sb.append(flag ? "YES" : "NO").append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
