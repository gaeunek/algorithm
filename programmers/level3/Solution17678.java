package level3;

import java.util.*;
//[1차] 셔틀버스
class Solution17678 {
	public String solution(int n, int t, int m, String[] timetable) {
        int[] times = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
        	String[] tmp = timetable[i].split(":");
        	times[i] = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
		}
        
        Arrays.sort(times);
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int time : times) {
			queue.add(time);
		}
        
        int answer = 0;
        int max = m;
        int arrived = 540;
        for (int i = 0; i < n; i++, arrived+=t, max = m) {
        	if(queue.isEmpty()) {
        		answer = arrived;
        		continue;
        	}
        	
			while(queue.peek() <= arrived) {
				int last = queue.poll();
				max--;
				
				/* 막차시간이고 막차가 꽉찼다. */
				if(i == n-1 && max == 0) {
					answer = last - 1;
					break;
				}
				
				/* 막차시간이고 남은 승객이 없다. */
				if(queue.isEmpty() && max != 0 && i == n-1) {
					answer = arrived;
					break;
				}
				
				/* 최대인원을 태웠다. */
				if(max == 0) break;
			}
		}
        
        /* 승객이 남아있는데 막차는 끝났다. */
        if(answer == 0 && !queue.isEmpty()) answer = arrived - t;
        
        int hour = answer / 60;
        int minute = answer % 60;
        
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }
	
	public static void main(String[] args) {
		Solution17678 sol = new Solution17678();
		System.out.println(sol.solution(2, 10, 2, new String[] {"09:10", "09:09", "08:00"}));
	}
}
