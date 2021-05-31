package level2;
//타겟넘버
class Solution43165 {
	static boolean[] used;
	static int[] nums;
	static int answer;

	public int solution(int[] numbers, int target) {
		answer = 0;
		nums = numbers;
		used = new boolean[numbers.length];

		dfs(0, 0, target);

		return answer;
	}

	static void dfs(int idx, int sum, int target) {
		if (idx == nums.length) {
			if (sum == target)
				answer++;

			return;
		}

		dfs(idx + 1, sum + nums[idx], target);
		dfs(idx + 1, sum - nums[idx], target);
	}

	public static void main(String[] args) {
		Solution43165 sol = new Solution43165();
		int[] numbers = { 1, 1, 1, 1, 1 };
		System.out.println(sol.solution(numbers, 3));
	}
}
