package lv3.숫자_타자_대회;

import java.util.*;

class Solution {
    int N;
    int[] nums;
    int[][][] dp;
    
    int[][] costs = {
        {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1},
    };
    
    public int solution(String numbers) {
        
        N = numbers.length();
        dp = new int[N + 1][10][10];
        
        nums = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            nums[i] = (int) numbers.charAt(i - 1) - '0';
            for(int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {1, 4, 6}); // 초기 손 위치
        
        int n, num, left, right;
        int cur[];
        while(!queue.isEmpty()) {
            cur = queue.poll();
            n = cur[0]; // n번째
            num = nums[n]; // n번째 숫자
            left = cur[1]; // 왼손 위치
            right = cur[2]; // 오른손 위치
            
            // n번째 숫자를 왼손으로 누른 경우 + 왼손 오른손 위치 같으면 안됨
            if(num != right && dp[n][num][right] > dp[n - 1][left][right] + costs[left][num]) {
                dp[n][num][right] = dp[n - 1][left][right] + costs[left][num];
                if(n + 1 <= N) queue.add(new int[] {n + 1, num, right});
            }
            
            // n번째 숫자를 오른손으로 누른 경우
            if(num != left && dp[n][left][num] > dp[n - 1][left][right] + costs[right][num]) {
                dp[n][left][num] = dp[n - 1][left][right] + costs[right][num];
                if(n + 1 <= N) queue.add(new int[] {n + 1, left, num});
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                answer = Math.min(answer, dp[N][i][j]);
            }
        }
        
        return answer;
    }
}