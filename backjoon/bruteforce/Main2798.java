package bruteforce;

// 블랙잭
import java.util.*;
public class Main2798 {
	static int[] result;
	static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] cards = new int[n];
		for (int i = 0; i < n; i++) {
			cards[i] = sc.nextInt();
		}
		
		result = new int[3];
		answer = 0;
		permutation(cards, new boolean[n], 0, 0, m);
		System.out.println(answer);
	}
	
	public static void permutation(int[] cards, boolean[] used, int st, int index, int m) {
		if(index == 3) {
			int sum = 0;
			for (int i = 0; i < index; i++) {
				sum += result[i];
			}
			
			if(sum > m) return;
			
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i = st; i < used.length; i++) {
			if(!used[i]) {
				used[i] = true;
				result[index] = cards[i];
				permutation(cards, used, i+1, index+1, m);
				result[index] = 0;
				used[i] = false;
			}
		}
	}
}
