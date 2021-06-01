package math2;

import java.util.*;

public class Main4948 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] arr = new int[2 * 123456 + 1];
		int n;
		
		while(true) {
			n = sc.nextInt();
			if(n == 0) break;
			
			for (int i = 2; i <= 2 * n; i++) {
				for (int j = 2; i * j <= 2 * n; j++) {
					if (arr[i * j] == 0)
						arr[i * j] = 1;
				}
			}
			
			int answer = 0;
			for (int i = n+1; i <= 2 * n; i++) {
				if (i >= 2 && arr[i] == 0)
					answer++;
			}
			
			System.out.println(answer);
		}
	}
}
