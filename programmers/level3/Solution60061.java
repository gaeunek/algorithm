package level3;

import java.util.*;
//기둥과 보 설치
class Solution60061 {
	static boolean[][] pillar, beams;
	static int N;
	public int[][] solution(int n, int[][] build_frame) {
		N = n;
		pillar = new boolean[n+1][n+1];
		beams = new boolean[n+1][n+1];
		
		for (int i = 0; i < build_frame.length; i++) {
			int x = n-build_frame[i][1]; //행
			int y = build_frame[i][0]; //열
			int structure = build_frame[i][2];
			int build = build_frame[i][3];
			
			if(structure == 0 && build == 1 && isPillar(x, y)) pillar[x][y] = true;
			else if(structure == 0 && build == 0) {
				pillar[x][y] = false;
				if(!check()) pillar[x][y] = true;
			}
			
			if(structure == 1 && build == 1 && isBeams(x, y)) beams[x][y] = true;
			else if(structure == 1 && build == 0) {
				beams[x][y] = false;
				if(!check()) beams[x][y] = true;
			}
		}
		
//		print(pillar);
//		print(beams);

		List<int[]> result = new ArrayList<int[]>();
		for (int i = N; i >= 0; i--) {
			for (int j = 0; j <= N; j++) {
				if(pillar[i][j]) result.add(new int[] {j, N-i, 0});
				
				if(beams[i][j]) result.add(new int[] {j, N-i, 1});
			}
		}
		
		int[][] answer = new int[result.size()][3];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = result.get(i);
		}
		
		Arrays.sort(answer, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) return o1[2] - o2[2];
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		
		return answer;
	}
	
	static boolean check() {
		for (int i = N; i >= 0; i--) {
			for (int j = 0; j <= N; j++) {
				if(pillar[i][j] && !isPillar(i, j)) return false;
			}
		}
		
		for (int i = N; i >= 0; i--) {
			for (int j = 0; j <= N; j++) {
				if(beams[i][j] && !isBeams(i, j)) return false;
			}
		}
		
		return true;
	}
	
	static boolean isPillar(int x, int y) {
		if(x == N || beams[x][y] || (y-1 >= 0 && beams[x][y-1]) || pillar[x+1][y]) return true;
		return false;
	}
	
	static boolean isBeams(int x, int y) {
		if(pillar[x+1][y] || pillar[x+1][y+1] || (y-1 >= 0 && y+1 <= N && beams[x][y-1] && beams[x][y+1])) return true;
		return false;
	}
	
	static void print(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Solution60061 sol = new Solution60061();
		int[][] answer = {};
		answer = sol.solution(5, new int[][] {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}});
//		answer = sol.solution(5, new int[][]{{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}});
		for (int[] is : answer) {
			System.out.println(Arrays.toString(is));
		}
	}
}
