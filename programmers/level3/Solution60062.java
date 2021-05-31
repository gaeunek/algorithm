package level3;

import java.util.*;

// �ܺ� ����
class Solution60062 {
	static Set<ArrayList<Integer>> set;
	static ArrayList<String> list;

	public int solution(int n, int[] weak, int[] dist) {
		int answer = Integer.MAX_VALUE;
		int index = 0;


		/* weak �迭�� weak.length ��ŭ shift�� circleWeak�迭�� ����� */
		int[] circleWeak = new int[weak.length * 2 - 1];

		for (int i = 0; i < weak.length; i++) {
			circleWeak[i] = weak[i];
		}

		int a = weak[weak.length - 1], b = weak[index];

		for (int i = weak.length; i < circleWeak.length; i++) {
			int c = b - a < 0 ? n + b - a : b - a;
			circleWeak[i] = circleWeak[i - 1] + c;
			a = b;
			b = weak[++index];
		}

		ArrayList<int[]> circle = new ArrayList<>();
		for (int i = 0; i < weak.length; i++) {
			index = 0;
			int[] temp = new int[weak.length];
			for (int j = i; j < i + weak.length; j++) {
				temp[index++] = circleWeak[j];
			}
			
			circle.add(temp);
		}
		
		/* dist�� ���� �� �ִ� ��� ������ �����. */
		set = new HashSet<>();
		list = new ArrayList<>();
		boolean[] used = new boolean[dist.length];
		comb(dist, used);
		
		
		ArrayList<ArrayList<Integer>> set_to_list = new ArrayList<>(set);
		
		/* circle�� ����� �迭�� dist ���յ�� �ּڰ��� ã�´�. */
		for (int i = 0; i < circle.size(); i++) {
			for (int j = 0; j < set_to_list.size(); j++) {
				int result = check(set_to_list.get(j), circle.get(i));
				
				if(result != -1) {
					answer = Math.min(answer, result);
				}
			}
		}

		return answer == Integer.MAX_VALUE ? -1 : answer;
	}
	
	/* �ּڰ� ã�� �޼��� */
	static public int check(ArrayList<Integer> dist_list, int[] circle) {
		int index = 0, point = 0, suc = 0; // ���� ģ�� ��, ��� ���� ���� ���� ����
		
		for (int i = 0; i < circle.length; i++) {
			point = circle[i];
			if((suc == 0 || point > suc) && index < dist_list.size()) {
				suc = point + dist_list.get(index++);
			}
		}
		
		return point <= suc ? index : -1;
	}

	/* ���� �޼��� */
	static public void comb(int[] dist, boolean[] used) {
		if (list.size() != 0) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for (String s : list) {
				int num = Integer.parseInt(s);
				tmp.add(num);
			}

			set.add(tmp);
		}

		for (int i = 0; i < dist.length; i++) {
			if (!used[i]) {
				String s = String.valueOf(dist[i]);
				used[i] = true;
				list.add(s);
				comb(dist, used);
				list.remove(s);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Solution60062 sol = new Solution60062();
		System.out.println(sol.solution(12, new int[] { 1, 5, 6, 10 }, new int[] { 1, 2, 3, 4 }));
		System.out.println(sol.solution(200, new int[] {0, 100}, new int[] {1, 1}));
	}
}
