package kakao;

// 6시 34분
import java.util.*;

class Solution60060_2 {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		Trie[] trie = new Trie[10001];
		for (String word : words) {
			if (trie[word.length()] == null) {
				trie[word.length()] = new Trie();
			}

			trie[word.length()].insert(word);
		}

		for (int i = 0; i < answer.length; i++) {
			if (trie[queries[i].length()] == null)
				continue;
			
			answer[i] = trie[queries[i].length()].get(queries[i]);
		}

		return answer;
	}

	static class Trie {
		Node front, back;

		public Trie() {
			this.front = new Node();
			this.back = new Node();
		}

		public void insert(String keyword) {
			// front insert
			Node now = this.front;
			for (int i = 0; i < keyword.length(); i++) {
				char c = keyword.charAt(i);
				now.count++;
				now = now.children.computeIfAbsent(c, key -> new Node());
			}

			now = this.back;
			for (int i = keyword.length() - 1; i >= 0; i--) {
				char c = keyword.charAt(i);
				now.count++;
				now = now.children.computeIfAbsent(c, key -> new Node());
			}
		}

		public int get(String query) {
			// front로 찾기
			if (query.charAt(0) == '?' && query.charAt(query.length() - 1) == '?')
				return this.front.count;

			if (query.charAt(query.length() - 1) == '?') {
				Node now = this.front;

				for (int i = 0; i < query.length(); i++) {
					char c = query.charAt(i);

					if (c == '?') {
						return now.count;
					}

					if (now.children.containsKey(c))
						now = now.children.get(c);
					else
						break;
				}
			} else {
				Node now = this.back;

				for (int i = query.length() - 1; i >= 0; i--) {
					char c = query.charAt(i);

					if (c == '?')
						return now.count;

					if (now.children.containsKey(c))
						now = now.children.get(c);
					else
						break;
				}
			}

			return 0;
		}

		@Override
		public String toString() {
			return "Trie [front=" + front + ", back=" + back + "]";
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
		Solution60060_2 sol = new Solution60060_2();
//		System.out.println(sol.solution(new String[] { "frodo", "front", "frost", "frozen", "frame", "kakao" },
//				new String[] { "fro??", "????o", "fr???", "fro???", "pro?" }));
		System.out.println(sol.solution(new String[] { "frodo" }, new String[] { "?????" }));
	}
}
