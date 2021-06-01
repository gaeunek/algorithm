package samsung;

import java.beans.Visibility;
import java.io.*;
import java.util.*;

public class Main14891 {
	static int K;
	static int[][] gear;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		gear = new int[5][9];
		for (int i = 1; i <= 4; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 1; j <= 8; j++) {
				gear[i][j] = tmp[j - 1] - '0';
			}
		}

		K = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gear_number = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			boolean[] visited = new boolean[5];
			dfs(gear_number, dir, visited);
		}
		
		int answer = 0;
		for (int i = 1; i <= 4; i++) {
			if(gear[i][1] == 1) answer += Math.pow(2, i - 1); 
		}
		System.out.println(answer);
	}

	public static void dfs(int gear_number, int dir, boolean[] visited) {
		visited[gear_number] = true;

		// 1이면 3만 보고 4면 7만 본다
		int now_right, now_left;
		now_right = gear[gear_number][3];
		now_left = gear[gear_number][7];

		// dfs 호출 후 돌린다.
		if (gear_number < 4 && !visited[gear_number + 1] && now_right != gear[gear_number + 1][7]) {
			dfs(gear_number + 1, dir * -1, visited);
		}
		if (gear_number > 1 && !visited[gear_number - 1] && now_left != gear[gear_number - 1][3]) {
			dfs(gear_number - 1, dir * -1, visited);
		}

		int[] newGear = new int[9];
		for (int i = 1; i <= 8; i++) {
			newGear[i] = gear[gear_number][i];
		}
		
//		System.out.println("방향은 : "+dir+"이고 "+gear_number+"번 Gear 복사 :" + Arrays.toString(newGear));
		for (int j = 1; j <= 8; j++) {
			int value = (j - dir) % 8;
			gear[gear_number][j] = newGear[value == 0 ? 8 : value];
		}
//		System.out.println("이동 후:"+Arrays.toString(gear[gear_number]));
	}
	
	public static void print() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 8; j++) {
				System.out.print(gear[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
