package unionfind;

import java.util.*;
import java.io.*;

public class Main4195 {
	static int tc, f, max;
	static Map<String, Integer> map;
	static int[] parent, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			f = Integer.parseInt(br.readLine());
			max = f * 2;
			parent = new int[max];
			count = new int[max];
			map = new HashMap<>();

			int index = 0;

			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
				count[i] = 1;
			}

			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				String a = st.nextToken();
				String b = st.nextToken();

				if (!map.containsKey(a))
					map.put(a, index++);
				if (!map.containsKey(b))
					map.put(b, index++);

				sb.append(union(map.get(a), map.get(b))).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	public static int union(int a, int b) {
		int findA = find(a);
		int findB = find(b);

		if (findA == findB)
			return count[findA];

		if (findA < findB) {
			parent[findB] = findA;
			count[findA] += count[findB];
			return count[findA];
		} else {
			parent[findA] = findB;
			count[findB] += count[findA];
			return count[findB];
		}
	}

	public static int find(int n) {
		if (parent[n] == n)
			return n;

		return parent[n] = find(parent[n]);
	}
}
