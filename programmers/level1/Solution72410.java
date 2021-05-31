package level1;

class Solution72410 {
	public String solution(String new_id) {

		// step 1
		new_id = new_id.toLowerCase();

		char[] tmp = new_id.toCharArray();
		String filter = "";

		// step 2 + 3
		for (int i = 0; i < tmp.length; i++) {
			char c = tmp[i];

			if (!filter.equals("") && filter.charAt(filter.length() - 1) == '.' && c == '.') {
				continue;
			} else {
				if (checkStepTwo(c)) {
					filter += c;
				}
			}
		}

		// step 4
		if (filter.length() != 1 && filter.charAt(0) == '.')
			filter = filter.substring(1, filter.length());
		if (filter.charAt(filter.length() - 1) == '.')
			filter = filter.substring(0, filter.length() - 1);

		// step 5
		if (filter.equals(""))
			filter += "a";

		// step 6
		if (filter.length() >= 16)
			filter = filter.substring(0, 15);
		if (filter.charAt(filter.length() - 1) == '.')
			filter = filter.substring(0, filter.length() - 1);

		// step 7
		if (filter.length() <= 2) {
			tmp = filter.toCharArray();
			char last = filter.charAt(filter.length() - 1);
			while (filter.length() < 3) {
				filter += last;
			}
		}

		return filter;
	}

	static boolean checkStepTwo(char c) {
		if ((c >= 'a' && c <= 'z') || c == '-' || c == '_' || c == '.' || (c - '0' >= 0 && c - '0' < 10))
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solution72410 sol = new Solution72410();
//		System.out.println(sol.solution("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(sol.solution("=.="));
		System.out.println(sol.solution("123_.def"));
	}
}
