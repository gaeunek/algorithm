package level3;

import java.util.*;
//매칭 점수
class Solution42893 {
	public int solution(String word, String[] pages) {
		int len = pages.length;
		word = word.toLowerCase();

		Map<String, Integer> url_list = new HashMap<>();
		Page[] result = new Page[len];

		String[] meta_filter, url_filter, body_filter, end_body_filter, words_list, find_word;
		String match = "[^a-z]";

		/* url과 index를 저장해준다. */
		for (int i = 0; i < len; i++) {
			pages[i] = pages[i].toLowerCase();
			meta_filter = pages[i].split("<meta property=\"og:url\" ");
			url_filter = meta_filter[1].split("content=\"");
			url_filter[1] = url_filter[1].replaceAll("\n", "");

			String url = url_filter[1];
			int j = 0;
			for (j = 1; j < url.length(); j++) {
				if (url.charAt(j) == '\"')
					break;
			}

			url_list.put(url.substring(0, j), i);
			result[i] = new Page(0, 0, 0);
		}

		for (int i = 0; i < len; i++) {
			body_filter = pages[i].split("<body>");
			end_body_filter = body_filter[1].split("</body>");
			words_list = end_body_filter[0].split("\n");

			int link_count = 0, word_count = 0;
			for (int j = 0; j < words_list.length; j++) {
				String words = words_list[j];

				/* 외부 링크 연결 */
				String[] tmp = words.split("<a href=\"");
				for (int k = 0; k < tmp.length; k++) {
					if (tmp[k].contains("</a>")) {
						link_count++;
						String s = tmp[k];

						int c = 0;
						for (c = 0; c < s.length(); c++) {
							if (s.charAt(c) == '\"')
								break;
						}

						String key = s.substring(0, c);
						
						if (url_list.get(key) != null)
							result[url_list.get(key)].connect_link_list.add(i);
					}
				}

				/* word 카운트 */
				words = words.replaceAll(match, " ");
				if (words.contains(word)) {
					find_word = words.split(" ");

					for (int k = 0; k < find_word.length; k++) {
						if (find_word[k].equals(word))
							word_count++;
					}
				}
			}

			result[i].basic_score = word_count;
			result[i].link_count = link_count;
			result[i].link_score = (double) word_count / link_count;
		}

		double max = 0;
		int max_index = 0;
		for (int i = 0; i < len; i++) {
			Page p = result[i];

			double sum = p.basic_score;
			for (int j = 0; j < p.connect_link_list.size(); j++) {
				int index = p.connect_link_list.get(j);
				sum += result[index].link_score;
			}

			if (max < sum) {
				max = sum;
				max_index = i;
			}
		}

		return max_index;
	}

	static class Page {
		int basic_score, link_count;
		double link_score;
		List<Integer> connect_link_list;

		public Page(int b, int lc, double ls) {
			this.basic_score = b;
			this.link_count = lc;
			this.link_score = ls;
			this.connect_link_list = new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		Solution42893 sol = new Solution42893();
//		System.out.println(sol.solution("blind", new String[] {
//				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
//				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
//				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" }));
		System.out.println(sol.solution("Muzi", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" }));
	}
}
