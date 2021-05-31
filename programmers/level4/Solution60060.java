package level4;

import java.util.*;

//가사 검색
class Solution60060 {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		TrieNode[] trie = new TrieNode[10001];

		for (int i = 0; i < words.length; i++) {
			int len = words[i].length();
			if (trie[len] == null)
				trie[len] = new TrieNode();
			trie[len].insert(words[i]);
		}

		for (int i = 0; i < queries.length; i++) {
			int len = queries[i].length();
			if (trie[len] == null)
				answer[i] = 0;
			else
				answer[i] = trie[len].getCount(queries[i]);
		}

		return answer;
	}

	static class TrieNode {
		Node front, back;

		public TrieNode() {
			this.front = new Node();
			this.back = new Node();
		}

		public void insert(String word) {
			insertFront(word);
			insertBack(word);
		}

		// 새로운 Node(Map = null, count = 0)
		// Node의 count 1 증가 (Map = null, count = 1)
		// f라는 key가 없으면 (f, new Node())를 Map에 넣음
		// 즉 Node(Map = [f, new Node()], count = 1)
		// 현재 node는 Map안에 있는 새로운 Node()라고 생각 즉, 노드안에 노드

		private void insertFront(String word) {
			Node node = front;
			for (int i = 0; i < word.length(); i++) {
				node.count++; // 갯수 증가시킴. f, r, o, d, o ...
				node = node.children.computeIfAbsent(word.charAt(i), key -> new Node());
			}
		}

		private void insertBack(String word) {
			Node node = back;
			for (int i = word.length() - 1; i >= 0; i--) {
				node.count++;
				node = node.children.computeIfAbsent(word.charAt(i), key -> new Node());
			}
		}

		public int getCount(String query) {
			if (query.charAt(0) == '?')
				return getCountFromBack(query);
			else
				return getCountFromFront(query);
		}

		private int getCountFromFront(String query) {
			Node node = front;
			for (int i = 0; i < query.length(); i++) {
				if (query.charAt(i) == '?')
					break;
				if (!node.children.containsKey(query.charAt(i)))
					return 0;
				node = node.children.get(query.charAt(i));
			}
			return node.count;
		}

		private int getCountFromBack(String query) {
			Node node = back;
			for (int i = query.length() - 1; i >= 0; i--) {
				if (query.charAt(i) == '?')
					break;
				if (!node.children.containsKey(query.charAt(i)))
					return 0;
				node = node.children.get(query.charAt(i));
			}
			return node.count;
		}
	}

	static class Node {
		Map<Character, Node> children;
		int count;

		public Node() {
			this.children = new HashMap<Character, Node>();
			this.count = 0;
		}
	}
}
