package backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class Main15649 {
	static int[] result;
	static StringBuilder sb;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	sb = new StringBuilder();
    	
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
        
    	result = new int[m];
        permutation(new boolean[n+1], 0, 0, m);
        bw.write(sb.toString());
        bw.close();
    }
    
    public static void permutation(boolean[] used, int index, int count, int m){
        if(count == m){
        	for (int i = 0; i < m; i++) {
				sb.append(result[i]+" ");
			}
        	
        	sb.append("\n");
            return;
        }
        
        
        for(int i = 1; i < used.length; i++){
            if(i != 0 && !used[i]){
                used[i] = true;
                result[index] = i;
                permutation(used, index + 1, count + 1, m);
                result[index] = 0;
                used[i] = false;
            }
        }
    }
}