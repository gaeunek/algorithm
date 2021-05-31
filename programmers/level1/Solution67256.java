package level1;

// 숫자 키패드 ?
class Solution67256 {
	public String solution(int[] numbers, String hand) {
		Point[] keypad = new Point[12];

		int index = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				keypad[index++] = new Point(i, j);
			}
		}

		keypad[10] = new Point(3, 0); // *
		keypad[0] = new Point(3, 1);
		keypad[11] = new Point(3, 2); // #

		String answer = "";

		Point left = keypad[10], right = keypad[11];

		for (int i = 0; i < numbers.length; i++) {
			int num = numbers[i];
			Point now = keypad[num];

			if (num == 1 || num == 4 || num == 7) {
				left = now;
				answer += "L";
			} else if (num == 3 || num == 6 || num == 9) {
				right = now;
				answer += "R";
			} else {
				int dist_left = Math.abs(left.i - now.i) + Math.abs(left.j - now.j);
				int dist_right = Math.abs(right.i - now.i) + Math.abs(right.j - now.j);

				if (dist_left < dist_right || (dist_left == dist_right && hand.equals("left"))) {
					left = now;
					answer += "L";
				} else if (dist_left > dist_right || (dist_left == dist_right && hand.equals("right"))) {
					right = now;
					answer += "R";
				}
			}
		}

		return answer;
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) {
		Solution67256 sol = new Solution67256();
		System.out.println(sol.solution(new int[] { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 }, "right"));
		System.out.println(sol.solution(new int[] { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 }, "left"));
	}
}
