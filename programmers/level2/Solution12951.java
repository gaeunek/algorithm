package level2;

//JadenCase 문자열 만들기
class Solution12951 {
	public String solution(String s) {
		String answer = "";
		s = s.toLowerCase();
		
		int jumpCnt = 0;
		for (int i = 0; i < s.length(); i++) {
			String tmp = String.valueOf(s.charAt(i));
			if(tmp.equals(" ")) jumpCnt++;
			else if(!tmp.equals(" ") && jumpCnt != 0) {
				tmp = tmp.toUpperCase();
				jumpCnt = 0;
			}
			
			answer += tmp;
		}

		return answer;
	}
	
	public static void main(String[] args) {
		Solution12951 sol = new Solution12951();
		System.out.println(sol.solution("3people unFollowed me"));
	}
}
