package level4;

import java.util.*;

//[3차]자동완성
//끝까지 비교 뺌 -> 효율성 변화 무
//sort뺌 -> 살짝 빨라졌으나 비슷함
//map이 아닌 배열을 사용하면 확실히 더 빠르다.
class Solution17685 {
	public int solution(String[] words) {
		int answer = 0;

		Tree[] tree = new Tree[26]; // 97

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			int index = word.charAt(0) - 97;

			if (tree[index] == null) {
				tree[index] = new Tree(word);
			}

			tree[index].createTreeNode(word);
		}

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			int index = word.charAt(0) - 97;

			if (tree[index].treeNode.count == 1)
				answer++;
			else
				answer += tree[index].searchTreeNode(word);
		}

		return answer;
	}

	static class Tree {
		TreeNode treeNode;

		public Tree(String word) {
			this.treeNode = new TreeNode(word.charAt(0));
		}

		public void createTreeNode(String word) {
			TreeNode now = this.treeNode;
			for (int i = 1; i <= word.length(); i++) {
				if (i == word.length()) {
					now.count++;
					break;
				}

				char c = word.charAt(i);
				TreeNode newNode = null;

				if (now.map.get(c) == null) { // 부모에 없다
					newNode = new TreeNode(c);
					now.map.put(newNode.top, newNode);
				} else {
					newNode = now.map.get(c);
				}

				now.count++;
				now = newNode;
			}
		}

		public int searchTreeNode(String word) {
			TreeNode now = this.treeNode;
			TreeNode findTreeNode = null;

			for (int i = 1; i < word.length(); i++) {
				findTreeNode = now.map.get(word.charAt(i));

				if (findTreeNode.count == 1) {
					return i + 1;
				} else {
					now = findTreeNode;
				}
			}

			return word.length();
		}

		@Override
		public String toString() {
			return "Tree [treeNode=" + treeNode + "]";
		}
	}

	static class TreeNode {
		char top;
		int count;
		Map<Character, TreeNode> map;

		public TreeNode(char c) {
			this.top = c;
			this.count = 0;
			map = new HashMap<>();
		}

		@Override
		public String toString() {
			return "TreeNode [top=" + top + ", count=" + count + ", map=" + map + "]";
		}
	}

	public static void main(String[] args) {
		Solution17685 sol = new Solution17685();
		System.out.println(sol.solution(new String[] { "go", "gone", "guild" }));
		System.out.println(sol.solution(new String[] { "abc", "def", "ghi", "jklm" }));
		System.out.println(sol.solution(new String[] { "word", "war", "warrior", "world" }));
	}
}
