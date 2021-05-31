package kakao;

// 자물쇠와 열쇠
class Solution60059 {
	static int key_len, lock_len;

	public boolean solution(int[][] key, int[][] lock) {
		key_len = key.length;
		lock_len = lock.length;

		// padding의 크기가 중요!
		int[][] padding = new int[lock_len + (key_len - 1) * 2][lock_len + (key_len - 1) * 2];
		int[][] origin = new int[padding.length][padding.length];
		int hole = 0;

		// padding 중심에 lock배열 값을 배치하고 hole의 갯수를 카운트한다.
		for (int i = 0; i < lock_len; i++) {
			for (int j = 0; j < lock_len; j++) {
				if (lock[i][j] == 1) {
					origin[key_len + i - 1][key_len + j - 1] = padding[key_len + i - 1][key_len + j - 1] = 1;
					continue;
				}
				hole++;
			}
		}

		int key_cnt = 0;
		for (int i = 0; i < key_len; i++) {
			for (int j = 0; j < key_len; j++) {
				if (key[i][j] == 1)
					key_cnt++;
			}
		}

		// 예외처리1. lock이 모두 1이면 true를 return한다.
		if (hole == 0)
			return true;

		// 예외처리2. key 갯수가 hole 갯수보다 적으면 어차피 열 수 없으니까 바로 종료
		if (key_cnt < hole)
			return false;

		for (int i = 0; i < 4; i++) { // 90, 180, 270 회전
			if (i != 0)
				key = rotat(key);

			if (move(key, lock, padding, origin))
				return true;
		}

		return false;
	}

	static boolean move(int[][] key, int[][] lock, int[][] padding, int[][] origin) {
		for (int a = 0; a < lock_len + key_len - 1; a++) {
			for (int b = 0; b < lock_len + key_len - 1; b++) {

				// 회전시킨 Key를 이동시킴
				for (int i = 0; i < key_len; i++) {
					for (int j = 0; j < key_len; j++) {
						padding[i + a][j + b] += key[i][j];
					}
				}

				if (open(padding))
					return true;
				else
					padding = copy(origin, padding);
			}
		}
		return false;
	}

	static int[][] copy(int[][] origin, int[][] after) {
		for (int i = 0; i < after.length; i++) {
			for (int j = 0; j < after.length; j++) {
				after[i][j] = origin[i][j];
			}
		}
		return after;
	}

	// 자물쇠에 일치하는지 확인
	static boolean open(int[][] padding) {
		for (int i = key_len - 1; i < lock_len + key_len - 1; i++) {
			for (int j = key_len - 1; j < lock_len + key_len - 1; j++) {
				if (padding[i][j] == 2 || padding[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	// key를 90도 회전
	static int[][] rotat(int[][] key) {
		int[][] result = new int[key_len][key_len];
		int[] tmp = new int[key_len];

		for (int i = 0; i < key_len; i++) {
			tmp = key[i];
			for (int j = 0; j < key_len; j++)
				result[j][key_len - 1 - i] = tmp[j];
		}

		return result;
	}

	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Solution60059 sol = new Solution60059();
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(sol.solution(key, lock));
	}
}
