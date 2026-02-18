package PCCP.카페_확장;

import java.util.*;

class Solution {
    public int solution(int[] menu, int[] order, int k) {
        int N = order.length;
        int answer = 0;
        int lastOrderSec = N * k + 1;

        int count = 1;
        int completeTime = 0;
        int orderIdx = 0;
        for(int sec = 0; sec <= lastOrderSec; sec++) {
            if(sec == completeTime) count = Math.max(0, count - 1);

            if(sec >= completeTime && orderIdx < N && sec >= orderIdx * k) { //다음 손님이 있고 + 그 손님이 들어왔을때
                completeTime = sec + menu[order[orderIdx++]];
            }

            if(sec % k == 0 && sec / k < N) count++;

            // System.out.println("sec : " + sec  + " -> count : " + count);
            answer = Math.max(answer, count);
        }

        return answer;
    }
}