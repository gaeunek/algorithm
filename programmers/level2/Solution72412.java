package level2;

import java.util.*;
//���� �˻�
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
				//������ ������ �� �ִ� �ε�������
				//���ذ� �ȵǸ� �迭�� ũ�Ⱑ 4�϶��� ���÷� �����غ���
				//st�� 4���� �� �� �ְ� end�� 4�϶� mid���� 4�ε� �ε����� 3�����ϱ� �翬�� ��Ÿ�ӿ����� ����. 

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
