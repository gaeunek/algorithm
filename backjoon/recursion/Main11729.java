package recursion;

// 하노이 탑 이동 순서
import java.util.*;
public class Main11729 {
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		sb = new StringBuilder();
		hanoi(n, 1, 3, 2);
        System.out.println((int)Math.pow(2, n)-1);
		System.out.println(sb.toString());
	}
	
	public static void hanoi(int n, int from, int to, int by) {
		// 원반이 하나뿐이라면 바로 목적지로 옮긴다.
		if(n == 1) {
			sb.append(from+" "+to+"\n");
			return;
		}
		
		// 먼저 가장 큰 원반을 제외한 n-1개의 원반을 A(from)->B(by)로 옮겨야 한다.
		hanoi(n-1, from, by, to);
		
		// 가장 큰 원반 1개를 A(from)->C(to)로 옮긴다.
		sb.append(from+" "+to+"\n");
		
		// A->B로 옮긴 나머지 원반을 B(by)->C(to)로 옮긴다.
		hanoi(n-1, by, to, from);
	}
}
