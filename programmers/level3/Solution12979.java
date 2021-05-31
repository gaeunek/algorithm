package level3;
//기지국 설치
class Solution12979 {
	public int solution(int n, int[] stations, int w) {
		int answer = 0;
		int distance = w * 2 + 1;
		
		int st = 1, left = 1, right = 1, num = 0;
		for (int i = 0; i < stations.length; i++) {
			left = stations[i] - w < 1 ? 1 : stations[i] - w;
			right = stations[i] + w > n ? n : stations[i] + w;
			
			num = left - st;
			
			if(num <= 0) {
				st = right + 1;
				continue;
			}
			answer += num / distance;
			if(num % distance != 0) answer++;
			st = right + 1;
		}
		
		if(st <= n) {
			num = n - st + 1;
			answer += num / distance;
			if(num % distance != 0) answer++;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution12979 sol = new Solution12979();
//		System.out.println(sol.solution(13, new int[] {5, 8, 12}, 1));
//		System.out.println(sol.solution(16, new int[] {9}, 2));
//		System.out.println(sol.solution(11, new int[] {4, 11}, 1));
//		System.out.println(sol.solution(5, new int[] {1, 4}, 1));
//		System.out.println(sol.solution(13, new int[] {8}, 2));
//		System.out.println(sol.solution(13, new int[] {2, 7}, 1));
		System.out.println(sol.solution(20, new int[] {5, 10, 15, 20}, 10));
	}
}
