package level2;
//행렬 곱셈
class Solution12949 {
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr2[0].length];

		for (int i = 0; i < arr1.length; i++) {
			for (int j1 = 0; j1 < arr2[0].length; j1++) {
				for (int j2 = 0; j2 < arr2.length; j2++) {
					answer[i][j1] += arr1[i][j2] * arr2[j2][j1];
				}
			}
		}
		return answer;
	}
}
