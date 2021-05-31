package level2;

import java.util.*;

//방금그곡
//네오가 들은 시간보다 재생 시간이 짧으면 그냥 (None)이 정답인가보다
class Solution17683 {
	public String solution(String m, String[] musicinfos) {
		MusicInfo[] music = new MusicInfo[musicinfos.length];
		
		String[] tmp;
		int len = 0;
		/* 재생시간에 맞게 멜로디를 늘려준다. */
		for (int i = 0; i < musicinfos.length; i++) {
			String[] infos = musicinfos[i].split(",");
			tmp = infos[0].split(":");
			len = (Integer.parseInt(tmp[0])*60) + Integer.parseInt(tmp[1]);
			tmp = infos[1].split(":");
			len -= (Integer.parseInt(tmp[0])*60) + Integer.parseInt(tmp[1]);
			len = Math.abs(len);
			String edit_melody = edit(infos[3]);
			
			if(len > edit_melody.length()) {
				int trans_music_len = edit_melody.length();
				for (int j = 0; j < len - trans_music_len; j++) {
					edit_melody += edit_melody.charAt(j % trans_music_len);
				}
			} else if(len < edit_melody.length()) {
				edit_melody = edit_melody.substring(0, len);
			}
			
			music[i] = new MusicInfo(i, infos[2], edit_melody);
		}
		
		/* 문제에 나온 조건에 맞게 정렬 */
		Arrays.sort(music, new Comparator<MusicInfo>() {

			@Override
			public int compare(MusicInfo o1, MusicInfo o2) {
				if(o1.melody.length() == o2.melody.length())
					return o1.index - o2.index;
				return o2.melody.length() - o1.melody.length();
			}
		});
		
		/* 네오가 들은 음악도 C# -> c 로 수정해준다. */
		m = edit(m);
		
		/* 정답찾기 */
		for (int i = 0; i < music.length; i++) {
			MusicInfo musicInfo = music[i];
			String melody = musicInfo.melody;
			
			if(melody.contains(m)) return musicInfo.title;
			
			//or
//			for (int j = 0; j <= melody.length()-m.length(); j++) {
//					String temp = melody.substring(j, j+m.length());
//					if(temp.equals(m)) return musicInfo.title;
//			}
		}
		
		return "(None)";
	}
	
	static String edit(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length()-1; i++) {
			char c = s.charAt(i);
			char next = s.charAt(i+1);
			if(c == '#') continue;
			if(next == '#') sb.append(String.valueOf(c).toLowerCase());
			else sb.append(c);
		}
		
		if(s.charAt(s.length()-1) != '#') sb.append(s.charAt(s.length()-1));
		
		return new String(sb);
	}
	
	static class MusicInfo {
		int index;
		String title, melody;
		
		public MusicInfo(int index, String title, String melody) {
			this.index = index;
			this.title = title;
			this.melody = melody;
		}
		
		@Override
		public String toString() {
			return this.title +","+ this.melody;
		}
	}

	public static void main(String[] args) {
		Solution17683 sol = new Solution17683();
		System.out.println(sol.solution("ABCDEFG", new String[] {"13:00,13:05,WORLD,ABCDEF"}));
//		System.out.println(sol.solution("CC#BCC#BCC#BCC#B", new String[] {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
//		System.out.println(
//				sol.solution("ABC", new String[] { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" }));
//		System.out.println(sol.solution("ABC", new String[] {"00:00,00:05,HI,ABC#ABC"}));
//		System.out.println(sol.solution("CDCDF", new String[] { "12:00,12:08,HI,CDCDCDF" }));
//		System.out.println(sol.solution("CDEFGAC", new String[] {"12:00,12:06,HELLO,CDEFGA"}));
//		System.out.println(sol.solution("CCB", new String[] {"03:00,03:10,FOO,CCB#CCB", "04:00,04:08,BAR,ABC"}));
//		System.out.println(sol.solution("A#", new String[] {"13:00,13:02,AN,B#A#", "13:00,13:02,HAPPY,B#A#"}));
	}
}
