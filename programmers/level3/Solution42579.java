package level3;

import java.util.*;
import java.util.Map.Entry;

//베스트 앨범
public class Solution42579 {
	public int[] solution(String[] genres, int[] plays) {
		ArrayList<Music> list = new ArrayList<>();

		for (int i = 0; i < plays.length; i++) {
			list.add(new Music(genres[i], plays[i], i));
		}

		Collections.sort(list);
		
		// 장르 재생 수 계산
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < genres.length; i++) {
			map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
		}

		// 내림차순 정렬
		List<Entry<String, Integer>> list_entry = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list_entry, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < list_entry.size(); i++) {
			String key = list_entry.get(i).getKey();
			
			int cnt = 0;
			
			for (Music m : list) {
				if (m.genre.equals(key)) {
					result.add(m.index);
					cnt++;
					if (cnt % 2 == 0)
						break;
				}
			}
		}
		
		int[] answer = new int[result.size()];
		
		for (int i = 0; i < answer.length; i++) {
			answer[i] = result.get(i);
		}
		
		return answer;
	}

	static public class Music implements Comparable<Music> {
		int play, index;
		String genre;

		public Music(String genre, int play, int index) {
			this.index = index;
			this.play = play;
			this.genre = genre;
		}

		@Override
		public int compareTo(Music o) {
			if(o.play == this.play)
				return this.index - o.index;
			else	
				return o.play - this.play;
		}

		@Override
		public String toString() {
			return "Music [play=" + play + ", index=" + index + ", genre=" + genre + "]";
		}
		
		
	}
}
