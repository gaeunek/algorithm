package math1;

import java.util.*;

public class Main2839{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int answer = n % 3 == 0 ? n / 3 : Integer.MAX_VALUE;
        answer = n % 5 == 0 && n / 5 < answer ? n / 5 : answer;
        
        int three = 1;
        while(three < n){
            int cal = n - three * 3;
            if(cal < 0) break;
            
            if(cal % 5 == 0){
                answer = Math.min(answer, three + cal / 5);
            }
            
            three++;
        }
        
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}