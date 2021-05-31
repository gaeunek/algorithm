package level3;

import java.util.*;
//경주로건설
class Solution67259 {
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int N;
	static int[][] arr;
	static int answer;
	
	static boolean check(int i, int j) {
		if(i >= 0 & i < N && j >= 0 && j < N) return true;
		return false;
	}
	
	public int solution(int[][] board) {
		N = board.length;
		arr = new int[N][N];
		answer = Integer.MAX_VALUE;
		
		board[0][0] = 1;
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
			continue;
		}
		arr[0][0] = 100;
		bfs(board);
		return answer - 100;
	}
	
	static void bfs(int[][] board) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(0, 0, 0, 100));
		queue.add(new Point(0, 0, 1, 100));
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			if(now.i == N-1 && now.j == N-1) {
				answer = Math.min(answer, arr[N-1][N-1]);
			};
			
			for (int d = 0; d < 4; d++) {
				int ni = dir[d][0] + now.i;
				int nj = dir[d][1] + now.j;
				
				if(check(ni, nj) && board[ni][nj] == 0) {
					int ncost = now.cost + (now.way != d ? 600 : 100);
					if(arr[ni][nj] >= ncost) {
						arr[ni][nj] = ncost;
						queue.add(new Point(ni, nj, d, ncost));
					}
				}
			}
		}
	}
	
	/* 배열에 저장하는 경우와 객체에 저장하는 경우 정답이 다르다. 왜 지금까지의 비용을 저장하지 않으면 안될까 ? */
	static class Point {
		int i, j, way, cost;
		
		public Point(int i, int j, int way, int cost) {
			this.i = i;
			this.j = j;
			this.way = way;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) {
		Solution67259 sol = new Solution67259();
//		System.out.println(sol.solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
		System.out.println(sol.solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
//		System.out.println(sol.solution(new int[][] {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}}));
//		System.out.println(sol.solution(new int[][]{{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}));
	}
}
 