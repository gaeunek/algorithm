package level2;
//오픈채팅방
import java.util.*;
class Solution42888 {
	public String[] solution(String[] record) {
		Map<String, String> map = new HashMap<>(); //key:ID, value:닉네임
		List<Records> list = new ArrayList<>();
		
		for (int i = 0; i < record.length; i++) {
			String[] input = record[i].split(" "); //활동, 아이디, 닉네임
			if(!input[0].equals("Leave")) {
				map.put(input[1], input[2]);
				if(input[0].equals("Change")) continue; 
			}
			
			list.add(new Records(input[1], input[0]));
		}

		String[] answer = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Records now = list.get(i);
			answer[i] = map.get(now.id);
			if(now.active.equals("Enter")) answer[i] += "님이 들어왔습니다.";
			else answer[i] += "님이 나갔습니다.";
		}
		
		return answer;
	}
	
	static class Records {
		String id, active;
		
		public Records(String id, String active) {
			this.id = id;
			this.active = active;
		}
	}
}
