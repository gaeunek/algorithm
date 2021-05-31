package level3;

import java.util.*;

//단속카메라
class Solution42884 {
	public int solution(int[][] routes) {
		int answer = 1;
		
        Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

        List<int[]> list = new ArrayList<>();
        
        int st = 0, end = 0;
        for (int i = 0; i < routes.length; i++) {
			int[] route = routes[i];
			
			if(list.isEmpty()) {
				st = route[0];
				end = route[1];
				continue;
			}
			
			for (int j = 0; j < list.size(); j++) {
				st = list.get(0)[0];
				end = list.get(0)[1];
				boolean flag = false;
				if(st < route[0]) {
					st = route[0];
					flag = true;
				}
				
				if(end > route[1]) {
					end = route[1];
					flag = true;
				}
				
				if(!flag) {
					list.add(new int[] {st, end});
					list.add(route);
					answer++;
				}
			}
		}
        System.out.println(answer);
        return answer;
    }

	public static void main(String[] args) {
		Solution42884 sol = new Solution42884();
		sol.solution(new int[][] { { 0, 12 }, { 1, 12 }, { 2, 12 }, { 3, 12 }, { 5, 6 }, { 6, 12 }, { 10, 12 } });
		sol.solution(new int[][] { { -2, -1 }, { 1, 2 }, { -3, 0 } });// 2
		sol.solution(new int[][] { { 0, 0 }, });// 1
		sol.solution(new int[][] { { 0, 1 }, { 0, 1 }, { 1, 2 } });// 1
		sol.solution(new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 }, { 6, 7 } });// 4
		sol.solution(new int[][] { { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } });// 2
//		System.out.println(sol.solution(new int[][] { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }));// 2
		sol.solution(new int[][] { { -20, 15 }, { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } });// 2
	}
}
