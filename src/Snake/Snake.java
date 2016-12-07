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
		System.out.print("�п�J��}�j�p : ");
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		map = new int[size][size];
		System.out.println();
		System.out.println("�п�J��}���Ʀr : ");
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
				System.out.println("���쩳�A�V�k���ʡC");
				break;
			} else if (map[row][col] == 0) {
				System.out.printf("�J���ê at (%d, %d), ���k���ʡC\n", row, col);
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
				System.out.printf("�L���i��\n");
				break;
			}
			while (row > -1) {

				if (map[row][col] == 0) {
					System.out.printf("�J���ê at (%d, %d), ���k����\n", row, col);
				}

				goUp();
				row = getRow();
			}

			// TODO goDown
			while (row > -1) {
				if (map[row][col] == 0) {
					System.out.printf("�J���ê at (%d, %d), ���k����\n", row, col);
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
 * 1. ��X�o�I
 * 2. �����(BreakPoint)
 * 3. ���W�B���U��
 * 4. �[�` sum
 * */
	int row = 0, col = 0, sum = 0;
	ArrayList<Breakpoint> bp = new ArrayList<Breakpoint>();
	int[][] map;
	boolean goUp = false;
	
	void setMap(int[][] map){
		this.map = map;
	}
	
	void setStart(){
		System.out.println("��X�o�I...");
		while (row < map.length) {
			if (row == map.length - 1) {
				System.out.println("�䤣��X�o�I");
				break;
			}else if(map[row][col]==0){
				break;
			}
			goDown();
			row = getRow();
		}
		System.out.printf("�X�o�I : %d, %d\n", row, col);
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