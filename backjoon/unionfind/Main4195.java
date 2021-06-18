package unionfind;

import java.util.*;
import java.io.*;

public class Main4195 {
	static int tc, f;
	static Map<String, String> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			f = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				String a = st.nextToken();
				String b = st.nextToken();

				if (!map.containsKey(a))
					map.put(a, a);
				if (!map.containsKey(b))
					map.put(b, b);

				union(a, b);

				sb.append(check(a, b)).append("\n");
			}

			System.out.println(sb.toString());
		}
	}

	public static int check(String a, String b) {
		Iterator<String> it = map.values().iterator();
		int count = 0;
		
		while (it.hasNext()) {
			String value = it.next();

			if (value.equals(a) || value.equals(b))
				count++;
		}

		return count;
	}

	public static void union(String a, String b) {
		String findA = find(a);
		String findB = find(b);

		if (findA.equals(findB))
			return;

		if (findA.compareTo(findB) < 1) {
			map.put(findB, findA);
		} else
			map.put(findA, findB);
	}

	public static String find(String s) {
		if (map.get(s) == s)
			return s;

		return map.put(s, map.get(s));
	}
}
