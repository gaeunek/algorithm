package level3;
//정수 삼각형
class Solution43105_2 {
	public int solution(int[][] triangle) {
		int answer = 0;
		
		int[][] result = new int[triangle.length][];
		for (int i = 0; i < triangle.length; i++) {
			result[i] =  new int[triangle[i].length];
		}
		
		result[0][0] = triangle[0][0];
		
		for (int i = 1; i < triangle.length; i++) {
			for (int j = 0; j < triangle[i].length-1; j++) {
				int top = result[i-1][j];
				
				if(result[i][j] == 0) result[i][j] = top + triangle[i][j];
				else result[i][j] = Math.max(top + triangle[i][j], result[i][j]);
				
				if(result[i][j+1] == 0) result[i][j+1] = top + triangle[i][j+1];
				else result[i][j+1] = Math.max(top + triangle[i][j+1], result[i][j+1]);
			}
		}
		
		for (int i = 0; i < result[result.length-1].length; i++) {
			answer = Math.max(result[result.length-1][i], answer);
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution43105_2 sol = new Solution43105_2();
		System.out.println(sol.solution(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
	}
}
