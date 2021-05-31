package kakao;

import java.util.*;

class Solution42892 {
	static Queue<Integer> pr, po;
	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];
		pr = new LinkedList<>();
		po = new LinkedList<>();
		
		int[][] new_nodeinfo = new int[nodeinfo.length][3];
		for (int i = 0; i < nodeinfo.length; i++) {
			new_nodeinfo[i][0] = nodeinfo[i][0];
			new_nodeinfo[i][1] = nodeinfo[i][1];
			new_nodeinfo[i][2] = i + 1;
		}

		// y축 기준으로 내림차순 정렬 한다.
		Arrays.sort(new_nodeinfo, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1])
					return o1[0] - o2[0];
				return o2[1] - o1[1];
			}
		});

		TreeNode tnode = new TreeNode(new Node(new_nodeinfo[0][0], new_nodeinfo[0][1], new_nodeinfo[0][2]));

		for (int i = 1; i < nodeinfo.length; i++) {
			tnode.add(new Node(new_nodeinfo[i][0], new_nodeinfo[i][1], new_nodeinfo[i][2]));
		}
		
		tnode.preorder(tnode);
		tnode.postorder(tnode);
		
		for (int i = 0; i < new_nodeinfo.length; i++) {
			answer[0][i] = pr.poll();
		}
		
		for (int i = 0; i < new_nodeinfo.length; i++) {
			answer[1][i] = po.poll();
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

		public void add(Node node) {
			TreeNode children = new TreeNode(node);

			if (this.left == null && this.root.x > node.x) {
				this.left = children;
				return;
			} else if (this.right == null && this.root.x < node.x) {
				this.right = children;
				return;
			}
			
			TreeNode parent = this.root.x > node.x ? this.left : this.right;

			while (true) {
				if (parent.root.x > node.x) {
					if(parent.left == null) {
						parent.left = children;
						break;
					}
					
					parent = parent.left;
				} else {
					if (parent.right == null) {
						parent.right = children;
						break;
					}
					
					parent = parent.right;
				}
			}
		}
		
		public void preorder(TreeNode tnode) {
			//root -> left -> right
			pr.add(tnode.root.number);
			
			if(tnode.left != null) preorder(tnode.left);
			if(tnode.right != null) preorder(tnode.right);
		}
		
		public void postorder(TreeNode tnode) {
			//left -> right -> root
			if(tnode.left != null) postorder(tnode.left);
			if(tnode.right != null) postorder(tnode.right);
			
			po.add(tnode.root.number);
		}
	}

	static class Node {
		int x, y, number;

		public Node(int x, int y, int number) {
			this.x = x;
			this.y = y;
			this.number = number;
		}
	}

	public static void main(String[] args) {
		Solution42892 sol = new Solution42892();
		System.out.println(sol.solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 },
				{ 8, 6 }, { 7, 2 }, { 2, 2 } }

		));
	}
}
