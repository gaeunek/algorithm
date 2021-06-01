package math2;

import java.util.*;

public class Main1929 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] arr = new int[m+1];
		
		for (int i = 2; i <= m; i++) {
			for (int j = 2; i*j <= m; j++) {
				arr[i*j] = 1;
			}
		}
		
		for (int i = n; i <= m; i++) {
			if(i >= 2 && arr[i] == 0) System.out.println(i);
		}
	}
}