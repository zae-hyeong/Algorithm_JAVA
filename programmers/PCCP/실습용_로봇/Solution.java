package PCCP.실습용_로봇;
import java.util.*;

class Solution {
    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};

    public int[] solution(String command) {
        int dir = 0;
        int[] answer = {0, 0};
        for(int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            switch(c) {
                case 'R':
                    dir = (dir + 1) % 4;  
                    break;
                case 'L':
                    if(--dir < 0) dir = 3;
                    break;
                case 'G':
                    answer[0] += dx[dir];
                    answer[1] += dy[dir];
                    break;
                case 'B':
                    answer[1] -= dy[dir];
                    answer[0] -= dx[dir];
                    break;
                default:
                    break;
            }
            // System.out.println(Arrays.toString(answer));
        }
        
        return answer;
    }
}