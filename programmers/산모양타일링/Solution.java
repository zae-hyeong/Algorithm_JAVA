package 산모양타일링;

import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        
        int[] a = new int[n];
        int[] b = new int[n];
        
        a[0] = 1;
        b[0] = tops[0] == 1 ? 3 : 2;
        
        for(int i = 1; i < n; i++) {
            if(tops[i] == 1) {
                b[i] = a[i - 1] * 2 + b[i - 1] * 3;
            } else {
                b[i] = a[i - 1] + b[i - 1] * 2;
            }
            
            a[i] = a[i - 1] + b[i - 1];
            
            a[i] = a[i] % 10007;
            b[i] = b[i] % 10007;
        }
        
        // System.out.println(Arrays.toString(a));
        // System.out.println(Arrays.toString(b));
        
        return (a[n - 1] + b[n - 1]) % 10007;
    }
}