package jungol;

import java.io.*;
import java.util.*;
public class Main3517 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < q; i++) {
			bw.write(binarySearch(nums, Integer.parseInt(st.nextToken()), n)+" ");
		}
		
		br.close();
		bw.close();
	}
	
	public static int binarySearch(int[] nums, int target, int n) {
		int min = 0, max = n-1;
		while(min <= max) {
			int mid = (min + max) / 2;
			
			if(nums[mid] == target) {
				return mid;
			} else if(nums[mid] < target) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		
		return -1;
	}
}
