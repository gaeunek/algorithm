package kakao;

import java.util.*;

class Solution64064 {
	static Set<ArrayList<String>> set;

	public int solution(String[] user_id, String[] banned_id) {
		set = new HashSet<>();
		List<String>[] list = new ArrayList[banned_id.length];

		for (int i = 0; i < banned_id.length; i++) {
			String b_id = banned_id[i];
			list[i] = new ArrayList<>();

			for (int j = 0; j < user_id.length; j++) {
				String u_id = user_id[j];
				if (b_id.length() != u_id.length())
					continue;

				boolean flag = true;
				for (int k = 0; k < b_id.length(); k++) {
					if (b_id.charAt(k) == '*')
						continue;

					if (b_id.charAt(k) != u_id.charAt(k)) {
						flag = false;
						break;
					}
				}

				if (flag) {
					list[i].add(u_id);
				}
			}
		}
		
		comb(list, 0, new ArrayList<>());
		return set.size();
	}

	public static void comb(List<String>[] list, int index, ArrayList<String> result) {
		if (index == list.length) {
			if(result.size() < list.length) return;
			
			Collections.sort(result);
			ArrayList<String> tmp = new ArrayList<>();
			for (int i = 0; i < result.size(); i++) {
				tmp.add(result.get(i));
			}
			set.add(tmp);
			return;
		}

		for (int i = 0; i < list[index].size(); i++) {
			String s = list[index].get(i);
			boolean flag = false;
			if(!result.contains(s)) {
				flag = true;
				result.add(s);
			}
			comb(list, index + 1, result);
			
			if(flag)
				result.remove(s);
		}
	}

	public static void main(String[] args) {
		Solution64064 sol = new Solution64064();
		System.out.println(sol.solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "abc1**" }));
		System.out.println(sol.solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "*rodo", "*rodo", "******" }));
	}
}
