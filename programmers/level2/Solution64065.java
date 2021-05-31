package level2;
import java.util.*;
//튜플
class Solution64065 {
	public int[] solution(String s) {
		List<Integer> list = new ArrayList<Integer>();
		String[] input = s.split("},");
		for (int i = 0; i < input.length; i++) {
			input[i] = input[i].replace("}", "").replace("{", "");
		}
		
		Arrays.sort(input, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		
		for (int i = 0; i < input.length; i++) {
			String[] tmp = input[i].split(",");
			int cnt = tmp.length - 1;
			for (int j = 0; j < tmp.length; j++) {
				int n = Integer.parseInt(tmp[j]);
				if((cnt == 0 &&  list.contains(n)) || !list.contains(n)) list.add(n);
				else if(list.contains(n)) cnt--;
			}
		}
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Solution64065 sol = new Solution64065();
		System.out.println(Arrays.toString(sol.solution("{{2},{2,1},{2,1,2},{2,1,2,2}}")));
		System.out.println(Arrays.toString(sol.solution("{{20,111},{111}}")));
	}
}
