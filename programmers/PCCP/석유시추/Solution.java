package PCCP.석유시추;

import java.util.*;

class Solution {
    int N, M;
    int[][] land;
    boolean[][] v;
    
    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};
    int bfsFrom(int i, int j, int id) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[] {i , j});
        land[i][j] = id;
        v[i][j] = true;
        int cnt = 1;
        
        int ny, nx;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                ny = cur[0] + dy[d];
                nx = cur[1] + dx[d];
            
                if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx] && land[ny][nx] != 0) {
                    v[ny][nx] = true;
                    land[ny][nx] = id;
                    cnt++;
                    queue.add(new int[] {ny, nx});
                }
            }
        }
        return cnt;
        
    }
    
    public int solution(int[][] land) {
        this.land = land;
        this.N = land.length;
        this.M = land[0].length;
        v = new boolean[N][M];
        
        HashMap<Integer, Integer> idSizeMapper = new HashMap<>();
        idSizeMapper.put(0, 0);
        
        int groupId = 1, size;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(land[i][j] == 0) {
                    v[i][j] = true;
                    continue;
                }
                if(v[i][j]) continue;
                
                size = bfsFrom(i, j, groupId);
                idSizeMapper.put(groupId++, size);
                // for(int[] l : land) System.out.println(Arrays.toString(l));
                // System.out.println("---------------------------------");
            }
        }
        
        
        // for(int[] l : land) System.out.println(Arrays.toString(l));
        // System.out.println(idSizeMapper.toString());
        
        Set<Integer> set = new HashSet<>();
        int maxSum = 0, sum;
        for(int j = 0; j < M; j++) {
            set.clear();
            for(int i = 0; i < N; i++) {
                set.add(land[i][j]);
            }
            
            sum = 0;
            for(int s : set) {
                sum += idSizeMapper.get(s);
            }
            maxSum = Math.max(maxSum, sum);
        }
        
        return maxSum;
    }
}