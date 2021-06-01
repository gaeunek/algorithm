package baekjoon;

import java.io.*;

public class Main2857 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer = "";
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			if (s.contains("FBI"))
				answer += (i + 1) + " ";
		}

		if (answer.equals(""))
			System.out.println("HE GOT AWAY!");
		else
			System.out.println(answer);
	}
}
