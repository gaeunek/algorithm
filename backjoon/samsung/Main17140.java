package samsung;

import java.io.*;
import java.util.*;

//원래는 count 2차원 배열로 만들어서 계속 정렬해줬는데, 그럴 필요없이 List 사용하자
public class Main17140 {
	static int r, c, k, end_r, end_c;
	static int[][] map = new int[101][101];
	static ArrayList<Number> numberList = new ArrayList<>();

	static class Number {
		int index, count;

		public Number(int index, int count) {
			this.index = index;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Number [index=" + index + ", count=" + count + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= 100; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		end_r = 3;
		end_c = 3;

		int time;
		for (time = 0; time <= 100; time++) {
			if (map[r][c] == k) {
				break;
			}
			sortMap();
		}

		System.out.println(time > 100 ? -1 : time);
	}

	public static void sortMap() {
		int[] count = new int[101];

		if (end_r >= end_c) {
			for (int i = 0; i < end_r; i++) {
				for (int j = 0; j <= end_r; j++) {
					if (map[i][j] != Integer.MAX_VALUE) {
						count[map[i][j]]++;
						map[i][j] = Integer.MAX_VALUE;
					}
				}

				for (int j = 0; j <= 100; j++) {
					if (count[j] > 0)
						numberList.add(new Number(j, count[j]));
				}
				
				sortNumberList();

				int size = numberList.size() > 100 ? 100 : numberList.size() * 2;
				Number number = null;
				for (int j = 0; j < size; j++) {
					if (j % 2 == 0) {
						number = numberList.remove(0);
						map[i][j] = number.index;
					} else
						map[i][j] = number.count;
				}
				
				Arrays.fill(count, 0);
				end_c = Math.max(size, end_c); // map 배열의 최대 크기를 바꾸어준다
			}
		} else {
			for (int j = 0; j < end_c; j++) {
				for (int i = 0; i <= end_c; i++) {
					if (map[i][j] != Integer.MAX_VALUE) {
						count[map[i][j]]++;
						map[i][j] = Integer.MAX_VALUE;
					}
				}

				for (int i = 0; i <= 100; i++) {
					if (count[i] > 0)
						numberList.add(new Number(i, count[i]));
				}

				sortNumberList();

				int size = numberList.size() > 100 ? 100 : numberList.size() * 2;
				Number number = null;
				for (int i = 0; i < size; i++) {
					if (i % 2 == 0) {
						number = numberList.remove(0);
						map[i][j] = number.index;
					} else
						map[i][j] = number.count;
				}

				Arrays.fill(count, 0);
				end_r = Math.max(size, end_r);
			}
		}
	}

	public static void sortNumberList() {
		Collections.sort(numberList, new Comparator<Number>() {

			@Override
			public int compare(Number o1, Number o2) {
				if (o1.count == o2.count)
					return o1.index - o2.index;
				return o1.count - o2.count;
			}
		});
	}

	public static void print() {
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 10; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

}
