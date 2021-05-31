package level2;

import java.util.*;
//스킬트리
class Solution49993 {
	public int solution(String skill, String[] skill_trees) {
		int answer = 0;
		List<Character> skills = new ArrayList<Character>();

		for (char ch : skill.toCharArray())
			skills.add(ch);

		for (int i = 0; i < skill_trees.length; i++) {
			char[] tmp = skill_trees[i].toCharArray();
			int idx = 0;
			boolean flag = true;
			for (int j = 0; j < tmp.length; j++) {
				if ((skills.contains(tmp[j]) && skills.get(idx) == tmp[j])) {
					idx++;
					continue;
				} else if(skills.contains(tmp[j]) && skills.get(idx) != tmp[j]){
					flag = false;
					break;
				}
			}

			if (flag)
				answer++;
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution49993 sol = new Solution49993();
		System.out.println(sol.solution("CBD", new String[] { "BACDE", "CBADF", "AECB", "BDA" }));
	}
}
