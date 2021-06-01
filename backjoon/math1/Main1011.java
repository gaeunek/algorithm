package math1;

import java.util.*;

public class Main1011 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		for (int t = 0; t < tc; t++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			int distance = y - x;
			
			if(distance <= 2) {
				System.out.println(distance);
				continue;
			}
			
			int answer = 2, cnt = 2;
			for (int i = 2; answer < distance; i+=2) {
				answer += i;
				cnt++;
			}

			System.out.println(cnt);
		}
	}
}
