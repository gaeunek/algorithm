package level3;

//징검다리
import java.util.*;

class Solution43236 {
	public int solution(int distance, int[] rocks, int n) {
		int answer = 0;

		Arrays.sort(rocks); // 일단 오름차순으로 정렬한다.

		int start = 1;
		int end = distance;
		while (start <= end) {
			int cnt = 0;
			int last = 0;
			int mid = (start + end) / 2;
			System.out.println("start : "+start +", end : "+end+", mid : "+mid);

			for (int i = 0; i < rocks.length + 1; i++) {
				int dis = i != rocks.length ? rocks[i] - last : distance;
				
				if(dis < mid)
					cnt++;
				else if(i != rocks.length) //rocks.length까지 가면 에러나니까 넣어 줘야하는 조건
					last = rocks[i];
			}
			
			if(cnt > n)
				end = mid - 1;
			else {
				start = mid + 1;
				answer = mid;
			}
		}

		return answer;
	}
}
