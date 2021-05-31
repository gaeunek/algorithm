package level2;

//큰 수 만들기 (StringBuilder를 사용하는것이 중요하다)
class Solution42883 {
	public String solution(String number, int k) {
		StringBuilder answer = new StringBuilder();
		
		char[] nums = number.toCharArray();
		
		int idx = 0;
		
		for (int i = 0; i < nums.length-k; i++) {
			int max = 0;
			System.out.println(i);
			for (int j = idx; j <= i+k; j++) {
				int num = nums[j] - '0';
				if(max < num) {
					idx = j + 1; //다음 인덱스 번호를 저장한다.
					max = num;
				}
			}
			
			answer.append(max);
		}
		
		return answer.toString();
	}
}
