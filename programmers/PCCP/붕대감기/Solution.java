package PCCP.붕대감기;

class Solution {
    public int solution(int[] bandage, int maxHealth, int[][] attacks) {
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        int curHp = maxHealth;
        int sec = 0;
        int success = 0;
        int attackIdx = 0;
        while(true) {
            sec++;
            if(sec == attacks[attackIdx][0]) {
                success = 0;
                curHp -= attacks[attackIdx++][1];
                if(curHp <= 0) return -1;
                if(attackIdx == attacks.length) return curHp;
                
                continue;
            }
            
            curHp = Math.min(maxHealth, curHp + x);
            success++;
            if(success == t) {
                curHp = Math.min(maxHealth, curHp + y);
                success = 0;
            }
        }
        
    }
}