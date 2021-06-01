package bruteforce;

import java.util.*;

public class Main2231 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int answer = Integer.MAX_VALUE;
        for(int i = n-1; i >= 1; i--){
            int target = n - i;
            int value = n - target;
            int sum = 0, tmp = value;
            
            while(tmp > 0){
                sum += tmp % 10;
                tmp /= 10;
            }
            
            if(sum == target) answer = Math.min(answer, value);
        }
        
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}