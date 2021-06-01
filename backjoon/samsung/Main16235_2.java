package samsung;

import java.io.*;
import java.util.*;

public class Main16235_2 {
	static int N, M, K;
	static int[][] A, map;
	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
//	static ArrayList<Point> treeList, deadTreeList;
	static PriorityQueue<Point> tree;
	static Queue<Point> deadTree;

	static class Point implements Comparable<Point> {
		int i, j, age;

		public Point(int i, int j, int age) {
			this.i = i;
			this.j = j;
			this.age = age;
		}

		@Override
		public int compareTo(Point o) {
			return this.age - o.age;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", age=" + age + "]";
		}
	}

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		map = new int[N][N];
		tree = new PriorityQueue<>();
		deadTree = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 초기 양분은 5이다.
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			tree.add(new Point(x, y, age));
		}

		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}

		System.out.println(tree.size());
	}

	public static void spring() {
		PriorityQueue<Point> newTree = new PriorityQueue<>();
		
		while (!tree.isEmpty()) {
			Point p = tree.poll();
			int nutrient = map[p.i][p.j] - p.age;
			if (nutrient >= 0) {
				map[p.i][p.j] = nutrient;
				newTree.add(new Point(p.i, p.j, p.age + 1));
			} else {
				deadTree.add(new Point(p.i, p.j, p.age / 2));
			}
		}
		
		tree = newTree;
	}

	public static void summer() {
		while (!deadTree.isEmpty()) {
			Point p = deadTree.poll();
			map[p.i][p.j] += p.age;
		}
	}

	public static void fall() {
		PriorityQueue<Point> newTree = new PriorityQueue<>();
		while (!tree.isEmpty()) {
			Point p = tree.poll();

			if (p.age % 5 == 0) { // 나이가 5의 배수인 경우에만 번식
				for (int d = 0; d < 8; d++) {
					int ni = p.i + dir[d][0];
					int nj = p.j + dir[d][1];

					if (check(ni, nj)) {
						newTree.add(new Point(ni, nj, 1));
					}
				}
			}

			newTree.add(p);
		}
		
		for(Point p : newTree) {
			tree.add(p);
		}
	}

	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
}
