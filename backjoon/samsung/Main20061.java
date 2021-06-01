package samsung;

import java.io.*;
import java.util.*;

public class Main20061 {
	static int N, score, block;
	static boolean[][] blue, green;

	static boolean check(int x, int y, int row, int col) {
		if (x >= 0 && x < row && y >= 0 && y < col)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		blue = new boolean[4][6];
		green = new boolean[6][4];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int t = Integer.parseInt(st.nextToken()); // 블록의 크기
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			stack(x, y, t);
//			print(green);
		}

//		print(blue);
		countBlock();

		System.out.println(score);
		System.out.println(block);
	}

	public static void stack(int x, int y, int t) {
		stackBlue(x, 3 - y, t); // 파란색으로 블록 옮기기
		stackGreen(3 - x, y, t); // 초록색으로 블록 옮기기
	}

	public static void stackBlue(int x, int y, int t) {
		int nx = x, ny = y, nxx, nyy;
		switch (t) {
		case 1:
			while (check(nx, ny, 4, 6) && !blue[nx][ny]) {
				ny++;
			}

			blue[nx][ny - 1] = true;
			lightBlueArea(ny - 1);
			break;
		default:
			if (t == 2) {
				nxx = x;
				nyy = y + 1;
			} else {
				nxx = x + 1;
				nyy = y;
			}

			while (check(nxx, nyy, 4, 6) && check(nx, ny, 4, 6) && !blue[nx][ny] && !blue[nxx][nyy]) {
				ny++;
				nyy++;
			}

			blue[nx][ny - 1] = true;
			blue[nxx][nyy - 1] = true;
			lightBlueArea(ny - 1);
			break;
		}
	}

	public static void stackGreen(int x, int y, int t) {
//		System.out.println("============쌓기 전");
//		print(green);
		int nx = x, ny = y, nxx, nyy;
		switch (t) {
		case 1:
			while (check(nx, ny, 6, 4) && !green[nx][ny]) {
				nx++;
			}

			green[nx - 1][ny] = true;
			lightGreenArea(nx - 1);
			break;
		default:
			if (t == 2) {
				nxx = x;
				nyy = y + 1;
			} else {
				nxx = x + 1;
				nyy = y;
			}

			while (check(nx, ny, 6, 4) && check(nxx, nyy, 6, 4) && !green[nx][ny] && !green[nxx][nyy]) {
				nx++;
				nxx++;
			}

			green[nx - 1][ny] = true;
			green[nxx - 1][nyy] = true;

//			System.out.println("===================쌓은 후");
//			print(green);
			lightGreenArea(nx - 1);
			break;
		}
//		System.out.println("===========쌓은 후");
//		print(green);
	}

	public static void lightBlueArea(int ny) {
		boolean isGetScore = getScoreBlue();
		// 점수 얻을 수 있는 행 찾는다.
		if (ny <= 1 && !isGetScore) {
			// 가장 아래 행 삭제한다
			int newj = 5;
			for (int j = ny; j <= 1; j++) {
				for (int i = 0; i < 4; i++) {
					blue[i][newj] = false;
				}
				newj--;
			}
			pullBlueBlock(5, 1);
		}
	}

	public static void lightGreenArea(int nx) {
		boolean isGetScore = getScoreGreen();
		if (nx <= 1 && !isGetScore) {
			int newi = 5;
			for (int i = nx; i <= 1; i++) {
				for (int j = 0; j < 4; j++) {
					green[newi][j] = false;
				}
				newi--;
			}
			pullGreenBlock(5, 1);
//			System.out.println("============연한 부분 땡겨준 후");
//			print(green);
		}
	}

	public static boolean getScoreBlue() {
		boolean isGetScore = false;
		for (int j = 5; j >= 2; j--) {
			int count = 0;
			for (int i = 0; i < 4; i++) {
				if (blue[i][j])
					count++;
			}

			if (count == 4) {
				isGetScore = true;
				for (int i = 0; i < 4; i++) {
					blue[i][j] = false;
				}
				score++;
				pullBlueBlock(j, 3);
			}
		}

		return isGetScore;
	}

	public static boolean getScoreGreen() {
		boolean isGetScore = false;
		for (int i = 5; i >= 2; i--) {
			int count = 0;
			for (int j = 0; j < 4; j++) {
				if (green[i][j])
					count++;
			}

			if (count == 4) {
				isGetScore = true;
				for (int j = 0; j < 4; j++) {
					green[i][j] = false;
				}
				score++;
				pullGreenBlock(i, 3);
			}
		}

//		System.out.println("===============점수 얻은 후");
//		print(green);
		return isGetScore;
	}

	public static void pullBlueBlock(int st, int end) {
		for (int j = st; j >= end; j--) {
			for (int i = 0; i < 4; i++) {
				blue[i][j] = blue[i][j - 1];
				blue[i][j - 1] = false;
			}
		}
	}

	public static void pullGreenBlock(int st, int end) {
//		System.out.println("=================당기기 전");
//		print(green);
		for (int i = st; i >= end; i--) {
			for (int j = 0; j < 4; j++) {
				green[i][j] = green[i - 1][j];
				green[i - 1][j] = false;
			}
		}
	}

	public static void countBlock() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (blue[i][j])
					block++;
			}
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j])
					block++;
			}
		}
	}

	public static void print(boolean[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
