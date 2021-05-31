package level3;

//광고 삽입
class Solution72414 {
	public String solution(String play_time, String adv_time, String[] logs) {
		String[] tmp, tmp1, tmp2;

		tmp = play_time.split(":");
		int play_time_sec = Integer.parseInt(tmp[0]) * 3600 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2]);
		tmp = adv_time.split(":");
		int adv_time_sec = Integer.parseInt(tmp[0]) * 3600 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2]);

		if (play_time == adv_time)
			return "00:00:00";

		long[] total_time = new long[360000];

		for (int i = 0; i < logs.length; i++) {
			tmp = logs[i].split("-");
			tmp1 = tmp[0].split(":");
			tmp2 = tmp[1].split(":");

			int start = Integer.parseInt(tmp1[0]) * 3600 + Integer.parseInt(tmp1[1]) * 60 + Integer.parseInt(tmp1[2]);
			int end = Integer.parseInt(tmp2[0]) * 3600 + Integer.parseInt(tmp2[1]) * 60 + Integer.parseInt(tmp2[2]);

			total_time[start]++;
			total_time[end]--;
		}

		for (int i = 1; i < play_time_sec; i++) {
			total_time[i] += total_time[i - 1];
		}

		for (int i = 1; i < play_time_sec; i++) {
			total_time[i] += total_time[i - 1];
		}

		long max = 0, result = 0;
		int max_start_sec = 0;

		for (int end = adv_time_sec - 1; end < play_time_sec; end++) {
			int st = end - adv_time_sec;
			if (st < 0)
				result = total_time[end];
			else
				result = total_time[end] - total_time[st];

			if (result > max) {
				max = result;
				max_start_sec = st + 1;
			}
		}

		return trans(max_start_sec);
	}

	static String trans(int max_start_sec) {
		int hour = max_start_sec / 3600;
		int minute = (max_start_sec % 3600) / 60;
		int second = max_start_sec % 3600 % 60;
		return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
				+ (second < 10 ? "0" + second : second);
	}

	public static void main(String[] args) {
		Solution72414 sol = new Solution72414();
		System.out.println(sol.solution("02:03:55", "00:14:15", new String[] { "01:20:15-01:45:14", "00:25:50-00:48:29",
				"00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29" }));
		System.out.println(sol.solution("99:59:59", "25:00:00",
				new String[] { "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00" }));
	}
}
