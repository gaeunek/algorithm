package kakao;

import java.util.*;

class Solution64064_2 {
	static Set<List<String>> set;
	public int solution(String[] user_id, String[] banned_id) {
		set = new HashSet<>();
		comb(user_id, banned_id, new boolean[user_id.length], 0);
		return set.size();
	}

	public static void comb(String[] user_id, String[] banned_id, boolean[] used, int idx) {
		if(idx == banned_id.length) {
			List<String> result = new ArrayList<>();
			for (int i = 0; i < used.length; i++) {
				if(used[i]) result.add(user_id[i]);
			}
			
			set.add(result);
			return;
		}
		
		for (int i = 0; i < user_id.length; i++) {
			String b_id = banned_id[idx];
			String u_id = user_id[i];

			if (b_id.length() != u_id.length() || used[i])
				continue;

			boolean flag = true;
			for (int j = 0; j < b_id.length(); j++) {
				if (b_id.charAt(j) != '*' && b_id.charAt(j) != u_id.charAt(j)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				used[i] = true;
				comb(user_id, banned_id, used, idx + 1);
				used[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Solution64064_2 sol = new Solution64064_2();
		System.out.println(sol.solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "abc1**" }));
		System.out.println(sol.solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "*rodo", "*rodo", "******" }));
	}
}
