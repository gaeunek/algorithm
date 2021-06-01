package mst;

import java.io.*;
import java.util.*;

public class Main1774 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[][] line = new boolean[N][N];
		int[][] edge = new int[N][2];
		int[] parent = new int[N];
		
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edge[i][0] = Integer.parseInt(st.nextToken());
			edge[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			line[a][b] = true;
			line[b][a] = true;
			
			union(a, b, parent);
		}

		List<Edge> list = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (!line[i][j])
					list.add(new Edge(i, j, getDistance(edge[i], edge[j])));
			}
		}

		Collections.sort(list);
		
		double answer = 0;
		for (int i = 0; i < list.size(); i++) {
			int a = list.get(i).i;
			int b = list.get(i).j;
			
			if(union(a, b, parent)) {
				answer += list.get(i).cost;
			}
		}
		
		System.out.println(String.format("%.2f", answer));
	}
	
	public static boolean union(int a, int b, int[] parent) {
		int findA = find(a, parent);
		int findB = find(b, parent);
		
		if(findA != findB) {
			parent[findB] = findA;
			return true;
		}
		
		return false;
	}
	
	public static int find(int n, int[] parent) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n], parent);
	}

	public static double getDistance(int[] now, int[] next) {
		return Math.sqrt(Math.pow(Math.abs(now[0] - next[0]), 2) + Math.pow(Math.abs(now[1] - next[1]), 2));
	}

	// prim써야함
	static class Edge implements Comparable<Edge>{
		int i, j;
		double cost;

		public Edge(int i, int j, double cost) {
			this.i = i;
			this.j = j;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [i=" + i + ", j=" + j + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Edge o) {
			if(this.cost < o.cost) return -1;
			return 1;
		}
		
	}
}
