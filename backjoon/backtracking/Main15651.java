package backtracking;

import java.util.*;
import java.io.*;

public class Main15651 {
	static int N, M;
	static int[] res;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		res = new int[M];
		dfs(0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void dfs(int index) {
		if (index == M) {
			for (int i : res) {
				if(i != 0) sb.append(i).append(" ");
			}

			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			res[index] = i + 1;
			dfs(index + 1);
		}
	}
}
