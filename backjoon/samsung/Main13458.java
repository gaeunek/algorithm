package samsung;

import java.io.*;
import java.util.*;

public class Main13458 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long answer = 0;
		for (int i = 0; i < N; i++) {
			arr[i] -= B;
			answer++;
			
			if(arr[i] < 0) {
				continue;
			}
			
			answer += arr[i] / C + (arr[i] % C == 0 ? 0 : 1);
		}
		
		System.out.println(answer);
	}
}
