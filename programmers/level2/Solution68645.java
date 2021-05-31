package level2;

import java.util.*;
class Solution68645 {
	public int[] solution(int n) {
		int[][] arr = new int[n][];

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new int[i + 1];
			sum += i + 1;
		}
		
		int[] answer = new int[sum];

		int value = 1;
		int i = 0, j = 0;
		arr[i][j] = value;
		while (value < sum) {
			while(i < n-1 && arr[i+1][j] == 0) {
				arr[++i][j] = ++value;
			}

			while(j < n-1 && arr[i][j+1] == 0) {
				arr[i][++j] = ++value;
			}
			
			while(i > 0 && j > 0 && arr[i-1][j-1] == 0) {
				arr[--i][--j] = ++value;
			}
			print(arr);
		}
		
		int index = 0;
		for (i = 0; i < arr.length; i++) {
			for (j = 0; j < arr[i].length; j++) {
				answer[index++] = arr[i][j]; 
			}
		}

		return answer;
	}
	
	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Solution68645 sol = new Solution68645();
//		System.out.println(sol.solution(6));
//		System.out.println(sol.solution(5));
		System.out.println(sol.solution(4));
	}
}
