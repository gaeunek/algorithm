package level3;

class Solution42897 {
	public int solution(int[] money) {
        int[] dp = new int[money.length];
        int max = 0;
        for (int i = 0; i < money.length-2; i++) {
        	if(dp[i] == 0) dp[i] = money[i];
			int st = i+2;
			int end = i != 0 ? money.length : money.length-1;
			for (int j = st; j < end; j++) {
				dp[j] = Math.max(dp[j], dp[i] + money[j]);
				max = Math.max(max, dp[j]);
			}
		}
        
        return max;
    }
	
	public static void main(String[] args) {
		Solution42897 sol = new Solution42897();
		System.out.println(sol.solution(new int[] {1,2,3,1}));
		System.out.println(sol.solution(new int[] {1,2,3,1,1,2,1}));
	}
	
}
