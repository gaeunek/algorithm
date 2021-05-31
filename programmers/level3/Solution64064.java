package level3;

import java.util.*;
// 불량 사용자
class Solution64064 {
	static boolean[] used;
	static Set<List<String>> set;

	public int solution(String[] user_id, String[] banned_id) {
		used = new boolean[user_id.length];
		set = new HashSet<>();
		dfs(user_id, banned_id, 0);
		System.out.println(set);
		return set.size();
	}

	static void dfs(String[] user_id, String[] banned_id, int idx) {
		if (idx == banned_id.length) {
			List<String> result = new ArrayList<>();
			for(int i = 0; i < used.length; i++) {
				if(used[i]) result.add(user_id[i]);
			}
			Collections.sort(result);
			set.add(result);
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			if (used[i])
				continue;

			String u_id = user_id[i];
			String b_id = banned_id[idx];

			if (b_id.length() != u_id.length())
				continue;

			int same = 0;
			for (int j = 0; j < b_id.length(); j++) {
				if (u_id.charAt(j) == b_id.charAt(j) || b_id.charAt(j) == '*')
					same++;
			}

			if (same == b_id.length()) {
				used[i] = true;
				dfs(user_id, banned_id, idx+1);
				used[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Solution64064 sol = new Solution64064();
//		System.out.println(sol.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "abc1**"}));
		System.out.println(sol.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"}));
	}
}
