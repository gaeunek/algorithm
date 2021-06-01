package mst;

import java.io.*;
import java.util.*;

public class Main2887 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 행성의 개수

		Point[] pos = new Point[N];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			pos[i] = new Point(i, x, y, z);
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		Arrays.sort(pos, (p1, p2) -> p1.x - p2.x); // x좌표 기준으로 정렬한다
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(pos[i].x - pos[i + 1].x);

			pq.add(new Edge(pos[i].num, pos[i + 1].num, cost));
		}

		Arrays.sort(pos, (p1, p2) -> p1.y - p2.y); // x좌표 기준으로 정렬한다
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(pos[i].y - pos[i + 1].y);

			pq.add(new Edge(pos[i].num, pos[i + 1].num, cost));
		}

		Arrays.sort(pos, (p1, p2) -> p1.z - p2.z); // x좌표 기준으로 정렬한다
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(pos[i].z - pos[i + 1].z);

			pq.add(new Edge(pos[i].num, pos[i + 1].num, cost));
		}

		parent = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}


		int answer = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			int findA = find(e.st);
			int findB = find(e.end);
			
			if (findA != findB) {
				parent[findB] = findA;
				answer += e.cost;
			}
		}

		System.out.println(answer);
	}

	static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

	static class Edge implements Comparable<Edge> {
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
	}

	static class Point {
		int num, x, y, z;

		public Point(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
