package level4;

//도둑질
class Solution42897 {
	public int solution(int[] money) {
		int answer = 0;
        
		int[] dp1 = new int[money.length]; // 1번 집 털 때
		int[] dp2 = new int[money.length]; // 1번 집 안 털 때

		dp1[0] = money[0];
        dp1[1] = money[0];
		for (int i = 2; i < money.length - 1; i++) {
			dp1[i] = Math.max(money[i] + dp1[i - 2], dp1[i - 1]);
			answer = Math.max(answer, dp1[i]);
		}

		dp2[1] = money[1];
		for (int i = 2; i < money.length; i++) {
			dp2[i] = Math.max(money[i] + dp2[i - 2], dp2[i - 1]);
			answer = Math.max(answer, dp2[i]);
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution42897 sol = new Solution42897();
		System.out.println(sol.solution(new int[] { 1, 2, 3, 1 }));
		System.out.println(sol.solution(new int[] { 1, 1, 4, 1, 4}));
		System.out.println(sol.solution(new int[] {3, 2, 1}));
	}
}
