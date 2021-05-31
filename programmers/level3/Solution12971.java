package level3;

//스티커 모으기(2)
class Solution12971 {
	public int solution(int sticker[]) {
		int answer = 0;

		int[] dp1 = new int[sticker.length];
		int[] dp2 = new int[sticker.length];
		
		if(sticker.length < 3) {
			for (int i = 0; i < sticker.length; i++) {
				answer = Math.max(sticker[i], answer);
			}
			
			return answer;
		}

		// 0번 인덱스를 선택할 때
		// dp1[1]에도 sticker[0] 값을 넣는 이유는 0번 인덱스를 선택하면
		// 1번은 선택못하니 2번 인덱스는 무조건 0번 인덱스 값만을 더하니까
		dp1[0] = sticker[0];
		dp1[1] = sticker[0];
		for (int i = 2; i < sticker.length - 1; i++) {
			dp1[i] = Math.max(sticker[i] + dp1[i - 2], dp1[i - 1]);
		}

		dp2[1] = sticker[1];
		for (int i = 2; i < dp2.length; i++) {
			dp2[i] = Math.max(sticker[i] + dp2[i - 2], dp2[i - 1]);
		}

		int max = 0;
		for (int i = 0; i < sticker.length; i++) {
			max = Math.max(dp1[i], dp2[i]);
			answer = Math.max(answer, max);
		}
		
		return answer;
	}
}
