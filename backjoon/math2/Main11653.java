package math2;

import java.util.Scanner;

public class Main11653 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int value = 2;
		
		while(value <= n) {
			if(n % value == 0) {
				 n /= value;
				 System.out.println(value);
				 value = 2;
				 continue;
			}
			
			if(n == 1) break;
			
			value++;
		}
	}
}
