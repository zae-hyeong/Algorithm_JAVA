package bj.bj_9249;

import java.io.*;
import java.util.*;

/**
 * 백준 9249: 최장 공통 부분 문자열 (Longest Common Substring)
 * - 문자열 A, B를 읽어 C = A + '$' + B 로 합친 뒤
 * - 접미사 배열(SA)을 두 배 증가(doubling) 방식으로 O(n log n)에 구성
 * - Kasai 알고리즘으로 LCP 배열 O(n) 계산
 * - SA에서 인접한 두 접미사가 서로 다른 문자열에서 왔을 때의 LCP 최댓값을 답으로 선택
 */
public class Main2 {
    static String A, B, C;
    static int al, bl;          // A, B 길이
    static int idx = 0, ans = 0; // 정답 길이, 시작 위치(원문 C에서의 인덱스)

    // 작업용 배열들
    static int[] g, ng;       // 현재/다음 그룹(랭크)
    static int[] sfx;         // 접미사 배열 (suffix array)
    static int[] ordered;     // 카운팅 정렬 중간 배열
    static int[] cnt;         // 카운팅 정렬 카운터
    static int[] lcp;         // 인접 접미사 쌍의 LCP

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();

        al = A.length();
        bl = B.length();

        // 구분자 '$'는 A, B에 등장하지 않는 문자여야 함 (백준 9249 조건에서 안전)
        C = A + '$' + B;

        buildSuffixAndLCP(C);
        findLCS(C);

        // 출력: 길이, 그리고 해당 부분 문자열
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n');
        if (ans > 0) sb.append(C, idx, idx + ans);
        System.out.print(sb.toString());
    }

    /**
     * 접미사 배열(SA)과 LCP를 모두 구성한다.
     * - SA: doubling + 두 번의 카운팅 정렬(2-키 정렬)
     * - LCP: Kasai 알고리즘
     */
    static void buildSuffixAndLCP(String s) {
        final int n = s.length();

        // mx: 카운팅 정렬에서 사용할 버킷 수
        // 초기에는 문자 코드 범위(<=256)와 n+1 중 큰 값 사용
        int mx = Math.max(257, n + 1);

        g = new int[n + 1];   // g[n] = 0 (guard, 범위 밖 랭크)
        ng = new int[n + 1];
        sfx = new int[n];
        ordered = new int[n];
        cnt = new int[mx];

        // 초기 그룹: 문자의 코드값(안정적으로 카운팅 정렬 가능)
        for (int i = 0; i < n; ++i) g[i] = s.charAt(i);

        // t는 비교 길이의 절반 (현재는 1, 다음 라운드에 2, 4, 8 …)
        // 매 라운드마다 (g[i], g[i+t]) 두 키로 접미사를 정렬한다.
        for (int t = 1, p = 1; t < n; t <<= 1) {
            // --- 두 번째 키 g[i+t]로 카운팅 정렬 (stable) ---
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; ++i) {
                int key = g[Math.min(i + t, n)]; // 범위를 넘으면 g[n]=0
                cnt[key]++;
            }
            for (int i = 1; i < mx; ++i) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; --i) {
                int key = g[Math.min(i + t, n)];
                ordered[--cnt[key]] = i;
            }

            // --- 첫 번째 키 g[i]로 카운팅 정렬 (stable) ---
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; ++i) cnt[g[i]]++;
            for (int i = 1; i < mx; ++i) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; --i) {
                int firstKeyIdx = ordered[i];
                int key = g[firstKeyIdx];
                sfx[--cnt[key]] = firstKeyIdx;
            }

            // 새 그룹(랭크) 계산
            p = 1;
            ng[sfx[0]] = 1;
            for (int i = 1; i < n; ++i) {
                int a = sfx[i - 1], b = sfx[i];
                if (lessInPair(a, b, t, n)) {
                    p++;
                    ng[b] = ng[a] + 1; // 새로운 그룹
                } else {
                    ng[b] = ng[a];     // 같은 그룹
                }
            }
            // 다음 라운드를 위해 교체
            g = Arrays.copyOf(ng, n + 1);

            // 모든 접미사가 서로 다른 그룹에 들어갔다면 정렬 완료
            if (p == n) break;
        }

        // --- Kasai 알고리즘으로 LCP 계산 ---
        lcp = new int[n];
        int[] rank = new int[n];       // rank[i]: 접미사 i의 SA 내 위치
        for (int i = 0; i < n; ++i) rank[sfx[i]] = i;

        int k = 0;
        for (int i = 0; i < n; ++i) {
            int r = rank[i];
            if (r == n - 1) { // 마지막 접미사는 LCP 없음
                k = Math.max(k - 1, 0);
                continue;
            }
            int j = sfx[r + 1];
            // i와 j의 공통 접두사 길이 증가
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) k++;
            lcp[r] = k;
            if (k > 0) k--; // 다음 비교를 위해 한 글자 줄임
        }
    }

    /**
     * (g[i], g[i+t]) < (g[j], g[j+t]) 인지 비교 (두 키 비교)
     * 범위를 넘는 인덱스는 0으로 간주하기 위해 g[n]을 0으로 둠.
     */
    static boolean lessInPair(int i, int j, int t, int n) {
        if (g[i] != g[j]) return g[i] < g[j];
        int gi = (i + t <= n) ? g[i + t] : 0;
        int gj = (j + t <= n) ? g[j + t] : 0;
        return gi < gj;
    }

    /**
     * SA와 LCP를 이용해 A와 B 사이의 최장 공통 부분 문자열을 찾는다.
     * - C = A + '$' + B
     * - SA에서 인접 접미사 두 개가 서로 다른 문자열에서 시작했다면,
     *   그 위치의 LCP가 두 문자열의 공통 접두사(=공통 부분문자열 후보) 길이.
     * - 그런 후보들 중 최댓값과 시작 위치를 갱신.
     */
    static void findLCS(String s) {
        int n = s.length();
        for (int i = 0; i < n - 1; ++i) {
            int x = sfx[i];
            int y = sfx[i + 1];

            // 구분자 위치 제외 ($ 는 al 위치)
            if (x == al || y == al) continue;

            // 서로 다른 문자열에서 시작하는지 확인:
            // x < al  <-> A의 접미사,  x > al  <-> B의 접미사
            if ((x < al) != (y < al)) {
                int cand = lcp[i];

                // 필요 시 구분자를 넘지 않도록 안전장치 (이 문제에선 lcp가 자동으로 멈춤)
                // cand = Math.min(cand, Math.min(al - Math.min(x, al), n - Math.max(y, al + 1)));

                if (cand > ans) {
                    ans = cand;
                    idx = x < y ? x : y; // 실제 공통 부분문자열의 시작 위치
                }
            }
        }
    }
}