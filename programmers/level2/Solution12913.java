package level2;

//땅따먹기
class Solution12913 {
	int solution(int[][] land) {
		int answer = 0;
		
		int[][] origin = new int[land.length][4];
		
		for (int i = 0; i < origin.length; i++) {
			for (int j = 0; j < 4; j++) {
				origin[i][j] = land[i][j];
			}
		}
		
		for (int i = 1; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if(k != j && (origin[i][k] + land[i-1][j] > land[i][k])) {
						land[i][k] = origin[i][k] + land[i-1][j];
						answer = land[i][k] > answer ? land[i][k] : answer;
					}
				}
			}
		}
		
		return answer;
	}
	
	static void print(int[][] land) {
		for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(land[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Solution12913 sol = new Solution12913();
		System.out.println(sol.solution(new int[][] {{1,2,3,5}, {5,6,7,8}, {4,3,2,1}}));
	}
}
