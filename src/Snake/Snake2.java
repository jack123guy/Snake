package Snake;

import java.util.Scanner;
import static java.lang.System.out;

public class Snake2 {

	public static void main(String[] args) {

		Map m = new Map();

		WalkPath wp = new WalkPath(m.map);

	}
}

enum direction {
	UP, DOWN, RIGHT;
}

class TreeNode {
	TreeNode top, right, down, parent;
	boolean topSignal = false, rightSignal = false, downSignal = false;
	int value;
	int key;

	TreeNode(int key, int value) {
		this.key = key;
		this.value = value;
	}

	int getValue() {
		return value;
	}
}

class WalkPath {

	int[][] map;
	int row = 0, col = 0, key = 0, walkTime = 0;
	int pathnum = 0; // 所有可能路徑數目，為零時就走完了
	boolean walkDownSignal = true;
	TreeNode nowNode, root;
	direction dir;

	WalkPath(int[][] map) {
		this.map = map;
		out.println("找出發點...");
		while (row < map.length) {
			if (map[row][col] != 0) {
				break;
			} else if (row == map.length - 1 && map[row][col] == 0) {
				out.println("找不到出發點");
				break;
			}
			row++;
		}
		out.printf("出發點為 : (%d, %d)。\n", row, col);
		root = new TreeNode(key++, map[row][col]);
		root.parent = root;
		nowNode = root;
		addAll();
		while (pathnum > 0) {
			out.printf("目前還有 %d 種可能未走完。\n", pathnum);
			if (walkDownSignal == true) {
				while (walkDownSignal == true) {
					if (nowNode.downSignal == true) {
						nowNode.downSignal = false;
						walk(direction.DOWN);
						out.printf("d現在位置 :(%d, %d);\n", row, col);
					}
				}
				out.println("1");
			} else if (walkDownSignal == false) {
				while (walkDownSignal == false) {
					if (nowNode.topSignal == true) {
						nowNode.topSignal = false;
						walk(direction.UP);
						out.printf("u現在位置 :(%d, %d);\n", row, col);
					}
				}
				out.println("2");
			}
			out.println("3");
			if (nowNode.rightSignal == true) {
				nowNode.rightSignal = false;
				out.printf("r現在位置 :(%d, %d);\n", row, col);
				walk(direction.RIGHT);
				walkDownSignal = !walkDownSignal;
				out.printf("r現在位置 :(%d, %d);\n", row, col);
			}
			out.printf("目前還有 %d 種可能未走完。\n", pathnum);
			goBack();
			// TODO Sum
		}
		out.printf("全部走完, 共有%d種可能。\n", walkTime);
	}

	void walk(direction dir) {
		this.dir = dir;
		switch (dir) {
		case UP:
			setNowNodeUp();
			addAll();
			break;
		case DOWN:
			setNowNodeDown();
			addAll();
			break;
		case RIGHT:
			setNowNodeRight();
			addAll();
			break;
		}
	}

	void goBack() {
		nowNode = nowNode.parent;
	}

	void addAll() {
		addTop();
		addDown();
		addRight();
	}

	int getRow() {
		return row;
	}

	int getCol() {
		return col;
	}

	void setNowNodeUp() {
		if (row-1 <0 || map[row-1][col] == 0) {
			out.println("U Revers");
			walkDownSignal = true;
			return;
		}

		out.println("UP");
		nowNode.top.parent = nowNode;
		nowNode = nowNode.top;
		row--;
		pathnum--;
	}

	void setNowNodeDown() {
		if (row + 1 == map.length || map[row+1][col] == 0) {
			out.println("D Revers");
			walkDownSignal = false;
			return;
		}
		out.println("DOWN");
		nowNode.down.parent = nowNode;
		nowNode = nowNode.down;
		row++;
		pathnum--;
	}

	void setNowNodeRight() {
		if (nowNode.right == null)
			return;
		out.println("RIGHT");
		nowNode.right.parent = nowNode;
		nowNode = nowNode.right;
		col++;
		pathnum--;
	}

	void addTop() {
		// key++;
		if (row - 1 < 0 || map[row - 1][col] == 0) {
			out.println("向上無路走");
			nowNode.top = null;
			return;
		} else if (walkDownSignal == true) {
			out.println("向上回到母節點");
			return;
		}
		TreeNode node = new TreeNode(key++, map[row - 1][col]);
		out.println("向上有路可走");
		nowNode.top = node;
		nowNode.topSignal = true;
		pathnum++;
		walkTime++;
	}

	void addRight() {
		// key++;
		if (col + 1 == map.length || map[row][col + 1] == 0) {
			out.println("向右無路走");
			nowNode.right = null;
			return;
		}
		TreeNode node = new TreeNode(key++, map[row][col + 1]);
		out.println("向右有路可走");
		nowNode.right = node;
		nowNode.rightSignal = true;
		pathnum++;
		walkTime++;
	}

	void addDown() {
		// key++;
		if (row + 1 == map.length || map[row + 1][col] == 0) {
			out.println("向下無路走");
			nowNode.down = null;
			return;
		} else if (walkDownSignal == false) {
			out.println("向下回到母節點");
			return;
		}
		TreeNode node = new TreeNode(key++, map[row + 1][col]);
		out.println("向下有路可走");
		nowNode.down = node;
		nowNode.downSignal = true;
		pathnum++;
		walkTime++;
	}
}

class Map {
	int[][] map;
	int size;

	Map() {
		Scanner sc = new Scanner(System.in);
		out.print("請輸入地圖大小 : ");
		size = sc.nextInt();
		map = new int[size][size];

		out.printf("\n請輸入格子內的值 : \n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}
}