package swea;

import java.io.*;
import java.util.*;
//염라대왕의 이름 정리 ?
public class Solution7701 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
	
			Set<String> set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				set.add(br.readLine());
			}
			
			List<String> list = new ArrayList<>(set);
			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if(o1.length() == o2.length()) return o1.compareTo(o2);
					return o1.length() - o2.length();
				}
			});
			
			System.out.println("#"+tc);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
}
