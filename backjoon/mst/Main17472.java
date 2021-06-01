package mst;

import java.util.*;
import java.io.*;

public class Main17472 {
	static int N, M, num, answer, count;
	static int[] parent;
	static int[][] dir = { { 0, 1, }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] board;
	static Queue<Point> island;
	static List<Edge> edgeList;

	static boolean isRange(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < M)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		island = new LinkedList<>();
		edgeList = new ArrayList<>();
		num = 1;
		
		division(); // 섬 번호 부여
		connect();
		
		parent = new int[num];
		answer = 0;
		count = 0;
		mst();
		
		System.out.println(count == num - 2 ? answer : -1);
	}
	
	static void mst() {
		for (int i = 1; i < num; i++) {
			parent[i] = i;
		}
		
		Collections.sort(edgeList);
		
		for (int i = 0; i < edgeList.size(); i++) {
			Edge e = edgeList.get(i);
			
			int findA = find(e.st);
			int findB = find(e.end);
			
			if(findA != findB) {
				parent[findB] = findA;
				answer += e.cost;
				count++;
			}
		}
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		return find(parent[n]);
	}

	static void connect() {
		while (!island.isEmpty()) {
			Point p = island.poll();

			for (int d = 0; d < 4; d++) {
				Edge e = bridge(d, p.i, p.j, board[p.i][p.j]);
				if(e != null) {
					edgeList.add(e);
				}
			}
		}
	}

	static Edge bridge(int d, int i, int j, int num) {
		int ni = i, nj = j;
		while (isRange(ni, nj)) {
			ni += dir[d][0];
			nj += dir[d][1];

			if (!isRange(ni, nj) || board[ni][nj] == board[i][j]) {
				break;
			} else if (isRange(ni, nj) && board[ni][nj] > 0) {
				int dist =  (Math.abs(ni - i) + Math.abs(nj - j)) - 1;
				if(dist <= 1) break;
				return new Edge(num, board[ni][nj], dist);
			}
		}

		return null;
	}

	static void division() {
		boolean[][] visit = new boolean[N][M];

		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1 && !visit[i][j]) {
					queue.add(new Point(i, j));
					visit[i][j] = true;
					board[i][j] = num;

					while (!queue.isEmpty()) {
						Point p = queue.poll();
						island.add(p);

						for (int d = 0; d < 4; d++) {
							int ni = p.i + dir[d][0];
							int nj = p.j + dir[d][1];

							if (isRange(ni, nj) && board[ni][nj] == 1 && !visit[ni][nj]) {
								visit[ni][nj] = true;
								board[ni][nj] = num;
								queue.add(new Point(ni, nj));
							}
						}
					}

					num++;
				}
			}
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Edge implements Comparable<Edge>{
		int st, end, cost;

		public Edge(int st, int end, int cost) {
			this.st = st;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
		@Override
		public String toString() {
			return "Edge [st=" + st + ", end=" + end + ", cost=" + cost + "]";
		}
		
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		
	}
}
