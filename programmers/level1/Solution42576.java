package level1;
//완주하지 못한 선수

import java.util.HashMap;
import java.util.Iterator;
public class Solution42576 {

	public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        
        for (String p : participant) {
        	hashMap.put(p, hashMap.getOrDefault(p, 0) + 1); //p가 hashMap에 존재하면 p 넣고 없으면 value를 +1 증가
        }
        
        for (String c : completion) {
			hashMap.put(c, hashMap.getOrDefault(c, 0) - 1); //위와 반대로 c가 hashMap에 존재하면 value -1 해줌
		}
        
        Iterator<String> keys = hashMap.keySet().iterator();
        
        while(keys.hasNext()) {
        	String key = keys.next();
        	if(hashMap.get(key) == 1)
        		return key;
        }
        
        return null;
    }
}
