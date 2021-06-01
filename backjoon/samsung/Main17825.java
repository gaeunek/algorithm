package samsung;

import java.io.*;
import java.util.*;

public class Main17825 {
	static int[] dice = new int[10];
	static int[][] map = new int[5][42];
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		int value = 0;
		// 빨간 선
		for (int j = 0; j < 21; j++, value+=2) {
			map[0][j] = value;
		}
		
		// 10에서 만난 파란선
		map[1][10] = 13;
		map[1][13] = 16;
		map[1][16] = 19;
		map[1][19] = 25;
		
		// 20에서 만난 파란선
		map[2][20] = 22;
		map[2][22] = 24;
		map[2][24] = 25;
		
		// 30에서 만난 파란선
		map[3][30] = 28;
		map[3][28] = 27;
		map[3][27] = 26;
		map[3][26] = 25;
		
		// 중앙
		map[4][25] = 30;
		map[4][30] = 35;
		map[4][35] = 40;
		
		answer = 0;
		int[][] horse = new int[4][2]; // 말의 위치 저장
		List<Integer> horseList = new ArrayList<Integer>(); // 현재 윷놀이판에 있는 말 저장
		dfs(0, 0, horse, horseList, new boolean[5][42], false);
		System.out.println(answer);
	}

	public static void dfs(int score, int n, int[][] origin_horse, List<Integer> origin_horseList, boolean[][] origin_visited, boolean check) {
		if(n == dice.length || check) {
			answer = Math.max(score, answer);
			return;
		}
		
		int[][] horse = copyHorse(origin_horse);
		List<Integer> horseList = copyHorseList(origin_horseList);
		boolean[][] visited = copyVisited(origin_visited);

		for (int i = 0; i <= horseList.size(); i++) {
			if(i >= 4) break;
			int k = dice[n]; // 이동해야 하는 칸 수
			int line, board;
			int h_number = i;
			if (i == horseList.size()) // 새로운 말 추가
				horseList.add(i);
			else h_number = horseList.get(i);
			
			// 현재 말의 위치
			line = horse[h_number][0];
			board = horse[h_number][1];
			
			// 빨간 라인 따라가고있던게 아니면 반복문 돌려야됨
			if(board != 40 && h_number == 0 && board % 10 == 0) {
				line = board / 10;
				
				for (int j = 0; j < k; j++) {
					board = map[line][board];
				}
			} else if(board == 25) {
				line = 4;
				
				for (int j = 0; j < k; j++) {
					board = map[line][board];
				}
			} else {
				board += k;
			}
			
//			System.out.println("라인은 :"+line+", 발판 값은 :"+board);
			// 이동하려는 칸에 다른 말이 있다면 이동할 수 없다 패스
			if(board > 41 || visited[line][board]) continue;
			
			visited[line][board] = true;
			visited[horse[h_number][0]][horse[h_number][1]] = false;
			horse[h_number][0] = line;
			horse[h_number][1] = board;
			
			if(board == 41) check = true;
			dfs(score + map[line][board], n + 1, horse, horseList, visited, check);
			visited[line][board] = false;
		}
	}
	
	public static int[][] copyHorse(int[][] origin){
		int[][] copy = new int[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}
	
	public static List<Integer> copyHorseList(List<Integer> origin){
		List<Integer> copy = new ArrayList<Integer>();
		for (int i = 0; i < origin.size(); i++) {
			copy.add(origin.get(i));
		}
		
		return copy;
	}
	
	public static boolean[][] copyVisited(boolean[][] origin){
		boolean[][] copy = new boolean[5][42];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j <  42; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}
}
