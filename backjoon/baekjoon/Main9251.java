package baekjoon;

import java.io.*;
import java.util.*;
//LCS 이런개같은
public class Main9251 {
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s1 = br.readLine();
		String s2 = br.readLine();
		List<Character> list1 = new ArrayList<>();
		List<Character> list2 = new ArrayList<>();
		
		for (int i = 0; i < s1.length(); i++) {
			if(s2.contains(String.valueOf(s1.charAt(i)))){
				list1.add(s1.charAt(i));
			}
		}
		
		for (int i = 0; i < s2.length(); i++) {
			if(s1.contains(String.valueOf(s2.charAt(i)))){
				list2.add(s2.charAt(i));
			}
		}
		
		System.out.println(list1);
		System.out.println(list2);
		
		
		answer = 0;
		
//		func(s1, s2);
//		func(s2, s1);
		System.out.println(answer);
	}
	
	static void func(String s1, String s2) {
		for (int k = 0; k < s1.length(); k++) {
			int st = 0, cnt = 0; 
			StringBuilder sb = new StringBuilder();
			for (int i = k; i < s1.length(); i++) {
				for (int j = st; j < s2.length(); j++) {
					if(s1.charAt(i) == s2.charAt(j)) {
						sb.append(s1.charAt(i));
						cnt++;
						st = j+1;
						break;
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
	}
}
