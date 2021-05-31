package kakao;

// 5:30
import java.util.*;

class Solution17685 {
	public int solution(String[] words) {
		int answer = 0;

		TreeNode tnode = new TreeNode();
		for (int i = 0; i < words.length; i++) {
			tnode.add(words[i]);
		}
		
		for (int i = 0; i < words.length; i++) {
			answer += tnode.get(words[i]);
		}
		
		return answer;
	}

	static class TreeNode {
		Node node;

		public TreeNode() {
			this.node = new Node();
		}

		public void add(String keyword) {
			Node now = this.node;

			for (int i = 0; i < keyword.length(); i++) {
				char c = keyword.charAt(i);
				now = now.children.computeIfAbsent(c, key -> new Node());
				now.count++;
			}
		}

		public int get(String keyword) {
			Node now = this.node;
			int depth = 0;

			for (int i = 0; i < keyword.length(); i++) {
				char c = keyword.charAt(i);
				now = now.children.get(c);
				
				depth++;
				if(now.count == 1) {
					return depth;
				}
			}

			return depth;
		}

		@Override
		public String toString() {
			return "TreeNode [node=" + node + "]";
		}
	}

	static class Node {
		int count;
		Map<Character, Node> children;

		public Node() {
			this.count = 0;
			this.children = new HashMap<>();
		}

		@Override
		public String toString() {
			return "Node [count=" + count + ", children=" + children + "]";
		}
		
	}

	public static void main(String[] args) {
		Solution17685 sol = new Solution17685();
		System.out.println(sol.solution(new String[] { "go", "gone", "guild" }));
	}
}
