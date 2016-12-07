package Snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Snake {

	public static void main(String[] args) {
		System.out.println("Snake Project Start;");
		MapCreate mc = new MapCreate();
		mc.showMap();
		int[][] map = mc.getMap();

		
		/*
		 * System.out.println(Arrays.toString(walk.tc.toArray())); for (int i =
		 * 0; i < walk.tc.size(); i++) { System.out.printf("(%d, %d)\t",
		 * walk.tc.get(i).x, walk.tc.get(i).y); System.out.println(); }
		 */
	}
}

class MapCreate {
	int[][] map;
	int size, runtime=0;
	int num = 0;
	MapCreate() {
		System.out.print("請輸入方陣大小 : ");
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		map = new int[size][size];
		System.out.println();
		System.out.println("請輸入方陣內數字 : ");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}

	int[][] getMap() {
		return map;
	}

	void showMap() {
		while (num < size) {
			for (int elem : map[num]) {
				System.out.printf("%d\t", elem);
			}
			System.out.println('\n');
			num++;
		}
	}
}

class WalkRule extends Rule{
	int row = super.row, col = super.col;
	int[][] map;
	int sum=0;
	// ArrayList<Track> tc = new ArrayList<Track>();

	void firstColRule() {
		while (row < map.length) {
			// tc.add(new Track(row, col));
			sum += map[row][col];
			goDown();
			row = getRow();
			if (row == map.length) {
				System.out.println("走到底，向右移動。");
				break;
			} else if (map[row][col] == 0) {
				System.out.printf("遇到障礙 at (%d, %d), 往右移動。\n", row, col);
				break;
			}
		}
		goRight();
		col = getCol();
	}

	void secColRule() {
		int row, col;
		row = getRow();
		col = getCol();

		// TODO goUp
		while (col < map.length - 1) {
			if (map[row][col] == 0) {
				System.out.printf("無路可走\n");
				break;
			}
			while (row > -1) {

				if (map[row][col] == 0) {
					System.out.printf("遇到障礙 at (%d, %d), 往右移動\n", row, col);
				}

				goUp();
				row = getRow();
			}

			// TODO goDown
			while (row > -1) {
				if (map[row][col] == 0) {
					System.out.printf("遇到障礙 at (%d, %d), 往右移動\n", row, col);
				}
				goUp();
				row = getRow();
			}
		}

	}

	void lastColRule() {

	}
}

class Rule extends Thread{
/*
 * 1. 找出發點
 * 2. 找分支(BreakPoint)
 * 3. 往上、往下走
 * 4. 加總 sum
 * */
	int row = 0, col = 0, sum = 0;
	ArrayList<Breakpoint> bp = new ArrayList<Breakpoint>();
	int[][] map;
	boolean goUp = false;
	
	void setMap(int[][] map){
		this.map = map;
	}
	
	void setStart(){
		System.out.println("找出發點...");
		while (row < map.length) {
			if (row == map.length - 1) {
				System.out.println("找不到出發點");
				break;
			}else if(map[row][col]==0){
				break;
			}
			goDown();
			row = getRow();
		}
		System.out.printf("出發點 : %d, %d\n", row, col);
	}
	
	void goUp() {
		
	}

	void goDown() {
		
	}

	void goRight() {
		
	}

	int getRow() {
		return row;
	}

	int getCol() {
		return col;
	}
}

/*
 * class Track { int x, y;
 * 
 * Track(int x, int y) { this.x = x; this.y = y; } }
 */
class Breakpoint {
	int row, col;
	boolean UpDown;// Up = true;Down = false;
}