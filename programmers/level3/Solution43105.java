package level3;

// 정수 삼각형
class Solution43105 {
	public int solution(int[][] triangle) {
		int answer = 0;
		int[][] origin = new int[triangle.length][];
		
		//원본값을 카피해둔다.
		for (int i = 0; i < triangle.length; i++) {
			origin[i] = new int[triangle[i].length];
			for (int j = 0; j < triangle[i].length; j++) {
				origin[i][j] = triangle[i][j];
			}
		}
		
		for (int i = 0; i < triangle.length-1; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				int now = triangle[i][j];
				
				int a = origin[i+1][j];
				int b = origin[i+1][j+1];
				
				if(a+now > triangle[i+1][j]) triangle[i+1][j] = a+now;
				if(b+now > triangle[i+1][j+1]) triangle[i+1][j+1] = b+now;
			}
		}

		for (int i = 0; i < triangle[triangle.length-1].length; i++)
			answer = Math.max(answer, triangle[triangle.length-1][i]);
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution43105 sol = new Solution43105();
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		sol.solution(triangle);
	}
}
