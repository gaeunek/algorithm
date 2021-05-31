package level3;
//전에 풀었던거랑 비교하면 많이 간결해졌다!
class Solution43163_2 {
	static int answer;
	public int solution(String begin, String target, String[] words) {
		boolean[] check = new boolean[words.length];
		dfs(words, begin, target, 0, check);
		
		return answer;
	}

	static void dfs(String[] words, String begin, String target, int cnt, boolean[] check) {
		if(begin.equals(target)) {
			if(answer == 0) answer = cnt;
			else answer = Math.min(answer, cnt);
			return;
		}
		
		for (int i = 0; i < check.length; i++) {
			if (check[i]) continue;
			
			int diff = 0;
			for (int j = 0; j < words[i].length(); j++) {
				if(words[i].charAt(j) != begin.charAt(j)) diff++;
			}
			
			if(diff == 1) {
				check[i] = true;
				dfs(words, words[i], target, cnt+1, check);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Solution43163_2 sol = new Solution43163_2();
		System.out.println(sol.solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));
	}
}
