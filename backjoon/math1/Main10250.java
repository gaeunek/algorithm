package math1;

import java.util.*;

public class Main10250 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        int h, w, client;
        int remainder, value;
        int floor = 0, number = 0;
        
        for(int tc = 1; tc <= t; tc++){
            h = sc.nextInt();
            w = sc.nextInt();
            client = sc.nextInt();
            
            remainder = client % h;
            value = client / h;
            floor = remainder == 0 ? h : remainder;
            number = remainder == 0 ? value : value + 1;
            System.out.println(floor+""+ (number < 10 ? "0"+number : number));
        }
        
    }
}