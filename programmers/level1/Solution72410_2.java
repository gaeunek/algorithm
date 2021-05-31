package level1;

class Solution72410_2 {
	public String solution(String new_id) {
		// 1단계
		new_id = new_id.toLowerCase();

		// 2단계
		new_id = new_id.replaceAll("[^-_.a-z0-9]", "");

		// 3단계
		new_id = new_id.replaceAll("[.]{2,}", ".");

		// 4단계
		new_id = new_id.replaceAll("^[.]|[.]$", "");

		// 5단계
		if (new_id.length() == 0)
			new_id += "a";

		// 6단계
		if (new_id.length() >= 16)
			new_id = new_id.substring(0, 15);
		
		new_id = new_id.replaceAll("[.]$", "");
		
		if (new_id.length() <= 2) {

			char last = new_id.charAt(new_id.length() - 1);

			while (new_id.length() <= 2) {
				new_id += last;
			}
		}

		return new_id;
	}
	
	public static void main(String[] args) {
		Solution72410_2 sol = new Solution72410_2();
		System.out.println(sol.solution("=.="));
	}
}