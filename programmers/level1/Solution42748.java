package level1;

import java.util.ArrayList;
import java.util.Collections;

//K번째 수
public class Solution42748 {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int c = 0; c < commands.length; c++) {
			for (int i = commands[c][0]-1; i <commands[c][1]; i++) {
				list.add(array[i]);
			}
			
			Collections.sort(list);
			answer[c] = list.get(commands[c][2]-1);
		}
		
		return answer;
	}
}
