package level3;
// 징검다리 건너기
class Solution64062 {
	public int solution(int[] stones, int k) {
		int answer = 0;
		int max = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < stones.length; i++) {
			max = Math.max(stones[i], max);
			min = Math.min(stones[i], min);
		}
		
		while(min <= max) {
			int mid = (min + max) / 2;
			int cnt = 0;
			
			boolean flag = false;
			
			for (int i = 0; i < stones.length; i++) {
				if(stones[i] - mid <= 0) cnt++;
				else cnt = 0;
				
				if(cnt == k) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				answer = mid;
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution64062 sol = new Solution64062();
		System.out.println(sol.solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 }, 3));
		System.out.println(sol.solution(new int[] {1,2,1,2}, 3));
	}
}
