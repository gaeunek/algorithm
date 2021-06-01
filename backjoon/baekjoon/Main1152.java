package baekjoon;

import java.io.*;
//단어의 개수
// " "가 들어오는 경우도 생각하자 ㅠ
public class Main1152 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		if(input.charAt(0) == ' ') input = input.substring(1);
		
		if(input.length() == 0) System.out.println(0);
		else {
			String[] strs = input.split(" ");
			System.out.println(strs.length);
		}
	}
}
