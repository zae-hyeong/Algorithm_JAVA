package gold.g4_2239;

import java.util.*;
import java.io.*;

public class Main {
	static int[][] board;
	static boolean[][] col, row, room;
	
	static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void sudoku(int rowNum, int colNum) {
//		System.out.println(rowNum + " : "+ colNum);
		
		if(rowNum == 9) {
			printBoard();
			System.exit(0);
		}
		
		if (colNum == 9) {
			sudoku(rowNum + 1, 0);
			return;
		}

		if (board[rowNum][colNum] != 0) {
			sudoku(rowNum, colNum + 1);
			return;
		}
		
		for (int num = 1; num <= 9; num++) {
			if (col[num][colNum] || row[num][rowNum] || room[num][roomMapper(rowNum, colNum)])
				continue;
			
			board[rowNum][colNum] = num;
			col[num][colNum] = true;
			row[num][rowNum] = true;
			room[num][roomMapper(rowNum, colNum)] = true;
			sudoku(rowNum, colNum + 1);
			
			board[rowNum][colNum] = 0;
			col[num][colNum] = false;
			row[num][rowNum] = false;
			room[num][roomMapper(rowNum, colNum)] = false;
		}
		return;
	}

	static int roomMapper(int y, int x) {
		return ((y / 3) * 3) + (x / 3);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj/bj_2239/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		col = new boolean[10][9];
		row = new boolean[10][9];
		room = new boolean[10][9];
		String tmp;
		for (int i = 0; i < 9; i++) {
			tmp = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(Character.toString(tmp.charAt(j)));
				if(board[i][j] == 0) continue;
				
				col[board[i][j]][j] = true;
				row[board[i][j]][i] = true;
				room[board[i][j]][roomMapper(i, j)] = true;
			}
		}
		
		br.close();
		sudoku(0, 0);
	}
}
