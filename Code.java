package edu.amrita.cb.cen.ds.sads.Final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap; 
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

class Node {
	char data;
	int count;
	Node left, right;
}

class BST {
	static int i = 0, j;

	public Node createNewNode(char k) {
		Node a = new Node();
		a.data = k;
		a.count = 1;
		a.left = null;
		a.right = null;
		return a;
	}

	public boolean search(Node root, char key) {
		Node temp = root;
		if (temp != null) {
			if (temp.data == key) {
				root.count++;
				return true;
			} // Since P<a
			if (temp.data < key) {
				return search(temp.right, key);
			} else {
				return search(temp.left, key);
			}
		} else {
			return false;
		}

	}

	public Node insert(Node node, char key) {
		if (node == null) {
			return createNewNode(key);
		}

		boolean searchValue = search(node, key);
		if (searchValue) {
			return node;
		}
		// A<P
		if (key < node.data) {
			node.left = insert(node.left, key);
		} // V>P
		else if (key > node.data) {
			node.right = insert(node.right, key);
		}
		return node;
	}

	
	void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + "");
			System.out.print(root.count + " ");
			inOrder(root.right);

		}
	}
}

class HuffmanNode {

	int data;
	char c;
	HuffmanNode left;
	HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
	public int compare(final HuffmanNode x, final HuffmanNode y) {

		return x.data - y.data;
	}
}

class Huffman {

	void printCode(HuffmanNode root, String s) {

		if (root.left == null && root.right == null ) {

			// c is the character in the node
			System.out.println(root.c + ":" + s);
			return;
		}

		// if we go to left then add "0" to the code.
		// if we go to the right add"1" to the code.
		// recursive calls for left and
		// right sub-tree of the generated tree.
		printCode(root.left, s + "0");
		printCode(root.right, s + "1");
	}
}

public class FinalTermProjectCode {
	static String d = "";

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\java\\text.txt");
		//File file = new File("D:\\java\\alice29.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st, s;
		s = null;
		while ((st = br.readLine()) != null) {
			s = st;
			System.out.println(s);
			// s=s.trim().replaceAll("[^a-zA-Z0-9]", "");
			d = d + s;
		}
		System.out.println("");
		System.out.println(d);
		d = d.trim().replaceAll("[^a-zA-Z0-9]", "");//excluding the characters other than them
		System.out.println("");
		System.out.println(d);
		System.out.println("");
		br.close();// to avoid the small idication given by bufferReader
		char ch[] = d.toCharArray();//used to convert a String to CharArray
		BST ab = new BST();//Creating an object of BST
		Node root = null;// creating node type variable and making it null
		for (char c : ch) {
			root = ab.insert(root, c);
		}
		System.out.println("BST In order Traversel");
		ab.inOrder(root);
		
		System.out.println("");
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : ch) {
			if (map.containsKey(c)) {//check wether the same char is present in the map
				map.put(c, map.get(c) + 1);
				//System.out.println(map);

			} else {
				map.put(c, 1);
				//System.out.println(map);
			}
		}
		System.out.println("\nMap");
		System.out.println(map+"\n");
		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,
				b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
														//Here checking which value is greater based on that we doing sorting
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			maxHeap.add(entry);
			//System.out.println(maxHeap);
		}
		System.out.println("\nFinal MaxHeap: ");
		System.out.println(maxHeap+"\n");
		char[] charArr = new char[maxHeap.size()];//for char
		int[] intArr = new int[maxHeap.size()];//for integer val
		PriorityQueue<Map.Entry<Character, Integer>> temp = maxHeap;//
		Iterator<Entry<Character, Integer>> it = temp.iterator();//By this i'm storing values in char array and integer
		int as = 0;
		while (it.hasNext()) {
			Map.Entry<Character, Integer> me = it.next();
			charArr[as] = me.getKey();
			intArr[as] = me.getValue();
			as++;
		}
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(maxHeap.size(), new MyComparator());
		for (int i = 0; i < maxHeap.size(); i++) {
			HuffmanNode hn = new HuffmanNode();

			hn.c = charArr[i];
			hn.data = intArr[i];
			hn.left = hn.right = null;
			pq.add(hn);
		}

		HuffmanNode Hfroot = null;

		while (pq.size() > 1) {

			HuffmanNode x = pq.peek();
			pq.poll();
			HuffmanNode y = pq.peek();
			pq.poll();

			HuffmanNode f = new HuffmanNode();
			f.data = x.data + y.data;
			f.c = '-';

			f.left = x;
			f.right = y;

			Hfroot = f;

			pq.add(f);
		}
		Huffman hf = new Huffman();
		hf.printCode(Hfroot, "");
	}

}

