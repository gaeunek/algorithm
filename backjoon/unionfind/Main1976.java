package unionfind;

import java.util.*;
import java.io.*;

public class Main1976 {
	static int n, m;
	static int[] parent;
	static int[] route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		StringTokenizer st;

		for (int u = 1; u <= n; u++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int v = 1; v <= n; v++) {
				int j = Integer.parseInt(st.nextToken());

				if (j == 1) {
					union(u, v);
				}
			}
		}

		route = new int[m];
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < m; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}

		int start = parent[route[0]];
		for (int i = 1; i < m; i++) {
			if (start != parent[route[i]]) {
				System.out.println("NO");
				return;
			}
		}

		System.out.println("YES");
	}

	public static void union(int a, int b) {
		int findA = find(a);
		int findB = find(b);

		if (findA == findB)
			return;

		if (findA < findB) {
			parent[findB] = findA;
		} else {
			parent[findA] = findB;
		}
	}

	public static int find(int n) {
		if (n == parent[n])
			return n;

		return parent[n] = find(parent[n]);
	}
}
