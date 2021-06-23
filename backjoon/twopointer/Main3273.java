package twopointer;

import java.io.*;
import java.util.*;

public class Main3273 {
	static int[] nums = new int[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		while (n-- > 0) {
			nums[Integer.parseInt(st.nextToken())]++;
		}

		int x = Integer.parseInt(br.readLine());

		int answer = 0;

		for (int i = 0; i <= 1000000; i++) {
			if (x > i && nums[i] > 0) {
				nums[i]--;
				if (nums[x - i] > 0) {
					nums[x - i]--;
					answer++;
				} else
					nums[i]++;
			}
		}

		System.out.println(answer);
	}
}