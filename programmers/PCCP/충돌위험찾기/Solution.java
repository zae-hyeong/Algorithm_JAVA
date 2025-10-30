package PCCP.충돌위험찾기;

import java.util.*;

class Solution {

    public int solution(int[][] points, int[][] routes) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int[] route : routes) {
            LinkedList<Integer> dests = new LinkedList<>();
            for (int r : route)
                dests.add(r - 1);

            int sec = 0;
            int curIdx = dests.poll();
            int y = points[curIdx][0];
            int x = points[curIdx][1];
            while (!dests.isEmpty()) {
                String key = y + "," + x + "," + sec;
                sec++;

                map.put(key, map.getOrDefault(key, 0) + 1);

                int destIdx = dests.get(0);

                if (y == points[destIdx][0] && x == points[destIdx][1]) {
                    dests.pollFirst();
                    if (dests.isEmpty())
                        break;
                    destIdx = dests.get(0);
                }

                if (y != points[destIdx][0]) {
                    y = y > points[destIdx][0] ? y - 1 : y + 1;
                    continue;
                }

                if (x != points[destIdx][1]) {
                    x = x > points[destIdx][1] ? x - 1 : x + 1;
                    continue;
                }
            }
        }

        int result = 0;
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            // System.out.println(entry.toString());
            if (entry.getValue() > 1)
                result++;
        }

        return result;
    }
}