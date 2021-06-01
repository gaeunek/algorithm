package segment;
import java.util.*;
import java.io.*;

// M 돌리면서 sysout 하는것보다 sb에 붙여서 마지막에 한번에 출력하는게 시간효율 더 좋음

public class Main11659_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] S = new int[N+1];
        
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++){
            S[i] = Integer.parseInt(st.nextToken());
            S[i] += S[i-1];
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(S[end] - S[start - 1]);
        }
    }
}