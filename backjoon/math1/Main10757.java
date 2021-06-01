package math1;

import java.io.*;
import java.util.*;
public class Main10757 {
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		char[] A = st.nextToken().toCharArray();
		char[] B = st.nextToken().toCharArray();
		sb = new StringBuilder();
		
		recur(A, B, 0, 1);
		
		System.out.println(sb);
	}
	
	static void recur(char[] A, char[] B, int ceil, int index) {
		if(A.length - index < 0 && B.length - index < 0) {
			if(ceil != 0) sb.append(ceil);
			return;
		}
		
		int a = 0, b = 0;
		
		if(A.length - index >= 0) a = A[A.length - index] - '0';
		if(B.length - index >= 0) b = B[B.length - index] - '0';
		
		
		int cal = a + b + ceil;
		recur(A, B, cal >= 10 ? 1 : 0, index+1);
		sb.append(cal % 10);
	}
}
