package level2;

import java.util.*;
//힙, 카펫
class Solution42842 {
	// 가로는 세로와 같거나 길다.
	// 옐로우를 감싸기 위해선 세로 길이가 3이상이어야 한다.
	// yellow와 brown 둘 다 약수를 구함
	// brown의 크기가 yellow 약수에 적합한지 확인 가로-2 세로 -2

	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];

		ArrayList<int[]> yellow_list = new ArrayList<>();

		for (int i = yellow; i >= 1; i--) {
			if(yellow % i == 0) {
				int[] tmp = {i, yellow / i};
				yellow_list.add(tmp);
			}
		}
		
		int size = brown + yellow;

		for (int i = size; i >= 3; i--) {
			boolean flag = false;
			if (size % i == 0) {
				int width = i;
				int height = size / i;

				for (int j = 0; j < yellow_list.size(); j++) {
					int[] yellows = yellow_list.get(j);
					if (width - 2 >= yellows[0] && height - 2 >= yellows[1] && width >= height) {
						answer[0] = width;
						answer[1] = height;
						flag = true;
					}
				}
			}
			
			if(flag) break;
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}

	public static void main(String[] args) {
		Solution42842 sol = new Solution42842();
		sol.solution(8, 1);
	}
}
