package level3;

import java.util.*;

//여행경로
class Solution43164 {
	static boolean[] used;
	static List<String> result, list;
	static int N;
	
	public String[] solution(String[][] tickets) {
        N = tickets.length;
        used = new boolean[N];
        list = new ArrayList<>();
        result = new ArrayList<>();
        
        dfs(tickets, "ICN");
        
        String[] answer = new String[N+1];
        for (int i = 0; i <= N; i++) {
        	answer[i] = result.get(i);
		}
        
        return answer;
    }
	
	static void dfs(String[][] tickets, String now) {
		if(list.size() == N) {
			List<String> tmp = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				int idx = Integer.parseInt(list.get(i));
				tmp.add(tickets[idx][0]);
				
				if(i == N-1) tmp.add(tickets[idx][1]);
			}
			
			if(result.size() == 0) result = tmp;
			else {
				boolean flag = false;
				for (int i = 1; i <= N; i++) {
					int v = result.get(i).compareTo(tmp.get(i));
					
					if(v > 0) {
						flag = true;
						break;
					} else if(v < 0) break;
				}
				
				if(flag) result = tmp;
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			String next = tickets[i][1];
			
			if(!used[i] && tickets[i][0].equals(now)) {
				String value = String.valueOf(i);
				list.add(value);
				used[i] = true;
				dfs(tickets, next);
				used[i] = false;
				list.remove(value);
			}
		}
	}

	public static void main(String[] args) {
		Solution43164 sol = new Solution43164();
//		sol.solution(new String[][] {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
//		System.out.println(Arrays.toString(sol.solution(new String[][]{ {"ICN", "AAA"}, {"ICN", "BBB"}, {"BBB", "ICN"}})));
//		sol.solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
//		System.out.println(Arrays.toString(sol.solution(new String[][]{{"ICN", "A"},{"ICN", "A"},{"ICN", "A"},{"A", "ICN"},{"A", "ICN"}})));
//		System.out.println(Arrays.toString(sol.solution(new String[][]{{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}, {"BOO", "DOO"}})));
		System.out.println(Arrays.toString(sol.solution(new String[][]{{"ICN","BOO" }, { "ICN", "COO" }, { "COO", "DOO" }, {"DOO", "COO"}, { "BOO", "DOO"} ,{"DOO", "BOO"}, {"BOO", "ICN" }, {"COO", "BOO"}})));
	}
}
