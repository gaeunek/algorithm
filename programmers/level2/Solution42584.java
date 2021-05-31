package level2;
//주식가격

import java.util.Arrays;

class Solution42584 {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		for (int i = 0; i < prices.length; i++) {
			int price = prices[i];
			for (int j = i + 1; j < prices.length; j++) {
				if (price > prices[j] || j == answer.length - 1) {
					answer[i] = j-i;
					break;
				}
			}
		}

		return answer;
	}
	
	public static void main(String[] args) {
		Solution42584 sol = new Solution42584();
		int[] prices = {1,2,3,2,3,1};
		System.out.println(Arrays.toString(sol.solution(prices)));
	}
}
