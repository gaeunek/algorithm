package level2;
//위장
import java.util.HashMap;
import java.util.Iterator;

public class Solution42578 {
	public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < clothes.length; i++) {
        	map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0)+1);
		}
        
        Iterator<Integer> values = map.values().iterator();
        
        while(values.hasNext()) {
        	answer *= (values.next()+1);
        }
        
        return answer-1;
    }
}
