package PCCP.동영상재생기;

class Solution {
    int StringToSec(String input ) {
        String[] time = input.split(":");
        int min = Integer.parseInt(time[0]);
        int sec = Integer.parseInt(time[1]);
        
        return 60 * min + sec;
    }
    
    String SecToString(int input) {
        String min = String.format("%d", input / 60);
        String sec = String.format("%d", input % 60);
        
        if(min.length() < 2) min = "0" + min;
        if(sec.length() < 2) sec = "0" + sec;
        
        return min + ":" + sec;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = StringToSec(video_len);
        int cur = StringToSec(pos);
        int opStart = StringToSec(op_start);
        int opEnd = StringToSec(op_end);
        
        for(String cmd : commands) {
            if(opStart <=cur && cur <= opEnd) {
                cur = opEnd;
            }
            
            switch(cmd) {
                case "next":      
                    cur = Math.min(cur + 10, videoLen);
                    break;
                case "prev":
                    cur = Math.max(0, cur - 10);
                    break;
                default :
                    break;
            }
            if(opStart <=cur && cur <= opEnd) {
                cur = opEnd;
            }
        }
        
        return SecToString(cur);
    }
}
