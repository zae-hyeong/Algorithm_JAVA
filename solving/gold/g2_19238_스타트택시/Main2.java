package gold.g2_19238_스타트택시;

import java.io.*;
import java.util.*;
/*
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

	boolean hasFuel() {
		return this.fuel > 0;
	}
}

public class Main2 {
	static int N, M, FUEL;
	static final int WALL = -1249071209;
	static int[][] map;
	static Customer[] customers;
	static boolean[] visitedCustomer;
	static boolean[][] v;

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static boolean isAvailable(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && map[y][x] != WALL;
	}

	static int bfsFromTaxi(Car car) { // return : 다음으로 태울 승객
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				v[i][j] = false;

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { car.y, car.x, 0 });
		v[car.y][car.x] = true;

		int ny, nx;
		int minDist = Integer.MAX_VALUE;
		int target = Integer.MAX_VALUE;
		int targetY = -1, targetX = -1;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[2] > minDist)
				continue;

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

//				if(isAvailable(ny, nx))System.out.println("ny : " + ny + " / nx : " + nx + " / isAvailable(ny, nx) : " + isAvailable(ny, nx) + " / !v[ny][nx] : " + !v[ny][nx] + " / map[ny][nx] != WALL  : " +  (map[ny][nx] != WALL));
				if (isAvailable(ny, nx) && !v[ny][nx]) {
					v[ny][nx] = true;
					queue.add(new int[] { ny, nx, cur[2] + 1 });

					if (map[ny][nx] > 0 && !visitedCustomer[map[ny][nx]]) {
						if (cur[2] + 1 <= minDist && map[ny][nx] < target) {
							minDist = cur[2] + 1;
							target = map[ny][nx];
							targetY = ny;
							targetX = nx;
						}
					}
				}
			}
		}

//		if()tar
		car.y = targetY;
		car.x = targetX;
		car.fuel -= minDist;
		car.target = target;

		return target;
	}

	static boolean bfsCustomer(Car car) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				v[i][j] = false;

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { car.y, car.x, 0 });
		v[car.y][car.x] = true;
		int target = -1 * car.target;

		int ny, nx;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

//				if(isAvailable(ny, nx))System.out.println("ny : " + ny + " / nx : " + nx + " / isAvailable(ny, nx) : " + isAvailable(ny, nx) + " / !v[ny][nx] : " + !v[ny][nx] + " / map[ny][nx] != WALL  : " +  (map[ny][nx] != WALL));
				if (isAvailable(ny, nx) && !v[ny][nx]) {
					if (map[ny][nx] == target) {
						visitedCustomer[car.target] = true;
						car.y = ny;
						car.x = nx;
						car.fuel -= (cur[2] + 1);
						if (car.fuel >= 0) {
							car.fuel += (cur[2] + 1) * 2;
						}
						car.target = -1;
						return true;
					}

					v[ny][nx] = true;
					queue.add(new int[] { ny, nx, cur[2] + 1 });
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g2_19238_스타트택시/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		FUEL = Integer.parseInt(st.nextToken());

		v = new boolean[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1)
					map[i][j] = WALL;
				else
					map[i][j] = 0;
			}
		}

		st = new StringTokenizer(br.readLine());
		Car car = new Car(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, FUEL);

		customers = new Customer[M + 1];
		visitedCustomer = new boolean[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			int dx = Integer.parseInt(st.nextToken()) - 1;
			customers[i] = new Customer(sy, sx, dy, dx);
			map[sy][sx] = i;
			map[dy][dx] = -i;
		}
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					int prev = map[i][j];
					map[customers[prev].destY][customers[prev].destX] = -1 * num;
					map[i][j] = num++;
				}
			}
		}

//		for (int[] a : map) System.out.println(Arrays.toString(a));

		for (int i = 0; i < M; i++) {
			System.out.println("###################");
			int target = bfsFromTaxi(car);

			System.out.println("target : " + target + " / fuel : " + car.fuel);
			System.out.println("cur car pos : " + car.y + ", " + car.x);
			if (target == Integer.MAX_VALUE ||car.fuel <= 0) {
				System.out.println(-1);
				return;
			}

			boolean flag = bfsCustomer(car);
			System.out.println("----------");
			System.out.println("next / fuel : " + car.fuel);
			System.out.println("cur car pos : " + car.y + ", " + car.x + " / falg : " + flag);
			if (!flag || car.fuel <= 0) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(car.fuel);

		br.close();
	}

}
*/
