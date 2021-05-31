package level2;

import java.util.*;
//순위 검색
class Solution72412 {
	static Map<ArrayList<String>, ArrayList<Integer>> map;

	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		map = new HashMap<>();
		String[][] row = new String[info.length][5];

		for (int i = 0; i < info.length; i++) {
			row[i] = info[i].split(" ");
		}

		Arrays.sort(row, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				return Integer.parseInt(o1[4]) - Integer.parseInt(o2[4]);
			}
		});

		for (int i = 0; i < info.length; i++) {
			boolean[] used = new boolean[5];
			comb(row[i], used, 0);
		}

		ArrayList<String> tmp = new ArrayList<>();

		for (int i = 0; i < query.length; i++) {
			String[] q = query[i].split(" ");

			for (int j = 0; j < q.length; j += 2) {
				tmp.add(q[j]);
			}

			int num = Integer.parseInt(q[7]);

			ArrayList<Integer> result = map.get(tmp);

			if (result != null) {
				int st = 0, end = result.size() - 1;
				//범위는 참조할 수 있는 인덱스까지
				//이해가 안되면 배열의 크기가 4일때를 에시로 생각해보자
				//st가 4까지 갈 수 있고 end도 4일때 mid값은 4인데 인덱스는 3까지니까 당연히 런타임에러가 난다. 

				while (st <= end) {
					int mid = (st + end) / 2;

					if (result.get(mid) < num) {
						st = mid + 1;
					} else {
						end = mid - 1;
					}
				}

				answer[i] = result.size() - st;
			}

			tmp.clear();
		}

		return answer;
	}

	static void comb(String[] row, boolean[] used, int index) {
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < used.length - 1; i++) {
			if (used[i]) {
				list.add(row[i]);
			} else {
				list.add("-");
			}
		}

		map.computeIfAbsent(list, key -> new ArrayList<>()).add(Integer.parseInt(row[4]));

		if (index == used.length)
			return;

		for (int i = index; i < used.length - 1; i++) {
			if (!used[i]) {
				used[i] = true;
				comb(row, used, i + 1);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Solution72412 sol = new Solution72412();
		System.out.println(Arrays.toString(sol.solution(
				new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
						"python frontend senior chicken 150", "cpp backend senior pizza 260",
						"java backend junior chicken 80", "python backend senior chicken 50" },
				new String[] { "java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
						"- and backend and senior and - 150", "- and - and - and chicken 100",
						"- and - and - and - 150" })));
	}
}
