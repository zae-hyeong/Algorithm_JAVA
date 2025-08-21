package d45.d4_5643;

import java.io.*;
import java.util.*;

class GraphClean {
    private final int n;
    private final List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    GraphClean(int n) {
        this.n = n;
        this.adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            this.adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int from, int to) {
        adj[from].add(to);
    }

    /**
     * start 정점에서 도달 가능한 정점 수(자기 자신 포함)를 반환.
     */
    int countReachable(int start) {
        boolean[] visited = new boolean[n + 1];
        return dfs(start, visited);
    }

    /**
     * 재귀 DFS. u를 처음 방문할 때 방문 표시 후 자식으로 확장.
     */
    private int dfs(int u, boolean[] visited) {
        if (visited[u]) return 0; // 이미 방문한 경우 중복 카운트 방지
        visited[u] = true;

        int count = 1; // 자신 포함
        for (int v : adj[u]) {
            count += dfs(v, visited);
        }
        return count;
    }
}

public class Solution_clean {
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("swexpert/d45/d4_5643/input.txt"));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int TC = Integer.parseInt(br.readLine().trim());

            for (int tc = 1; tc <= TC; tc++) {
                int N = Integer.parseInt(br.readLine().trim());
                int M = Integer.parseInt(br.readLine().trim());

                GraphClean g = new GraphClean(N);
                GraphClean rg = new GraphClean(N); // reverse graph

                for (int i = 0; i < M; i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    g.addEdge(from, to);
                    rg.addEdge(to, from); // 역방향 간선
                }

                int result = 0;
                for (int i = 1; i <= N; i++) {
                    // i에서 도달 가능한 노드 수 + i로 도달 가능한 노드 수 - 1(자기 자신 중복)
                    if (g.countReachable(i) + rg.countReachable(i) - 1 == N) {
                        result++;
                    }
                }

                sb.append('#').append(tc).append(' ').append(result).append('\n');
            }

            System.out.print(sb.toString());
        }
    }
}
