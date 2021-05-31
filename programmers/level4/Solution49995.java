package level4;

// ÄíÅ° ±¸ÀÔ
class Solution49995 {
	public int solution(int[] cookie) {
		int answer = 0;

		if (cookie.length == 1)
			return 0;

		for (int i = 0; i < cookie.length - 1; i++) {
			int indexOfFirst = i;
			int indexOfSecond = i + 1;

			int sumOfFirst = cookie[i];
			int sumOfSecond = cookie[i + 1];
			
			while (true) {
				
				if (sumOfFirst == sumOfSecond) {
					answer = Math.max(answer, sumOfFirst);
				}

				if (indexOfFirst > 0 && sumOfFirst <= sumOfSecond) {
					indexOfFirst--;
					sumOfFirst += cookie[indexOfFirst];
				} else if (indexOfSecond < cookie.length - 1 && sumOfSecond <= sumOfFirst) {
					indexOfSecond++;
					sumOfSecond += cookie[indexOfSecond];
				} else break;
			}
			
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution49995 sol = new Solution49995();
		System.out.println(sol.solution(new int[] { 1, 1, 2, 3 }));
		System.out.println(sol.solution(new int[] { 1, 2, 4, 5 }));
	}

}
