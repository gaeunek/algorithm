package level3;

import java.util.*;
//길 찾기 게임
class Solution42892 {
	static List<Integer> po, ps;
	public int[][] solution(int[][] nodeinfo) {
		Node[] nodes = new Node[nodeinfo.length];
		po = new ArrayList<>();
		ps = new ArrayList<>();
		
		for (int i = 0; i < nodes.length; i++) {
			int[] tmp = nodeinfo[i];
			nodes[i] = new Node(tmp[0], tmp[1], i + 1);
		}
	
		Arrays.sort(nodes);
		
		TreeNode treeNode = new TreeNode(nodes[0]);
		for (int i = 1; i < nodes.length; i++) {
			treeNode.make(treeNode, nodes[i]);
		}
		
		/*전위 순회*/
		treeNode.preorder(treeNode);
		/*후위 순회*/
		treeNode.postorder(treeNode);
		
		int[][] answer = new int[2][po.size()];
		for (int i = 0; i < po.size(); i++) {
			answer[0][i] = po.get(i);
			answer[1][i] = ps.get(i);
		}
		
		return answer;
	}
	
	static class TreeNode {
		Node root;
		TreeNode left, right;
		
		public TreeNode(Node node) {
			this.root = node;
			this.left = null;
			this.right = null;
		}
		
		public void make(TreeNode top, Node node) {
			TreeNode tnode = new TreeNode(node);
			
			if(top.root.x > node.x) {
				if(top.left == null) makeLeftChild(top, tnode);
				else make(top.left, node);
			} else {
				if(top.right == null) makeRightChild(top, tnode);
				else make(top.right, node);
			}
		}
		
		public void makeLeftChild(TreeNode top, TreeNode tnode) {
			top.left = tnode;
		}
		
		public void makeRightChild(TreeNode top, TreeNode tnode) {
			top.right = tnode;
		}
		
		public void preorder(TreeNode tnode){
			po.add(tnode.root.num);
			
			if(tnode.left != null) preorder(tnode.left);
			if(tnode.right != null) preorder(tnode.right);
		}
		
		public void postorder(TreeNode tnode) {
			if(tnode.left != null) postorder(tnode.left);
			if(tnode.right != null) postorder(tnode.right);
			
			ps.add(tnode.root.num);
		}
		
		@Override
		public String toString() {
			return "TreeNode [root=" + root + ", left=" + left + ", right=" + right + "]";
		}
	}
	

	static class Node implements Comparable<Node> {
		int x, y, num;

		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			if(this.y == o.y) return this.x - o.x;
			return o.y - this.y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", num=" + num + "]";
		}
	}

	public static void main(String[] args) {
		Solution42892 sol = new Solution42892();
		sol.solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } });
	}
}
