package backtracking;

import java.io.*;
import java.util.*;
public class Main15650 {
	static StringBuilder sb;
	static int[] result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		result = new int[m];
		permutation(new boolean[n+1], 0, m, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void permutation(boolean[] used, int st, int m, int index) {
		if(index == m) {
			for (int i = 0; i < m; i++) {
				sb.append(result[i]+" ");
			}
			
			sb.append("\n");
			return;
		}
		
		for (int i = st; i < used.length; i++) {
			if(i != 0 && !used[i]) {
				used[i] = true;
				result[index] = i;
				permutation(used, i+1, m, index + 1);
				result[index] = 0;
				used[i] = false;
			}
		}
	}
}
