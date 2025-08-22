package d456.d5_1247;

import java.io.*;
import java.util.*;

public class Solution2 {
    static int N;
    static int[][] customers;      // [i][0]=y, [i][1]=x
    static int[] company = new int[2];
    static int[] home = new int[2];

    static int[][] distCC;         // 고객↔고객 맨해튼 거리
    static int[] distFromCompany;  // 회사→고객
    static int[] distToHome;       // 고객→집

    static int manhattan(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }

    static void buildDistances() {
        distCC = new int[N][N];
        distFromCompany = new int[N];
        distToHome = new int[N];

        for (int i = 0; i < N; i++) {
            distFromCompany[i] = manhattan(company[0], company[1], customers[i][0], customers[i][1]);
            distToHome[i] = manhattan(customers[i][0], customers[i][1], home[0], home[1]);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distCC[i][j] = manhattan(customers[i][0], customers[i][1], customers[j][0], customers[j][1]);
            }
        }
    }

    static int solve() {
        buildDistances();

        int FULL = 1 << N;
        final int INF = 1_000_000_000;

        // dp[mask][i] : mask 방문 완료, 마지막이 i일 때 최소비용
        int[][] dp = new int[FULL][N];
        for (int[] row : dp) Arrays.fill(row, INF);

        // 초기화: 회사 → 각 고객 i
        for (int i = 0; i < N; i++) {
            dp[1 << i][i] = distFromCompany[i];
        }

        // 전이
        for (int mask = 1; mask < FULL; mask++) {
            for (int last = 0; last < N; last++) {
                if ((mask & (1 << last)) == 0) continue;   // last가 mask 안에 있어야 함
                int cur = dp[mask][last];
                if (cur == INF) continue;

                // 다음 고객 next 선택
                int rem = (~mask) & (FULL - 1);
                for (int next = rem; next != 0; next &= (next - 1)) {
                    int j = Integer.numberOfTrailingZeros(next);
                    int nmask = mask | (1 << j);
                    int cand = cur + distCC[last][j];
                    if (cand < dp[nmask][j]) dp[nmask][j] = cand;
                }
            }
        }

        // 마지막 고객에서 집으로
        int ans = INF;
        int ALL = FULL - 1;
        for (int last = 0; last < N; last++) {
            ans = Math.min(ans, dp[ALL][last] + distToHome[last]);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            company[0] = Integer.parseInt(st.nextToken());
            company[1] = Integer.parseInt(st.nextToken());
            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());

            customers = new int[N][2];
            for (int i = 0; i < N; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }

            int result = solve();
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }
}
