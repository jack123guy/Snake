package Snake;

import java.util.Scanner;
import static java.lang.System.out;

public class Snake2 {
	int row, col;

	public static void main(String[] args) {
		Map m = new Map();
		WalkPath wp = new WalkPath(m.map);
	}
}

class TreeNode {
	TreeNode top, right, down, parent;
	int value;
	int key;

	TreeNode(int key, int value) {
		this.key = key;
		this.value = value;
	}

	int getValue() {
		return value;
	}

	TreeNode getParent() {
		return parent;
	}

}

class WalkPath {

	int[][] map;
	int key = 0;
	int row = 0, col = 0;
	TreeNode nowNode, root;

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
		out.printf("出發點為 : %d, %d。", row, col);
		root = new TreeNode(key++, map[row][col]);
		nowNode = root;
	}

	void setnowNodeTop() {
		if (nowNode.top == null || nowNode.top.key < this.key)
			return;
		nowNode.top.parent = nowNode;
		nowNode = nowNode.top;
	}

	void setnowNodeDown() {
		if (nowNode.down == null || nowNode.down.key < this.key)
			return;
		nowNode.down.parent = nowNode;
		nowNode = nowNode.down;
	}

	void setnowNodeRight() {
		if (nowNode.right == null || nowNode.right.key < this.key)
			return;
		nowNode.right.parent = nowNode;
		nowNode = nowNode.right;
	}

	void addTop() {
		// key++;
		if (row - 1 < 0 || map[row - 1][col] == 0) {
			nowNode.top = null;
			return;
		}
		TreeNode node = new TreeNode(key++, map[row - 1][col]);
		nowNode.top = node;
	}

	void addRight() {
		// key++;
		if (col + 1 == map.length || map[row][col + 1] == 0) {
			nowNode.right = null;
			return;
		}
		TreeNode node = new TreeNode(key++, map[row][col + 1]);
		nowNode.right = node;
	}

	void addDown() {
		// key++;
		if (row + 1 == map.length || map[row + 1][col] == 0) {
			nowNode.down = null;
			return;
		}
		TreeNode node = new TreeNode(key++, map[row + 1][col]);
		nowNode.down = node;
	}
	/*
	 * TreeNode Find(int key) { nowNode = root; while (nowNode.key != key) {
	 * 
	 * if (nowNode.right.key == key) { nowNode = nowNode.right; break; } if
	 * (nowNode.right.key < key) { if (nowNode.down.key == key) { nowNode =
	 * nowNode.down; } else if (nowNode.top.key == key) { nowNode = nowNode.top;
	 * } }
	 * 
	 * } return nowNode; }
	 */
}

class Map {
	int[][] map;
	int size;
	Scanner sc = new Scanner(System.in);

	Map() {
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