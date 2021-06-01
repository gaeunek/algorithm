package math2;

import java.util.*;

public class Main1978 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int answer = 0;
        for(int i = 0; i < n; i++){
            int num = sc.nextInt();
            
            int cnt = 0;
            if(num <= 1){
                continue;
            } 
            
            for(int j = 2; j <= num; j++){
                if(num % j == 0) cnt++;
            }
            
            if(cnt == 1) answer++;
        }
        
        System.out.println(answer);
    }
}