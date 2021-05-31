package summer_winter_coding_2018;
//예산
import java.util.Arrays;

class Solution12982 {
	public int solution(int[] d, int budget) {
		Arrays.sort(d);

		int cnt = 0;
		for (int i : d) {
			if (cnt - i > 0) {
				budget -= i;
				cnt++;
				break;
			}
		}

		return cnt;
	}
}
