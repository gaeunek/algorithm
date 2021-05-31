package level3;

import java.util.*;

class Solution42861 {
	static int[] parent;
	public int solution(int n, int[][] costs) {
		int answer = 0;
		
		Arrays.sort(costs, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		parent = new int[n];
		
		for (int i = 0; i < n; i++) parent[i] = i; //부모값 본인으로 초기화
		
		for (int i = 0; i < costs.length; i++) {
			if(find(costs[i][0]) != find(costs[i][1])) {
				union(costs[i][0], costs[i][1]);
				answer += costs[i][2];
			}
		}
		
		return answer;
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA != parentB) parent[parentB] = parentA;
	}
}
