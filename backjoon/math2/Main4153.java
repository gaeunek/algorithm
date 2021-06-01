package math2;

import java.util.*;
import java.io.*;

public class Main4153 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	while(true) {
    		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    		
    		double[] arr = new double[3];
    		int index = 0;
    		while(st.hasMoreTokens()){
    			arr[index++] = Integer.parseInt(st.nextToken());
    		}
    		
    		if(arr[0] + arr[1] + arr[2] == 0) break;
    		
    		Arrays.sort(arr);
    		
    		System.out.println(Math.pow(arr[0], 2) + Math.pow(arr[1], 2) == Math.pow(arr[2], 2) ? "right" : "wrong");
    	}
    }
}
