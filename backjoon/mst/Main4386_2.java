package mst;

import java.io.*;
import java.util.*;

// 모르겟따
public class Main4386_2 {
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

		double[][] edge = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double dist = getDistance(location[i], location[j]);
				edge[i][j] = dist;
				edge[j][i] = dist;
			}
		}

		double[] result = new double[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.add(new Edge(0, 0));
		Arrays.fill(result, Integer.MAX_VALUE);

		while (!pq.isEmpty()) {
			Edge e = pq.poll();

			result[e.node] = Math.min(e.cost, result[e.node]);

			for (int i = 0; i < N; i++) {
				if (i != e.node && edge[e.node][i] > 0) {
					pq.add(new Edge(i, e.cost + edge[e.node][i]));
				}
			}
		}

		System.out.println(Math.round(result[N - 1] * 100) / 100.0);
	}

	public static class Edge implements Comparable<Edge> {
		int node;
		double cost;

		public Edge(int node, double cost) {
			this.node = node;
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
			return "Edge [node=" + node + ", cost=" + cost + "]";
		}
	}

	public static double getDistance(double[] a, double[] b) {
		return Math.sqrt(Math.pow(Math.abs(a[0] - b[0]), 2) + Math.pow(Math.abs(a[1] - b[1]), 2));
	}
}
