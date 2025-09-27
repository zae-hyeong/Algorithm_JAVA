package algorithm.test;

import java.util.*;

class auto2_hyun {

	public static void main(String[] args) {
		System.out.println("---[ Test Case 1: 기본 예제 ]---");
		String base1 = "iron_ore";
		String[] proc1 = { "iron_ore 1 iron_ingot 1", "iron_ingot 2 iron_plate 1" };
		int[] time1 = { 3, 2 };
		String target1 = "iron_plate";
		int qty1 = 3;
		int expected1 = 11;
		int result1 = solution(base1, proc1, time1, target1, qty1);
		System.out.println(
				"기대 결과: " + expected1 + " / 실제 결과: " + result1 + " -> " + (result1 == expected1 ? "PASS" : "FAIL"));
		System.out.println();

		System.out.println("---[ Test Case 2: 병목 공정 테스트 ]---");
		String base2 = "a";
		String[] proc2 = { "a 1 b 1", "a 1 c 1", "b 1 c 1 d 1" };
		int[] time2 = { 2, 5, 1 };
		String target2 = "d";
		int qty2 = 1;
		int expected2 = 7;
		int result2 = solution(base2, proc2, time2, target2, qty2);
		System.out.println(
				"기대 결과: " + expected2 + " / 실제 결과: " + result2 + " -> " + (result2 == expected2 ? "PASS" : "FAIL"));
		System.out.println();

		System.out.println("---[ Test Case 3: 기초 자원 제약 테스트 ]---");
		String base3 = "sand";
		String[] proc3 = { "sand 10 glass 1" };
		int[] time3 = { 1 };
		String target3 = "glass";
		int qty3 = 2;
		int expected3 = 21;
		int result3 = solution(base3, proc3, time3, target3, qty3);
		System.out.println(
				"기대 결과: " + expected3 + " / 실제 결과: " + result3 + " -> " + (result3 == expected3 ? "PASS" : "FAIL"));
		System.out.println();

		System.out.println("---[ Test Case 4: 복잡한 수량 계산 테스트 ]---");
		String base4 = "log";
		String[] proc4 = { "log 3 plank 5", "plank 2 chair 1" };
		int[] time4 = { 4, 10 };
		String target4 = "chair";
		int qty4 = 3;
		int expected4 = 20;
		int result4 = solution(base4, proc4, time4, target4, qty4);
		System.out.println(
				"기대 결과: " + expected4 + " / 실제 결과: " + result4 + " -> " + (result4 == expected4 ? "PASS" : "FAIL"));
		System.out.println();
	}

	// 생산 품목을 만들어내는 공정 정보를 담은 일종의 그래프
	static Map<String, Process> G;
	// 생산 품목을 특정 개수만큼 생성하려면 얼마나 걸리는 지 기록(없으면 시간초과?일?수?도? 엄밀히 계산 안해봄 ㅋㅋ.. 정당성 자체에는 큰
	// 의미 없음)
	static Map<String, Map<Integer, Integer>> dp;

	// 생산 품목을 만들기 위한 공정의 시간, 생산품의 개수, 필요한 재료의 목록
	static class Process {
		List<Block> needs;
		int time;
		int qty;

		public Process(List<Block> needs, int time, int qty) {
			this.needs = needs;
			this.time = time;
			this.qty = qty;
		}
	}

	// 필요한 재료의 이름과 수량
	static class Block {
		String from;
		int qty;

		public Block(String from, int qty) {
			this.from = from;
			this.qty = qty;
		}
	}

	// 가장 첫 기초 재료의 이름
	static String bName;

	public static int solution(String base_name, String[] process, int[] time_taken, String target_name,
			int target_qty) {
		G = new HashMap<>();
		dp = new HashMap<>();

		bName = base_name;

		// 개 열받는 형식의 입력
		for (int i = 0; i < process.length; i++) {
			List<Block> ps = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(process[i]);

			String f = st.nextToken();
			int q = Integer.parseInt(st.nextToken());
			ps.add(new Block(f, q));
			if (st.countTokens() == 4) {
				f = st.nextToken();
				q = Integer.parseInt(st.nextToken());
				ps.add(new Block(f, q));
			}

			f = st.nextToken();
			q = Integer.parseInt(st.nextToken());
			G.put(f, new Process(ps, time_taken[i], q));
		}

		// 최종 목적 상품의 이름과 수량
		return dfs(target_name, target_qty);
	}

	private static int dfs(String rName, int rQty) {
		// 가장 첫 기초 재료인 경우 그냥 그 수량(=구비하는 데 걸리는 시간)을 리턴
		if (rName.equals(bName))
			return rQty;

		// DP 적용
		Map<Integer, Integer> map = dp.computeIfAbsent(rName, k -> new HashMap<>());
		Integer result = map.get(rQty);
		if (result != null)
			return result;

		// 원하는 상품을 위한 공정 조회
		Process p = G.get(rName);
		int rq = p.qty;
		int rc = rQty / rq + (rQty % rq > 0 ? 1 : 0);

		// 필요한 재료 중에서 가장 오래 걸리는 녀석 찾기
		int ret = Integer.MIN_VALUE;
		for (Block b : p.needs) {
			String f = b.from;
			int q = b.qty;
			ret = Math.max(ret, dfs(f, q * rc));
		}

		// 필요한 재료 중에서 가장 오래 걸리는 녀석 + 공정의 시간
		result = ret + p.time;
		// DP 적용
		map.put(rQty, result);
		return result;
	}
}
