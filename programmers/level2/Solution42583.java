package level2;

import java.util.*;
//다리를 지나는 트럭
public class Solution42583 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Queue<Integer> pass = new LinkedList<Integer>();

		int i = 0, t_weight = 0, cnt = 0;
		do {
			if (i < truck_weights.length && t_weight + truck_weights[i] <= weight) {
				queue.add(truck_weights[i]);
				t_weight += truck_weights[i];
				i++;
				cnt++;
			} else {
				queue.add(0);
				cnt++;
			}

			if (cnt >= bridge_length && !queue.isEmpty()) {
				int tmp = queue.poll();
				
				if(tmp != 0) {
					t_weight -= tmp;
					pass.add(tmp);
				}
			}
		} while (pass.size() < truck_weights.length);

		return cnt+1;
	}

	public static void main(String[] args) {
		Solution42583 sol = new Solution42583();
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = { 10,10,10,10,10,10,10,10,10,10 };
		System.out.println(sol.solution(bridge_length, weight, truck_weights));
	}
}
