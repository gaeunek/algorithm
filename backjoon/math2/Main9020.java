package math2;

import java.util.*;
public class Main9020 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		int[] arr = new int[10001];
		for (int i = 0; i < tc; i++) {
			int n = sc.nextInt();
			int[] ans = new int[2];
			ans[0] = 0;
			ans[1] = Integer.MAX_VALUE;
			
			for (int j = 2; j <= n; j++) {
				for (int k = 2; k * j <= n; k++) {
					if(arr[j * k] == 0) arr[j * k] = 1;
				}
			}
			
			for (int j = 2; j <= n; j++) {
				if(arr[j] == 1 || arr[n - j] == 1) continue;
				
				if(Math.abs(ans[0]-ans[1]) > Math.abs(j-(n - j))) {
					ans[0] = j;
					ans[1] = n - j;
				}
			}
			
			Arrays.sort(ans);
			System.out.println(ans[0]+" "+ans[1]);
		}
		
	}
	
}
