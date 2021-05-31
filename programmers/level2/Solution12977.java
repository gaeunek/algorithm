package level2;
//소수만들기
//시간 복잡도 이해가 필요한 문제다. 재귀함수를 조합으로 만들었기 때문에 10! > 10의 4승 이기 때문에 for문이 더 빠르다.
class Solution12977 {
	static int answer;

	public int solution(int[] nums) {
		answer = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				for (int k = j+1; k < nums.length; k++) {
					boolean flag = false;
					int num = nums[i] + nums[j] + nums[k];
					for (int l = 2; l < num; l++) {
						if(num % l == 0) {
							flag = true;
							break;
						}
					}
					if(!flag) answer++;
				}
			}
		}
		return answer;
	}

//	static void combination(int i, int num, int cnt, int[] nums) {
//		if (num > 1 && cnt == 3 && num % 2 != 0 && num % 3 != 0) answer++;
//		
//		if(i == nums.length) return;
//
//		combination(i+1, num+nums[i], cnt+1, nums);
//		combination(i+1, num, cnt, nums);
//	}

//	static boolean decimal(int num) {
//		for (int i = 2; i < num; i++) {
//			if (num % i == 0)
//				return false;
//		}
//		return true;
//	}

	public static void main(String[] args) {
		Solution12977 sol = new Solution12977();
		System.out.println(sol.solution(new int[] { 1, 2, 3, 4 }));
	}
}
