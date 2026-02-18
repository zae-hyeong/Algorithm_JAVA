package lv1.공원_산책;

import java.util.*;

class Solution {
    
    void log(Object o) {
        System.out.println(o.toString());
    }
    
    int H, W;
    int[][] map;
    boolean[][] v;
    
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    
    boolean inBound(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }
    
    int parseToDir(String s) {
        if("N".equals(s)) {
            return 0;
        } else if("S".equals(s)) {
            return 1;
        } else if("W".equals(s)) {
            return 2;
        } else {
            return 3;
        }
    }
    
    boolean isSomethingInRoute(int y, int x, int ny, int nx) {
        if(y < ny) {
            for(int i = y + 1; i <= ny; i++) {
                if(map[i][x] == 1) return true;
            }
        } else if(y > ny) { 
            for(int i = y - 1; i >= ny; i--) {
                if(map[i][x] == 1) return true;
            }
        } else if(x < nx) {
            for(int i = x + 1; i <= nx; i++) {
                if(map[y][i] == 1) return true;
            }
        } else if(x > nx) {
            for(int i = x - 1; i >= nx; i--) {
                if(map[y][i] == 1) return true;
            }
        }
        
        return false;
    }
    
    public int[] solution(String[] p, String[] r) {
        H = p.length;
        W = p[0].length();
        
        map = new int[H][W];
        v = new boolean[H][W];
        
        int posY = 0, posX = 0;
        
        char c;
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                c = p[i].charAt(j);
                
                map[i][j] = 0;
                
                if(c == 'S') {
                    posY = i;
                    posX = j;
                } else if (c == 'X') {
                    map[i][j] = 1;
                }
            }
        }
        
        int ny, nx;
        for(String route: r) {
            StringTokenizer st = new StringTokenizer(route);
            int dir = parseToDir(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            
            ny = posY + dy[dir] * dist;
            nx = posX + dx[dir] * dist;
            
            if(!inBound(ny, nx)) continue;
            if(isSomethingInRoute(posY, posX, ny, nx)) continue;
            
            posY = ny;
            posX = nx;
        }
        
        int[] answer = {posY, posX};
        return answer;
    }
}