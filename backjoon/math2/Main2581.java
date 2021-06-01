package math2;

import java.util.*;

public class Main2581 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sum = 0;
        int min = Integer.MAX_VALUE;
        
        for(int i = n; i <= m; i++){
            int cnt = 0;
            for(int j = 2; j <= i; j++){
                if(i % j == 0) cnt++;
            }
            
            if(cnt == 1) {
                sum += i;
                min = Math.min(i, min);
            }
        }
        
        if(sum == 0) System.out.println(-1);
        else{
        	System.out.println(sum);
        	System.out.println(min);
        }
    }
}