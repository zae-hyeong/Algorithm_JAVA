package PCCP.퍼즐게임챌린지;

class Solution {
    int[] diffs, times;
    int N;
    long limit;
    
    long getTime(int myLevel) {
        long totalTime = 0;
        
        for(int i = 0; i < N; i++) {
            if(diffs[i] <= myLevel) {
                totalTime += times[i];
                continue;
            }
            
            totalTime = (times[i] + times[i - 1]) * (diffs[i] - myLevel) +
            
            totalTime + times[i];
            // System.out.println("i : " + i + " / totalTime : "+ totalTime);
        }
        
        return totalTime;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        this.N = diffs.length;
        
        int front = 1;
        int rear = -1;
        for(int d: diffs) 
            rear = Math.max(rear, d);
        
        int level;
        while(front <= rear) {
            level = (front + rear) / 2;
            if(getTime(level) <= limit) {
                rear = level - 1;
            } else {
                front = level + 1;
            }
        }
        
        return front;
    }
}