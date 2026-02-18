package PCCP.신입사원교육;

import java.util.*;

class Solution {
    public int solution(int[] ability, int number) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = ability.length;

        for(int i = 0 ; i < N; i++) {
            pq.add(ability[i]);
        }

        for(int i = 0 ; i < number; i++) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + b);
            pq.add(a + b);
        }

        int sum = 0;
        for(int i = 0 ; i < N; i++) {
            sum += pq.poll();
        }

        return sum;
    }
}