package level2;
//단체사진 찍기
import java.util.*;
class Solution1835 {
	static Set<String> set;
	static boolean[] used;
	static List<String> list;
	static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
	public int solution(int n, String[] data) {
		int answer = 0;
		set = new HashSet<>();
		used = new boolean[friends.length];
		list = new ArrayList<>();
		combination();
		Iterator<String> it = set.iterator();
		int c1, c2, res, value;
		
		while(it.hasNext()) {
			String str = it.next();
			boolean flag = false;
			
			for (int i = 0; i < n; i++) {
				flag = false;
				String d = data[i];
				c1 = str.indexOf(d.charAt(0));
				c2 = str.indexOf(d.charAt(2));
				value = d.charAt(d.length()-1) - '0';
				res = Math.abs(c1-c2) - 1;
				if(d.contains("=") && res == value) {
					flag = true;
				}else if(d.contains(">") && res > value) {
					flag = true;
				}else if(d.contains("<") && res < value) {
					flag = true;
				}
				
				if(!flag) break;
			}
			if(flag) answer++; 
		}
		
		return answer;
	}
	
	static void combination() {
		if(list.size() == friends.length) {
			StringBuilder sb = new StringBuilder();
			for (String str : list) sb.append(str);
			set.add(new String(sb));
			return;
		}
		
		for (int i = 0; i < friends.length; i++) {
			if(!used[i]) {
				list.add(friends[i]);
				used[i] = true;
				combination();
				used[i] = false;
				list.remove(friends[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		Solution1835 sol = new Solution1835();
		System.out.println(sol.solution(2, new String[] {"N~F=0", "R~T>2"}));
//		System.out.println(sol.solution(2, new String[] {"M~C<2", "C~M>1"}));
	}
}
