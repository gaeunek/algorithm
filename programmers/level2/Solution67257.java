package level2;

import java.util.*;
//수식최대화
class Solution67257 {
	static List<String> exp, oper, result;
	static boolean[] used;
	static long answer;
	public long solution(String expression) {
		exp = new ArrayList<>();
		oper = new ArrayList<>();
		
		char[] tmp = expression.toCharArray();
		String str = "", s;
		for (int i = 0; i < tmp.length; i++) {
			if(tmp[i] == '-' || tmp[i] == '+' || tmp[i] == '*') {
				s = String.valueOf(tmp[i]);
				exp.add(str);
				exp.add(s);
				if(!oper.contains(s)) oper.add(s);
				str = "";
				continue;
			}
			
			str += tmp[i];
			if(i == tmp.length-1) exp.add(str);
		}
		
		result = new ArrayList<>();
		used = new boolean[oper.size()];
		answer = 0;
		priorityOper();
		
		return answer;
	}
	
	static void priorityOper(){
		if(result.size() == oper.size()) {
			long res = calculation();
			answer = res > answer ? res : answer;
			return;
		}
		
		for (int i = 0; i < oper.size(); i++) {
			if(!used[i]) {
				used[i] = true;
				result.add(oper.get(i));
				priorityOper();
				result.remove(oper.get(i));
				used[i] = false;
			}
		}
	}
	
	static long calculation() {
		List<String> temp = new ArrayList<>();
		for (String str : exp) temp.add(str);
		
		for (int i = 0; i < result.size(); i++) {
			String op = result.get(i);
			for (int j = 0; j < temp.size(); j++) {
				if(temp.get(j).equals(op)) {
					long a = Long.parseLong(temp.get(j-1));
					long b = Long.parseLong(temp.get(j+1));
					a = op(a, b, op);
					temp.add(j-1, String.valueOf(a));
					for (int k = 0; k < 3; k++) temp.remove(j);
					j = 0;
				}
			}
		}
		
		return Math.abs(Long.parseLong(temp.get(0)));
	}
	
	static long op(long a, long b, String op) {
		switch (op) {
		case "+":
			return a += b;
		case "-":
			return a -= b;
		default:
			return a *= b;
		}
	}
	
	public static void main(String[] args) {
		long a = 1;
		for(int i=0; i<63; i++) {
			a*=2;
		}
		
		System.out.println(a-1);
		Solution67257 sol = new Solution67257();
		System.out.println(sol.solution("9223372036854775807*1"));
	}
}
