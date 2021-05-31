package kakao;

class Solution60057 {
	public int solution(String s) {
		int answer = Integer.MAX_VALUE;

		int n = s.length();
		
		if(n == 1) return 1; // 범위가 1~1000
		
		StringBuilder sb;
		for (int i = n / 2; i > 0; i--) {
			sb = new StringBuilder();
			String now = s.substring(0, i);
			int count = 1;
			for (int j = i; j < n; j += i) {
				if(j + i > n) {
					sb.append(count > 1 ? count : ""); // 이전 count sb에 저장 후
					sb.append(now);
					sb.append(s.substring(j, s.length())); // 뒤에 나머지 알파벳 붙이고
					break;
				}
				
				String next = s.substring(j, j + i);
				
				if(now.equals(next)) count++;
				else {
					sb.append(count > 1 ? count : "");
					sb.append(now);
					count = 1;
					now = next;
				}
				
				if(j + i == n) {
					sb.append(count > 1 ? count : "");
					sb.append(next);
					break;
				}
			}
//			System.out.println(sb.toString());
			answer = Math.min(sb.toString().length(), answer);
		}

		return answer;
	}
	
	public static void main(String[] args) {
		Solution60057 sol = new Solution60057();
		System.out.println(sol.solution("aabbaccc"));
		System.out.println(sol.solution("ababcdcdababcdcd"));
		System.out.println(sol.solution("aa"));
	}
}
