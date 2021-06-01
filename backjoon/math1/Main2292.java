package math1;

import java.util.*;
public class Main2292 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		int num = 1, seq = 0;
		
		while(true) {
			int res = num + 6 * seq;
			
			
			if(res >= n) {
				System.out.println(seq + 1);
				break;
			}
			
			num += 6 * seq;
			seq++;
		}
	}
}
