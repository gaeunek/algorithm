package segment;

import java.util.*;
import java.io.*;

public class Main1517 {
	static long answer;
	static long[] arr, temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		arr = new long[N];
		temp = new long[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		answer = 0;
		
		// mergeSort 사용
		mergeSort(0, N - 1);

		System.out.println(answer);
	}

	public static void mergeSort(int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(start, mid);
			mergeSort(mid + 1, end);
			merge(start, mid, end);
		}
	}

	public static void merge(int start, int mid, int end) {
		int left = start;
		int right = mid + 1;
		int index = start;

		while (left <= mid && right <= end) {
			if (arr[left] <= arr[right]) {
				temp[index++] = arr[left++];
			} else {
				answer += mid + 1 - left;
				temp[index++] = arr[right++];
			}
		}

		while (left <= mid) {
			temp[index++] = arr[left++];
		}

		while (right <= end) {
			temp[index++] = arr[right++];
		}

		for (int i = start; i <= end; i++) {
			arr[i] = temp[i];
		}
	}
}
