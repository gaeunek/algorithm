package baekjoon;

import java.io.*;
import java.util.*;
public class Main16637 {
	// dfs와 백트래킹
	static List<Character> opers;
	static List<Integer> nums;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[] c = br.readLine().toCharArray();
		
		opers = new LinkedList<>();
		nums = new LinkedList<>();
		
		for (int i = 0; i < c.length; i++) {
			if(c[i] == '+' || c[i] == '-' || c[i] == '*') opers.add(c[i]);
			else nums.add(c[i]-'0');
		}
		
		max = 0;
		backTracking();
	}
	
	public static void backTracking() {
		
	}
	
	public static int calculator(int a, int b, char c) {
		switch (c) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		default:
			return a * b;
		}
	}
}
