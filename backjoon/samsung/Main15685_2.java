package samsung;

import java.io.*;
import java.util.*;

public class Main15685_2 {
	static int N;
	static boolean[][] map;
	static ArrayList<Integer> directions;
	static final int RIGTH = 0;
	static final int UP = 1;
	static final int LEFT = 2;
	static final int DOWN = 3;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new boolean[101][101];
		StringTokenizer st;
		directions = new ArrayList<>();
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			directions.add(d);
			for (int i = 0; i < g; i++) {
				int size = directions.size();
				for (int j = size - 1; j >= 0 ; j--) {
					int newd = (directions.get(j) + 1) % 4;
					directions.add(newd);
				}
			}
			drawCurve(x, y);
			directions.clear();
		}
		
		System.out.println(checkSquare());
	}
	
	private static int checkSquare() {
		int result = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) result++;
			}
		}
		return result;
	}

	public static void drawCurve(int x, int y) {
		map[y][x] = true;
		for (int i = 0; i < directions.size(); i++) {
			int d = directions.get(i);
			
			switch (d) {
			case RIGTH:
				map[y][++x] = true;
				break;
			case UP:
				map[--y][x] = true;
				break;
			case LEFT:
				map[y][--x] = true;
				break;
			default:
				map[++y][x] = true;
				break;
			}
		}
	}
}
