package level3;

import java.util.Arrays;

class Solution17676 {
	public int solution(String[] lines) {
		int answer = 0;

		int[] starTime = new int[lines.length];
		int[] endTime = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {
			String[] input = lines[i].split(" ");

			double pros_time = Double.parseDouble(input[2].replaceAll("[^.0-9]", "")) - 0.001;

			String[] times = input[1].split(":");

			int hour = Integer.parseInt(times[0]) * 3600 * 1000;
			int minute = Integer.parseInt(times[1]) * 60 * 1000;
			int second = Integer.parseInt(times[2].replace(".", ""));

			endTime[i] = hour + minute + second;
			starTime[i] = endTime[i] - (int) (pros_time * 1000);
		}
		
		for (int i = 0; i < lines.length; i++) {
			int startSection = endTime[i];
			int endSection = startSection + 999;

			int count = 0;
			for (int j = 0; j < lines.length; j++) {
				int start = starTime[j];
				int end = endTime[j];

				if (startSection <= end && end <= endSection)
					count++;
				else if (start <= startSection && endSection <= end)
					count++;
				else if (startSection <= start && start <= endSection)
					count++;
				else if (startSection <= start && end <= endSection)
					count++;
			}

			answer = Math.max(count, answer);
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution17676 sol = new Solution17676();
		System.out.println(sol.solution(new String[] { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" }));
		System.out.println(sol.solution(new String[] { "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" }));
		System.out.println(sol.solution(new String[] { "2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" }));
	}
}
