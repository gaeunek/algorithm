package samsung;

import java.io.*;
import java.util.*;

public class Main15686_2 {
	static int N, M, answer;
	static int[][] map;
	static ArrayList<Point> homeList, chickenList;

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		homeList = new ArrayList<>();
		chickenList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					homeList.add(new Point(i, j));
				else if (map[i][j] == 2)
					chickenList.add(new Point(i, j));
			}
		}

		int[][] dist = calculateDistance();
		if (M == chickenList.size()) {
			int sum = 0;
			for (int i = 0; i < homeList.size(); i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < chickenList.size(); j++) {
					min = Math.min(dist[i][j], min);
				}

				sum += min;
			}
			System.out.println(sum);
			return;
		}

		answer = Integer.MAX_VALUE;
		permutation(dist, new boolean[chickenList.size()], 0, 0);
		System.out.println(answer);
	}

	public static void permutation(int[][] dist, boolean[] used, int count, int index) {
		if (count == M) {
			int[] result = new int[homeList.size()];
			Arrays.fill(result, Integer.MAX_VALUE);
			
			for (int j = 0; j < used.length; j++) {
				if(used[j]) {
					for (int i = 0; i < homeList.size(); i++) {
						result[i] = Math.min(result[i], dist[i][j]);
					}
				}
			}
			
			int sum = 0;
			for (int i = 0; i < result.length; i++) {
				sum += result[i];
				if(sum > answer) return;
			}
			
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = index; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				permutation(dist, used, count + 1, i + 1);
				used[i] = false;
			}
		}
	}

	public static int[][] calculateDistance() {
		int[][] dist = new int[homeList.size()][chickenList.size()];

		for (int i = 0; i < homeList.size(); i++) {
			Point home = homeList.get(i);

			for (int j = 0; j < chickenList.size(); j++) {
				Point chicken = chickenList.get(j);

				dist[i][j] = Math.abs(chicken.i - home.i) + Math.abs(chicken.j - home.j);
			}
		}

		return dist;
	}
}
