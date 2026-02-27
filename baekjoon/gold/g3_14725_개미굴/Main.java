package gold.g3_14725_개미굴;

import java.util.*;
import java.io.*;

public class Main {
	
	static Map<String, Map> graph = new HashMap<>();
	
	static void dfs(StringBuffer sb, Map<String, Map> g, String prefix) {
		if(g.isEmpty()) return;
		
		List<String> list = new ArrayList<>(g.keySet());
		Collections.sort(list);
		
		for(String str: list) {
			sb.append(prefix).append(str).append("\n");
			dfs(sb, g.get(str), prefix + "--");
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./solving/gold/g3_14725_개미굴/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		graph.put("start", new HashMap<>());
		
		final int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		String str, parent;
		
		Map<String, Map> map = graph;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int M = Integer.parseInt(st.nextToken());
			parent = "start";
			map = graph;
			
			for(int j = 0; j < M; j++) {
				str = st.nextToken();
				
				if(!map.get(parent).containsKey(str)) {
					map.get(parent).put(str, new HashMap<String, Map>());
				}

				map = map.get(parent);
				parent = str;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		dfs(sb, graph.get("start"), "");

		System.out.println(sb.toString());
		br.close();
	}
}
