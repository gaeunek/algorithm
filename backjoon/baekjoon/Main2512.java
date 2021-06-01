package baekjoon;

import java.util.*;
// 예산
public class Main2512 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int N = sc.nextInt();
		int[] city = new int[N];
		
		for (int i = 0; i < N; i++)
			city[i] = sc.nextInt();
		
		int M = sc.nextInt();
		
		Arrays.sort(city);
		
		int start = 0;
		int end = city[N-1];
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			
			for (int i : city) {
				if(i > mid)
					sum += mid;
				else
					sum += i;
			}
			
			if(sum > M)
				end = mid - 1;
			else {
				start = mid + 1;
				answer = mid;
			}
		}
		
		System.out.println(answer);
	}
}
