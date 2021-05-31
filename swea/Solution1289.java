package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1289 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			char[] input = br.readLine().toCharArray();
			int[] memory = new int[input.length];
			
			int count = 0;
			for (int i = 0; i < input.length; i++) {
				int after = input[i] - '0';
				if (after != memory[i]) {
					count++;
					for (int j = i; j < memory.length; j++) {
						memory[j] = after;
					}
				}
			}
			
			System.out.println("#"+ tc + " "+count);
		}
	}
}
