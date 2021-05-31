package level3;

//단어 변환
class Solution43163 {
	static int min;
	static String[] staticWords;
	static String staticTarget;
	static boolean[] used;

	public int solution(String begin, String target, String[] words) {
		boolean flag = false;
		for (String str : words) {
			if (str.equals(target)) {
				flag = true;
				break;
			}
		}
		if (!flag)
			return 0;

		staticTarget = target;
		staticWords = words;
		used = new boolean[words.length];
		min = Integer.MAX_VALUE;

		dfs(begin, 0);

		return min;
	}

	static void dfs(String begin, int cnt) {

		if (begin.equals(staticTarget)) {
			min = min < cnt ? min : cnt;
			return;
		}
		

		char[] begin_to_char = begin.toCharArray();
		char[] next_to_char = new char[begin.length()];
		
		for (int n = 0; n < staticWords.length; n++) {
			if(!used[n]) {
				next_to_char = staticWords[n].toCharArray();
				
				int wrong = 0;
				for (int i = 0; i < begin_to_char.length; i++) {
					if (begin_to_char[i] != next_to_char[i])
						wrong++;
				}
				
				if (wrong == 1) {
					used[n] = true;
					dfs(staticWords[n], cnt + 1);
					used[n] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution43163 sol = new Solution43163();
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(sol.solution(begin, target, words));
	}
}
