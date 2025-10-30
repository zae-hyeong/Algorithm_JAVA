package PCCP.수레움직이기;

import java.util.*;

/**
 * 문제 제대로 읽자 = 가만히 있는 경우도 있었음..
 */
class Solution {
    int N, M;
    int[][] map;
    int[] redStart = null, redEnd = null, blueStart = null, blueEnd = null;
    
    class Node {
        int y, x, sec;
        HashSet<String> route;
        
        Node(int y, int x, int sec, HashSet<String> route) {
            this.y = y;
            this.x = x;
            this.sec = sec;
            this.route = route;
        }
    }
    
    String makeKey(int y, int x, int sec) {
        return y + "," + x + "," + sec;
    }
    
    boolean inBound(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
    
    HashSet<String> bfsFrom(int y, int x, boolean isRed) {
        boolean[][] v = new boolean[N][M];
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        
        ArrayDeque<Node> queue = new ArrayDeque<>();
        HashSet<String> route = new HashSet<>();
        v[y][x] = true;
        route.add(makeKey(y, x, 0));
        queue.add(new Node(y, x, 0, route));
        
        Node cur = null;
        int ny, nx;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                ny = cur.y + dy[d];
                nx = cur.x + dx[d];
                if(inBound(ny, nx) && map[ny][nx] >= 0 && !v[ny][nx]) {
                    v[ny][nx] = true;
                    
                    HashSet<String> newRoute = new HashSet<>(cur.route);
                    newRoute.add(makeKey(ny, nx, cur.sec + 1));
                    
                    if(isRed && redEnd[0] == ny && redEnd[1] == nx) {
                        return newRoute;
                    } else if(!isRed && blueEnd[0] == ny && blueEnd[1] == nx) {
                        return newRoute;
                    }
                    
                    queue.add(new Node(ny, nx, cur.sec + 1, newRoute));
                }
            }
        }
        
        return null;
    }
    
    int bfsFrom(int y, int x, HashSet<String> visited, boolean isRed) {
        boolean[][] v = new boolean[N][M];
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        
        ArrayDeque<Node> queue = new ArrayDeque<>();
        v[y][x] = true;
        queue.add(new Node(y, x, 0, null));
        
        Node cur = null;
        int ny, nx;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                ny = cur.y + dy[d];
                nx = cur.x + dx[d];
                // System.out.println("route : " + ny + ":"+ nx + " / sec : " + (cur.sec + 1));
                // System.out.println(!visited.contains(makeKey(ny, nx, cur.sec + 1)) + " / makeKey(ny, nx, cur.sec + 1) : " + makeKey(ny, nx, cur.sec + 1));
                if(inBound(ny, nx) && map[ny][nx] >= 0 && !v[ny][nx] && !visited.contains(makeKey(ny, nx, cur.sec + 1))) {
                    //수레의 위치가 스위치 되는 경우 
                    if(visited.contains(makeKey(y, x, cur.sec + 1)) && visited.contains(makeKey(ny, nx, cur.sec)))
                        continue;
                
                    if(isRed && redEnd[0] == ny && redEnd[1] == nx) {
                        return cur.sec + 1;
                    } else if(!isRed && blueEnd[0] == ny && blueEnd[1] == nx) {
                        return cur.sec + 1;
                    }
                    
                    v[ny][nx] = true;
                    queue.add(new Node(ny, nx, cur.sec + 1, null));
                }
            }
        }
        
        return 0;
    }
    
    public int solution(int[][] maze) {
        this.N = maze.length;
        this.M = maze[0].length;
        this.map = maze;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    redStart = new int[] {i, j};
                } else if (map[i][j] == 2) {
                    blueStart = new int[] {i, j};
                } else if (map[i][j] == 3) {
                    redEnd = new int[] {i, j};
                } else if (map[i][j] == 4) {
                    blueEnd = new int[] {i, j};
                } else if (map[i][j] == 5) {
                    map[i][j] = -1;
                    continue;
                }
                map[i][j] = 0;
            }
        }
        
        // for(int[] m : map) System.out.println(Arrays.toString(m));
        
        HashSet<String> redRoute = bfsFrom(redStart[0], redStart[1], true);
        // if(redRoute == null) {
        //     // System.out.println("redRoute is NULL");
        //     return 0;
        // }
        HashSet<String> blueRoute = bfsFrom(blueStart[0], blueStart[1], false);
        // if(redRoute == null) {
        //     // System.out.println("blueRoute is NULL");
        //     return 0;
        // }
        
        if(redRoute == null) return 0;
        if(blueRoute == null) return 0;
        
        if(redRoute.size() <= blueRoute.size()) { // 빨간색 도착
            // 빨간색은 도착했으므로, 빨강이 도착한 뒤의 redEnd는 지나갈 수 없음.
            for(int i = redRoute.size(); i <= 16; i++) 
                redRoute.add(makeKey(redEnd[0], redEnd[1], i));
            
            // System.out.println(redRoute.toString());
            return bfsFrom(blueStart[0], blueStart[1], redRoute, false);
        } else {  // 파란색 도착
            for(int i = blueRoute.size(); i <= 16; i++) 
                blueRoute.add(makeKey(blueEnd[0], blueEnd[1], i));
            
            // System.out.println(blueRoute.toString());
            return bfsFrom(redStart[0], redStart[1], blueRoute, true);
        }
    }
}