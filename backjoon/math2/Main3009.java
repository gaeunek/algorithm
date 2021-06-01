package math2;

import java.util.*;

public class Main3009 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Map<Integer, Integer> x = new HashMap<>();
		Map<Integer, Integer> y = new HashMap<>();
		
		for (int i = 0; i < 3; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			x.put(n, x.getOrDefault(n, 0)+1);
			y.put(m, y.getOrDefault(m, 0)+1);
		}
		
		Iterator<Integer> it1 = x.keySet().iterator();
		Iterator<Integer> it2 = y.keySet().iterator();
		
		int[] ans = new int[2];
		
		while(it1.hasNext()) {
			int keyX = it1.next();
			int keyY = it2.next();
			
			if(x.get(keyX) == 1) ans[0] = keyX;
			if(y.get(keyY) == 1) ans[1] = keyY;
		}
		
		System.out.println(ans[0]+" "+ans[1]);
		
	}
}
