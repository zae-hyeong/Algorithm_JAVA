package gold.g2_19238_스타트택시;

import java.io.*;
import java.util.*;

class Customer implements Comparable<Customer> {
	int startY, startX, destY, destX;

	public Customer(int startY, int startX, int destY, int destX) {
		super();
		this.startY = startY;
		this.startX = startX;
		this.destY = destY;
		this.destX = destX;
	}

	@Override
	public int compareTo(Customer o) {
		if (this.startY == o.startY) {
			return Integer.compare(startX, o.startX);
		} else {
			return Integer.compare(startY, o.startY);
		}
	}

	@Override
	public String toString() {
		return String.format("start : [%d, %d], dest : [%d, %d]", this.startY, this.startX, this.destY, this.destX);
	}
}

class Car {
	int y, x, fuel, target;

	Car(int y, int x, int fuel) {
		this.y = y;
		this.x = x;
		this.fuel = fuel;
		this.target = -1;
	}
}

public class Main {
	static int N, M, FULL;
	static int[][] map;
	static Customer[] customers;
	static Set<Integer> visitedCustomer = new HashSet<>();
	static boolean[][] v;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int bfsFromTaxi(Car car) {

		ArrayDeque<int[]> queue = new ArrayDeque<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				v[i][j] = false;

		queue.add(new int[] { car.y, car.x, 0 });
		v[car.y][car.x] = true;

		int minFuel = Integer.MAX_VALUE;
		int curY = N, curX = N;
		int ny, nx;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[2] > minFuel)
				continue;

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

				if (0 <= ny && ny < N && 0 <= nx && nx < N && map[ny][nx] > -1) {
					v[ny][nx] = true;
					queue.add(new int[] { ny, nx, cur[2] + 1 });

					if (map[ny][nx] > 0 && !visitedCustomer.contains(map[ny][nx])) {
						if (minFuel < cur[2])
							continue;
						if (minFuel == cur[2] && curY < ny)
							continue;
						if (minFuel == cur[2] && curY == ny && curX < nx)
							continue;

						minFuel = cur[2];
						curY = ny;
						curX = nx;
					}
				}
			}
		}

		car.y = curY;
		car.x = curX;
		car.fuel -= minFuel;
		car.target = map[curY][curX];

		return minFuel;
	}
	
	static int bfsCustomer(Car car) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				v[i][j] = false;

		queue.add(new int[] { car.y, car.x, 0 });
		v[car.y][car.x] = true;

		int minFuel = Integer.MAX_VALUE;
		int curY = N, curX = N;
		int ny, nx;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[2] > minFuel)
				continue;

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

				if (0 <= ny && ny < N && 0 <= nx && nx < N && map[ny][nx] > -1) {
					v[ny][nx] = true;
					queue.add(new int[] { ny, nx, cur[2] + 1 });

					if (map[ny][nx] > 0 && !visitedCustomer.contains(map[ny][nx])) {
						if (minFuel < cur[2])
							continue;
						if (minFuel == cur[2] && curY < ny)
							continue;
						if (minFuel == cur[2] && curY == ny && curX < nx)
							continue;

						minFuel = cur[2];
						curY = ny;
						curX = nx;
					}
				}
			}
		}

		car.y = curY;
		car.x = curX;
		car.fuel -= minFuel;
		car.target = map[curY][curX];

		return minFuel;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_19238_스타트택시/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		FULL = Integer.parseInt(st.nextToken());

		v = new boolean[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1)
					map[i][j] = -1;
				else
					map[i][j] = num;
			}
		}

		int[] start = new int[2];
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		Car car = new Car(start[0], start[1], FULL);

		customers = new Customer[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			int dx = Integer.parseInt(st.nextToken()) - 1;
			customers[i] = new Customer(sy, sx, dy, dx);
			map[sy][sx] = i;
		}

		while (visitedCustomer.size() < M) {
			bfsFromTaxi(car);
			if (car.fuel <= 0) {
				System.out.println(-1);
				return;
			}

		}

		System.out.println(customers.toString());
		/*
		 * customers.sort(new Comparator<Customer>() {
		 * 
		 * @Override public int compare(Customer o1, Customer o2) { if (o1.startY !=
		 * o2.startY) { return Integer.compare(o1.startY, o2.startY); } else { return
		 * Integer.compare(o1.startX, o2.startX); } } });
		 */

		br.close();
	}

}
