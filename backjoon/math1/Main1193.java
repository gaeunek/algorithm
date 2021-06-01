package math1;

import java.util.*;

public class Main1193 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int num = 0, seq = 1, res = 0;
        
        while(true){
            num += seq;
            
            if(num >= n){
                res = seq;
                break;
            }
            
            seq++;
        }
        
        int i = 0, j = 0;
        int st = num - (res - 1);
        
        if(res % 2 == 0){
            //i+1, j-1
            i = 1;
            j = res;
           
            for(int k = st; k <= num; k++, i++, j--){
                if(k == n) {
                    System.out.println(i+"/"+j);
                    break;
                }
            }
        } else {
            //i-1, j+1
            i = res;
            j = 1;
            
            for(int k = st; k <= num; k++, i--, j++){
                if(k == n) {
                    System.out.println(i+"/"+j);
                    break;
                }
            }
        }
    }
}