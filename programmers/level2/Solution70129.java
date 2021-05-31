package level2;

class Solution70129 {
	public int[] solution(String s) {
		int[] answer = new int[2];
		
		while(s.length() > 1) {
			String tmp = "";
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '1') tmp += 1;
			}
			
			answer[0]++;
			answer[1] += s.length() - tmp.length();
			
			s = binary(tmp.length());
		}
		
		return answer;
	}
	
	static String binary(int a) {
		if(a == 1) return String.valueOf(a);
		return binary(a/2) + String.valueOf(a%2);
	}
}
