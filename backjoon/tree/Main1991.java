package tree;

import java.util.*;
import java.io.*;

public class Main1991 {
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		TreeNode tnode = new TreeNode();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			char top = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			tnode.add(tnode.root, top, left, right);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(tnode.preOrder(tnode.root, new StringBuilder())).append("\n");
		sb.append(tnode.inOrder(tnode.root, new StringBuilder())).append("\n");
		sb.append(tnode.postOrder(tnode.root, new StringBuilder()));
		
		System.out.println(sb.toString());
	}
	
	public static class TreeNode {
		Node root;
		
		public TreeNode() {
			this.root = new Node('A');
		}
		
		public void add(Node node, char top, char left, char right) {
			if(node.data == top) {
				if(node.left == null && left != '.') {
					node.left = new Node(left);
				}
				
				if(node.right == null && right != '.') {
					node.right = new Node(right);
				}
			} else {
				search(node, top, left, right);
			}
		}
		
		public void search(Node node, char top, char left, char right) {
			if(node.left != null && node.left.data == top) {
				node = node.left;
				node.left = new Node(left);
				node.right = new Node(right);
				return;
			}else if(node.right != null && node.right.data == top) {
				node = node.right;
				node.left = new Node(left);
				node.right = new Node(right);
				return;
			}
			
			if(node.left != null) {
				search(node.left, top, left, right);
			}
			
			if(node.right != null) {
				search(node.right, top, left, right);
			}
		}
		
		public StringBuilder preOrder(Node node, StringBuilder answer) {
			// 루트 -> 왼쪽 -> 오른쪽
			if(node.data != '.') answer.append(node.data);
			if(node.left != null) preOrder(node.left, answer);
			if(node.right != null) preOrder(node.right, answer);
			
			return answer;
		}
		
		public StringBuilder inOrder(Node node, StringBuilder answer) {
			// 왼쪽 -> 루트 -> 오른쪽
			if(node.left != null) inOrder(node.left, answer);
			if(node.data != '.') answer.append(node.data);
			if(node.right != null) inOrder(node.right, answer);
			
			return answer;
		}
		
		public StringBuilder postOrder(Node node, StringBuilder answer) {
			// 왼쪽 -> 오른쪽 -> 루트
			if(node.left != null) postOrder(node.left, answer);
			if(node.right != null) postOrder(node.right, answer);
			if(node.data != '.') answer.append(node.data);
			
			return answer;
		}

		@Override
		public String toString() {
			return "TreeNode [root=" + root + "]";
		}
		
	}
	
	public static class Node {
		private char data;
		private Node left, right;
		
		public Node(char data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
		}

	}
}
