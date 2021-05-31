package level2;
//영어 끝말잇기
import java.util.*;
class Solution12981 {
	public int[] solution(int n, String[] words) {
		List<String> list = new ArrayList<String>();
		int cnt = 1, number = 1; //차례와 번호
		char end = 'a';
		for (int i = 0; i < words.length; i++) {
			System.out.println(words[i]+", "+end+", "+number+", "+cnt);
			if(list.isEmpty() || (end == words[i].charAt(0) && !list.contains(words[i]))) {
				list.add(words[i]);
				end = words[i].charAt(words[i].length()-1);
				number++;
			} else if(end != words[i].charAt(0) || list.contains(words[i]))	return new int[] {number, cnt};
			
			if(number == n+1) {
				number = 1;
				cnt++;
			}
		}
		return new int[] {0, 0};
	}
	
	public static void main(String[] args) {
		Solution12981 sol = new Solution12981();
		System.out.println(Arrays.toString(sol.solution(3, new String[] {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
	}
}
