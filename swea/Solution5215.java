package swea;

import java.io.*;

//1.맛 점수별로 정렬시킨다.
//2.조합 ?만들 수 있는 조합 ...?
//조합을 할거면 맛점수별 정렬이 필요없다.
//같은 재료 여러번 사용 불가
public class Solution5215 {
	static int[][] score_cal;
	static int N, L, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			L = Integer.parseInt(input[1]);

			score_cal = new int[N][2];
			for (int i = 0; i < N; i++) {
				String[] tmp = br.readLine().split(" ");
				score_cal[i][0] = Integer.parseInt(tmp[0]);
				score_cal[i][1] = Integer.parseInt(tmp[1]);
			}
			max = 0;
			combination(0, 0, 0);
			System.out.println("#" + tc + " " + max);
		}
	}

	public static void combination(int score, int cal, int depth) {

		if (cal > L) {
			return;
		}

		max = score > max ? score : max;
		
		if(depth >= N) {
			return;
		}
		//used 사용하니까 시간초과났다.
		combination(score + score_cal[depth][0], cal + score_cal[depth][1], depth + 1);
		
		combination(score, cal, depth + 1);
	}

}
