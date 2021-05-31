package level4;

import java.util.*;

// 다시 풀어본 가사 검색
class Solution60060_2 {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		// 단어 길이로 1차 필터링 해주기 위해 10001 크기의 배열을 만든다.
		TreeNode[] treeNode = new TreeNode[10001];

		for (int i = 0; i < words.length; i++) {
			int len = words[i].length();

			if (treeNode[len] == null) {
				treeNode[len] = new TreeNode();
			}

			treeNode[len].insert(words[i]);
		}
		
		for (int i = 0; i < queries.length; i++) {
			int len = queries[i].length();
			String query = queries[i];

			if (treeNode[len] == null)
				continue;
			
			answer[i] = treeNode[len].get(query);
		}

		return answer;
	}

	static class TreeNode {
		Node front, back;

		public TreeNode() {
			front = new Node();
			back = new Node();
		}

		public void insert(String word) {
			insertFront(word.toCharArray());
			insertBack(word.toCharArray());
		}

		public void insertFront(char[] c) {
			Node nowNode = front;

			for (int i = 0; i < c.length; i++) {
				nowNode.count++;
				nowNode = nowNode.children.computeIfAbsent(c[i], key -> new Node());
			}
		}

		public void insertBack(char[] c) {
			Node nowNode = back;

			for (int i = c.length - 1; i >= 0; i--) {
				nowNode.count++;
				nowNode = nowNode.children.computeIfAbsent(c[i], key -> new Node());
			}
		}

		public int get(String query) {
			char[] c = query.toCharArray();

			if (c[0] == '?') {
				return getBack(c);
			} else {
				return getFront(c);
			}
		}

		public int getFront(char[] c) {
			Node nowNode = front;

			for (int i = 0; i < c.length; i++) {
				if(nowNode == null) return 0;
				
				if (c[i] == '?') {
					return nowNode.count;
				}

				nowNode = nowNode.children.get(c[i]);
			}

			return nowNode.count;
		}

		public int getBack(char[] c) {
			Node nowNode = back;

			for (int i = c.length - 1; i >= 0; i--) {
				if(nowNode == null) return 0;
				
				if (c[i] == '?') {
					return nowNode.count;
				}

				nowNode = nowNode.children.get(c[i]);
			}

			return nowNode.count;
		}
	}

	static class Node {
		Map<Character, Node> children;
		int count;

		public Node() {
			children = new HashMap<>();
			count = 0;
		}

		@Override
		public String toString() {
			return "Node [children=" + children + ", count=" + count + "]";
		}
	}

	public static void main(String[] args) {
		Solution60060_2 sol = new Solution60060_2();
		System.out.println(
				Arrays.toString(sol.solution(new String[] { "frodo", "front", "frost", "frozen", "frame", "kakao" },
						new String[] { "fro??", "????o", "fr???", "fro???", "pro?" })));
	}
}
