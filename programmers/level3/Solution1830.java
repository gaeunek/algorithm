package level3;

import java.util.*;

// 몰라 개어려워
class Solution1830 {
	static String s, answer;

	public String solution(String sentence) {
		answer = "";
		s = sentence;

		// 1. 공백 존재 X
		if (sentence.contains(" "))
			return "invalid";

		while (!s.equals("")) {
			char c = s.charAt(0);

			if (c >= 'a' && c <= 'z') {
				if (!ruleTwo(0, c))
					return "invaild";
			} else {
				if (!ruleOne(0, s.charAt(1)))
					return "invalid";
			}
		}

		return answer;
	}

	static boolean ruleTwo(int index, char c) {
		int i;
		for (i = index + 1; i < s.length(); i++) {
			char now = s.charAt(i);
			if (now == c)
				break;

			if (now != c && now >= 'a' && now <= 'z') {
				if (ruleOne(i - 1, now)) {
					i--;
					continue;
				} else {
					return false;
				}
			}
			
			answer += now;
		}

		answer += " ";
		if(i == s.length()) s = "";
		else s = s.substring(i, s.length() - 1);
		return true;
	}

	static boolean ruleOne(int index, char c) {
		char front = s.charAt(index);
		int i;
		answer += front;
		
		for (i = index + 1; i < s.length(); i++) {
			char now = s.charAt(i);
			
			if(front >= 'a' && front <= 'z' && now == c) return false;

			if ((now != c && now >= 'a' && now <= 'z') || (front >= 'A' && front <= 'Z' && now >= 'A' && now <= 'Z'))
				break;

			front = now;
			if (now == c)
				continue;
			answer += now;
		}

		answer += " ";
		if(i == s.length()) s = "";
		else s = s.substring(i, s.length() - 1);
		return true;
	}

	public static void main(String[] args) {
		Solution1830 sol = new Solution1830();
//		System.out.println(sol.solution("HaEaLaLaObWORLDb"));
		System.out.println(sol.solution("abHELLObaWORLD"));
	}
}
