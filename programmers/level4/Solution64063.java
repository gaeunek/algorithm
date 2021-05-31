package level4;

import java.util.*;

// 호텔 방 배정
class Solution64063 {
	static Map<Long, Long> map;
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];

		map = new HashMap<>();
		
		for (int i = 0; i < room_number.length; i++) {
			answer[i] = findRoomNumber(room_number[i]);
		}
		
		return answer;
	}
	
	static long findRoomNumber(long number) {
		if(!map.containsKey(number)) {
			map.put(number, number + 1);
			return number;
		}
		
		long nextRoom = map.get(number);
		long newRoom = findRoomNumber(nextRoom);
		map.put(number, newRoom);
		return newRoom;
	}

	public static void main(String[] args) {
		Solution64063 sol = new Solution64063();
		System.out.println(Arrays.toString(sol.solution(10, new long[] { 1, 3, 4, 1, 3, 1 })));
		System.out.println(Arrays.toString(sol.solution(10, new long[] { 1, 1, 1, 1, 5, 5, 5, 5, 3 })));
	}
}
