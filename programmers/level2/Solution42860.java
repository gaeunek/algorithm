package level2;

//조이스틱
class Solution42860 {
	public int solution(String name) {
		int answer = 0;
		
		int len = name.length();
		int min_move = len-1;
		
		for (int i = 0; i < len; i++) {
			if(name.charAt(i) != 'A') {
				answer += Math.min(name.charAt(i) - 'A', ('Z' - name.charAt(i)) + 1);
				
				int next = i + 1;
				
				while(next < len-1 && name.charAt(next) == 'A') next++;
				
				min_move = Math.min(min_move, i * 2 + len - next);
			}
		}
		
		return answer + min_move;
	}

	public static void main(String[] args) {
		Solution42860 sol = new Solution42860();
//		System.out.println(sol.solution("JEROEN")); // 56
//		System.out.println(sol.solution("JAN")); // 23
//		System.out.println(sol.solution("BBBBAAAABA")); // 13
		System.out.println(sol.solution("BBBAAAA"));
//		System.out.println(sol.solution("ABABAAAAABA"));
	}
}
