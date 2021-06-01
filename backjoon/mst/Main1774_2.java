package mst;

import java.util.*;
import java.io.*;

public class Main1774_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Position[] pos = new Position[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pos[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int[] parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			parent[find(b, parent)] = find(a, parent);
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (i != j && find(i, parent) != find(j, parent))
					pq.add(new Edge(i, j, getDistance(pos[i].x, pos[i].y, pos[j].x, pos[j].y)));
			}
		}

		double answer = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int findA = find(e.e1, parent);
			int findB = find(e.e2, parent);

			if (findA != findB) {
				parent[findB] = findA;
				answer += e.cost;
			}
		}

		System.out.printf("%.2f", answer);
	}

	static int find(int n, int[] parent) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n], parent);
	}

	public static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	static class Edge implements Comparable<Edge> {
		int e1, e2;
		double cost;

		public Edge(int e1, int e2, double cost) {
			this.e1 = e1;
			this.e2 = e2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.cost < o.cost)
				return -1;
			return 1;
		}

		@Override
		public String toString() {
			return "Edge [e1=" + e1 + ", e2=" + e2 + ", cost=" + cost + "]";
		}

	}

	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
