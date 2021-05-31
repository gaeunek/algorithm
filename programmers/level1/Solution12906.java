package level1;
//같은 숫자는 싫어
import java.util.*;

public class Solution12906 {
    public int[] solution(int []arr) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int prefix = arr[0];
        list.add(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
			if(prefix != arr[i]) {
				prefix = arr[i];
				list.add(prefix);
			}
		}
        
        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
