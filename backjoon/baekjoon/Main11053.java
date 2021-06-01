package baekjoon;

import java.util.*;
//가장 긴 증가하는 부분 수열
public class Main11053 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		int[] count = new int[N];
		for (int i = 0; i < N; i++) {
			int max = 1;
			for (int j = 0; j < i; j++) {
				if(nums[j] < nums[i]) {
					max = Math.max(max, count[j] + 1);
				}
			}
			count[i] = max;
		}
		
		int answer = 0;
		for(int i : count) answer = Math.max(answer, i);
		
		System.out.println(answer);
	}
}
