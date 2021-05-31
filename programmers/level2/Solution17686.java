package level2;

import java.util.*;
//파일명 정렬
class Solution17686 {
	public String[] solution(String[] files) {
		String[] answer = new String[files.length];
		File[] result = new File[files.length];

		String s = "";
		for (int i = 0; i < files.length; i++) {
			String tmp = files[i];
			boolean flag = false, numCheck = false;
			String num = "0";

			for (int j = 0; j < tmp.length(); j++) {
				char c = tmp.charAt(j);
				if (c == '0' && !flag) {
					numCheck = true;
					continue;
				} else if (c >= '0' && c <= '9') {
					flag = true;
					num += c;
					continue;
				}

				if ((j != 0 && flag) || numCheck)
					break;
				if (!flag)
					s += c;
			}

			result[i] = new File(i, s.toLowerCase(), Integer.parseInt(num));
			s = "";
		}
		
		Arrays.sort(result, new Comparator<File>() {
			
			@Override
			public int compare(File o1, File o2) {
				if(o1.head.compareTo(o2.head) == 0) {
					if(o1.num == o2.num) return o1.index - o2.index;
					return o1.num - o2.num;
				}
				return o1.head.compareTo(o2.head);
			}
		});
		
//		//삽입정렬 또는 버블정렬로 풀어야함
//		for (int i = 0; i < result.length-1; i++) {
//			for (int j = 1; j < result.length-i; j++) {
//				File now = result[j-1];
//				File next = result[j];
//				if(now.head.compareTo(next.head) >= 1) {
//					result[j] = now;
//					result[j-1] = next;
//				} else if(now.head.compareTo(next.head) == 0) {
//					if(now.num <= next.num) continue;
//					result[j] = now;
//					result[j-1] = next;
//				}
//			}
//		}
		
		for (int i = 0; i < answer.length; i++) {
			answer[i] = files[result[i].index];
		}

		return answer;
	}

	static class File {
		String head;
		int index, num;

		public File(int index, String head, int num) {
			this.index = index;
			this.head = head;
			this.num = num;
		}

		@Override
		public String toString() {
			return "[" + this.index + ", " + this.head + ", " + this.num + "]";
		}
	}

	public static void main(String[] args) {
		Solution17686 sol = new Solution17686();
		System.out.println(Arrays.toString(sol.solution(
				new String[] { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10D Thunderbolt II", "F-14 Tomcat" })));
		System.out.println(Arrays.toString(sol.solution(new String[] {"F13.png", "F14", "f013", "F014F", "F013.TXT"})));
		System.out.println(Arrays.toString(sol.solution(new String[] {"a0001", "a001", "a000001"} )));
	}
}
