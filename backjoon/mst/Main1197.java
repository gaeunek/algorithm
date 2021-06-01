package mst;

import java.util.*;
import java.io.*;

// prim 알고리즘으로 풀면 터진다!!

public class Main1197 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수

		int[][] edge = new int[E][3];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edge[i] = new int[] { a, b, c };
		}

		Arrays.sort(edge, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] == o2[2] && o1[0] == o2[0])
					return o1[1] - o2[1];
				else if (o1[2] == o2[2])
					return o1[0] - o2[0];
				return o1[2] - o2[2];
			}

		});

		parent = new int[V + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		boolean[] used = new boolean[V + 1];
		int answer = 0;
		
		for (int i = 0; i < edge.length; i++) {
			int a = find(edge[i][0]);
			int b = find(edge[i][1]);
			
			if(a != b) {
				union(a, b);
				answer += edge[i][2];
			}
		}
		
		System.out.println(answer);
	}

	public static void union(int a, int b) {
		parent[b] = parent[a];
	}

	public static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

}
