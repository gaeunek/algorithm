package level2;

import java.util.Arrays;
//카카오프렌즈 컬러링북
class Solution68936 {
	static int[] answer;
	public int[] solution(int[][] arr) {
		answer = new int[2];
		division(arr, 0, 0, arr.length);
		return answer;
	}
	
	static void division(int[][] arr, int row, int col, int mid) {
		if(mid == 0) return;
		int zero = 0, one = 0;
		for (int i = row; i < row+mid; i++) {
			for (int j = col; j < col+mid; j++) {
				if(arr[i][j] == 1) one++;
				else zero++;
			}
		}
		
		int area = mid * mid;
		if(one == area) answer[1]++;
		else if(zero == area) answer[0]++;
		else { //이 부분에서 헤맴
			mid /= 2;
			division(arr, row, col, mid);
			division(arr, row, col+mid, mid);
			division(arr, row+mid, col, mid); 
			division(arr, row+mid, col+mid, mid);
		}
	}
	
	public static void main(String[] args) {
		Solution68936 sol = new Solution68936();
//		System.out.println(Arrays.toString(sol.solution(new int[][]{{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}})));
		System.out.println(Arrays.toString(sol.solution(new int[][]{{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},{0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,1},{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}})));
	}
}
