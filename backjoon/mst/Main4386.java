package mst;

import java.io.*;
import java.util.*;

public class Main4386 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		double[][] location = new double[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			location[i] = new double[] { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()) };
		}

		List<Edge> line = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				line.add(new Edge(i, j, getDistance(location[i], location[j])));
			}
		}

		Collections.sort(line);

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		double answer = 0;

		for (int i = 0; i < line.size(); i++) {
			Edge edge = line.get(i);

			int a = edge.e1;
			int b = edge.e2;

			if (union(a, b)) {
				answer += edge.cost;
			}
		}

		System.out.println(Math.round(answer * 100) / 100.0);
	}

	public static boolean union(int a, int b) {
		int findA = find(a);
		int findB = find(b);

		if (findA != findB) {
			parent[findB] = findA;
			return true;
		}

		return false;
	}

	public static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

	public static class Edge implements Comparable<Edge> {
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
	}

	public static double getDistance(double[] a, double[] b) {
		return Math.sqrt(Math.pow(Math.abs(a[0] - b[0]), 2) + Math.pow(Math.abs(a[1] - b[1]), 2));
	}
}
