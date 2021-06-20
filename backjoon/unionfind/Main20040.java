package unionfind;

import java.util.*;
import java.io.*;

public class Main20040 {
    static int n, m;
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parent = new int[n];
        
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(union(u, v)){
                System.out.println(i);
                return;
            }
        }
        
        System.out.println(0);
    }
    
    public static boolean union(int a, int b){
        int findA = find(a);
        int findB = find(b);
        
        if(findA == findB) return true;
        
        if(findA < findB) parent[findA] = findB;
        else parent[findA] = findB;
        
        return false;
    }
    
    public static int find(int n){
        if(parent[n] == n) return n;
        
        return parent[n] = find(parent[n]);
    }
}