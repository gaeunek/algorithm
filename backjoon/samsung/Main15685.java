package samsung;

import java.io.*;
import java.util.*;

public class Main15685 {
	static int N;
	static boolean[][] map;
	static int[][] dir = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int[][] squareDir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
	static int[] rotateEndPoint, newEndPoint;

	static boolean check(int i, int j) {
		if (i >= 0 && i < 101 && j >= 0 && j < 101)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new boolean[101][101];
		rotateEndPoint = new int[2];
		newEndPoint = new int[2];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			boolean[][] newMap = new boolean[101][101];
			// 0세대 추가
			newMap[row][col] = true;
			newMap[row + dir[d][0]][col + dir[d][1]] = true;
			if (g > 0) {
				newEndPoint[0] = row + dir[d][0];
				newEndPoint[1] = col + dir[d][1];
				newMap = dfs(newMap, 0, g);
			}
			copyNewMapToOriginMap(newMap);
		}

		System.out.println("결과");
		print(map);
		System.out.println(checkSquareCount());
	}

	private static int checkSquareCount() {
		int result = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(!map[i][j]) continue;
				boolean flag = true;
				for (int d = 0; d < 3; d++) {
					int ni = i + squareDir[d][0];
					int nj = j + squareDir[d][1];
					
					if(!check(ni, nj) || !map[ni][nj]) {
						flag = false;
						break;
					}
				}
				
				if(flag) result++;
			}
		}
		return result;
	}

	public static boolean[][] dfs(boolean[][] newMap, int g, int og) {
		if (og == g) {
			return newMap;
		}

		int row = newEndPoint[0];
		int col = newEndPoint[1];

//		print(newMap);
//		System.out.println("끝점 : "+Arrays.toString(newEndPoint));
		// 회전
		boolean[][] rotateMap = rotate(newMap, row, col);

		// 회전한 배열을 newMap 끝점에 추가
		newMap = copyRotateMapToNewMap(newMap, rotateMap, row, col);
		return dfs(newMap, g + 1, og);
	}

	public static boolean[][] copyRotateMapToNewMap(boolean[][] origin, boolean[][] rotate, int row, int col) {
		int nowR = rotateEndPoint[0]; // 회전된 끝점, row col은 원래 끝점
		int nowC = rotateEndPoint[1];
		int nr = 0, nc = 0;

		while (true) {
			boolean flag = false;
			rotate[nowR][nowC] = false;
//			System.out.println(nowR+", "+nowC);
			for (int d = 0; d < 4; d++) {
				nr = nowR + dir[d][0];
				nc = nowC + dir[d][1];

				if (check(nr, nc) && rotate[nr][nc]) {
//					System.out.println(nr+", "+nc+", "+row+", "+col);
					origin[row + dir[d][0]][col + dir[d][1]] = true;
					flag = true;
					nowR = nr;
					nowC = nc;
					row += dir[d][0];
					col += dir[d][1];
					break;
				}
			}

			if (!flag) {
				newEndPoint[0] = row;
				newEndPoint[1] = col;
				break;
			}
		}

		return origin;
	}

	public static boolean[][] rotate(boolean[][] origin, int nowR, int nowC) {
		boolean[][] rotateMap = new boolean[101][101];

		for (int i = 0; i < 101; i++) {
			int newj = 100 - i;
			for (int j = 0; j < 101; j++) {
				if (i == nowR && j == nowC) { // 현재 끝점을 회전시킨다면 회전된 후의 좌표를 저장해준다
					rotateEndPoint[0] = j;
					rotateEndPoint[1] = newj;
				}

				rotateMap[j][newj] = origin[i][j];
			}
		}

//		print(rotateMap);
		return rotateMap;
	}

	public static void copyNewMapToOriginMap(boolean[][] newMap) {
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (!map[i][j] && newMap[i][j])
					map[i][j] = newMap[i][j];
			}
		}
	}

	public static void print(boolean[][] arr) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
//				System.out.print(arr[i][j] + "\t");
				if(arr[i][j])
				System.out.print(i+", "+j+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}

}
