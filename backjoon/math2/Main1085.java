package math2;

import java.util.*;

public class Main1085 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();

		int minHeight = Math.min(h - y, y - 0);
		int minWidth = Math.min(w - x, x - 0);
		
		System.out.println(Math.min(minHeight, minWidth));
	}
}