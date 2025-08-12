package silver.s2_11279;

import java.io.*;
import java.util.*;

class MaxHeap {
	private ArrayList<Integer> arr;

	MaxHeap() {
		this.arr = new ArrayList<>();
	}

	void add(int v) {
		arr.add(v);
		bubbleUp();
		return;
	}

	int poll() {
		if (this.arr.isEmpty())
			return 0;

		swap(0, arr.size() - 1);
		int returnVal = arr.get(arr.size() - 1);
		arr.remove(arr.size() - 1);

		bubbleDown();

		return returnVal;
	}

	private void bubbleUp() {
		int child = arr.size() - 1;
		int parent = (child - 1) / 2;

		while (arr.get(parent) < arr.get(child)) {
			swap(parent, child);

			child = parent;
			parent = (child - 1) / 2;
		}
	}

	private void bubbleDown() {

		int parent = 0, maxIdx = 0;
		int leftChild = parent * 2 + 1;
		int rightChild = parent * 2 + 2;

		while (true) {
			maxIdx = parent;

			if (leftChild < arr.size() && this.arr.get(maxIdx) < this.arr.get(leftChild))
				maxIdx = leftChild;

			if (rightChild < arr.size() && this.arr.get(maxIdx) < this.arr.get(rightChild))
				maxIdx = rightChild;

			if (parent == maxIdx) break;

			swap(maxIdx, parent);
			parent = maxIdx;
			leftChild = parent * 2 + 1;
			rightChild = parent * 2 + 2;
		}
	}

	private void swap(int a, int b) {
		int valA = this.arr.get(a);
		int valB = this.arr.get(b);
		this.arr.set(a, valB);
		this.arr.set(b, valA);
	}
}

public class Main {
	static int N;
	static MaxHeap mh;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./baekjoon/silver/s2_11279/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		mh = new MaxHeap();

		int num;
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());

			if (num == 0)
				sb.append(mh.poll()).append("\n");
			else
				mh.add(num);
		}

		System.out.println(sb.toString());
		br.close();
	}
}
