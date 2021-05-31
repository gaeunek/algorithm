package level2;

//멀쩡한 사각형 다시해야함 수학적 접근임
public class Solution62048 {
	public long solution(int w, int h) {
		if(h == 1 || w * h == 1) return 0;
		else if(w == h) return w * h - w;
		else {
			int tmp = 0;
			if(w > h) {
				tmp = w;
				w = h;
				h = tmp;
			}
			
			return (long)(w * h) - (w * 2);
		}
		//한줄에 두개씩 빼기.. 즉, w = 6이면 12개
	}
}
