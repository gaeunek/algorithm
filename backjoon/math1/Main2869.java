package math1;

import java.util.*;

public class Main2869 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int v = sc.nextInt();
		
		// a-b가 수열의 계차
		int c = a - b;
		
		System.out.println(v%c == 0 ? v/c-1 : v/c+1);
	}
}