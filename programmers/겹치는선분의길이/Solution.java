package 겹치는선분의길이;

import java.util.*;

/**
 * 스위프 라인 알고리즘을 사용하여 겹치는 선분의 길이를 계산하는 솔루션입니다.
 * 시간 복잡도: O(N log N) (이벤트 정렬이 지배적)
 * 공간 복잡도: O(N) (이벤트를 저장할 리스트)
 */
public class Solution {
    
    /**
     * 선분의 시작점 또는 끝점을 나타내는 '이벤트' 클래스입니다.
     * 내부 클래스 또는 간단한 int[]를 사용할 수도 있습니다.
     */
    class Event implements Comparable<Event> {
        int coordinate; // 좌표
        int type;       // 이벤트 타입: +1 (시작), -1 (끝)

        Event(int coordinate, int type) {
            this.coordinate = coordinate;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            // 1. 좌표(coordinate) 기준으로 오름차순 정렬
            if (this.coordinate != other.coordinate) {
                // coordinate가 int 범위를 넘지 않으므로 간단히 뺄셈 사용 가능
                // 만약 long이라면 Integer.compare(this.coordinate, other.coordinate) 사용
                return this.coordinate - other.coordinate;
            }
            
            // 2. 좌표가 같다면, 시작 이벤트(+1)가 끝 이벤트(-1)보다 먼저 오도록
            //    type을 기준으로 내림차순 정렬합니다. (1이 -1보다 먼저 옴)
            return other.type - this.type;
        }
    }

    public long solution(int[][] lines) {
        List<Event> events = new ArrayList<>();
        
        // "이벤트" 생성
        for (int[] line : lines) {
            events.add(new Event(line[0], 1));  // 시작 이벤트
            events.add(new Event(line[1], -1)); // 끝 이벤트
        }

        Collections.sort(events);

        long totalLength = 0;
        int overlapCount = 0;
        int lastCoordinate = events.get(0).coordinate;

        for (Event event : events) {
            int segmentLength = event.coordinate - lastCoordinate;  // A. 이전 이벤트(lastCoordinate)부터 현재 이벤트(currentCoordinate)까지의 구간 계산

            if (overlapCount >= 2) {  // B. 이전 구간의 overlapCount가 2 이상이었는지 확인(이전 이벤트 ~ 현재 이벤트 사이 구간)
                totalLength += (long) segmentLength;
            }

            overlapCount += event.type;  // C. 현재 이벤트 타입을 적용하여 overlapCount 업데이트
            
            lastCoordinate = event.coordinate;  // D. 마지막 좌표 업데이트
        }

        return totalLength;
    }
}