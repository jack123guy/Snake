package Snake;

import java.util.Scanner;
import static java.lang.System.out;

public class Snake2 {

	public static void main(String[] args) {
		int row = 0, col = 0;
		Map m = new Map();
		TreeNode nextNode;
		WalkPath wp = new WalkPath(m.map);

	}
}

enum direction {
	UP, DOWN, RIGHT;
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
}

class WalkPath {

	int[][] map;
	int row = 0, col = 0, key = 0, walkTime = 0;
	int pathnum = 0; // �Ҧ��i����|�ƥءA���s�ɴN�����F
	boolean DownSignal = true;
	TreeNode nowNode, root;
	direction dir;

	WalkPath(int[][] map) {
		this.map = map;
		out.println("��X�o�I...");
		while (row < map.length) {
			if (map[row][col] != 0) {
				break;
			} else if (row == map.length - 1 && map[row][col] == 0) {
				out.println("�䤣��X�o�I");
				break;
			}
			row++;
		}
		out.printf("�X�o�I�� : %d, %d�C", row, col);
		root = new TreeNode(key++, map[row][col]);
		nowNode = root;
		addAll();
		while (pathnum > 0) {
			if (DownSignal == true) {
				while (nowNode.down != null)
					walk(direction.DOWN);
				if (nowNode.right == null)
					goBack();
			} else {
				while (nowNode.top != null)
					walk(direction.UP);
				if (nowNode.right == null)
					goBack();
			}
			// Sum
			if(col == map.length-1){
				
			}
			walk(direction.RIGHT);
			DownSignal = !DownSignal;
		}
	}

	void walk(direction dir) {
		this.dir = dir;
		switch (dir) {
		case UP:
			setNowNodeUp();
			addAll();
		case DOWN:
			setNowNodeDown();
			addAll();
		case RIGHT:
			setNowNodeRight();
			addAll();
		}
	}

	boolean hasToStop() {
		if (nowNode.right == null)
			return true;
		return false;
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
		if (nowNode.top == null || nowNode.top == nowNode.parent)
			return;
		nowNode.top.parent = nowNode;
		nowNode = nowNode.top;
		row--;
		pathnum--;
	}

	void setNowNodeDown() {
		if (nowNode.down == null || nowNode.down == nowNode.parent)
			return;
		nowNode.down.parent = nowNode;
		nowNode = nowNode.down;
		row++;
		pathnum--;
	}

	void setNowNodeRight() {
		if (nowNode.right == null)
			return;
		nowNode.right.parent = nowNode;
		nowNode = nowNode.right;
		col++;
		pathnum--;
	}

	void addTop() {
		// key++;
		if (row - 1 < 0 || map[row - 1][col] == 0) {
			nowNode.top = null;
			return;
		} else if (nowNode.top == nowNode.parent) {
			return;
		}
		TreeNode node = new TreeNode(key++, map[row - 1][col]);
		nowNode.top = node;
		pathnum++;
	}

	void addRight() {
		// key++;
		if (col + 1 == map.length || map[row][col + 1] == 0) {
			nowNode.right = null;
			return;
		}
		TreeNode node = new TreeNode(key++, map[row][col + 1]);
		nowNode.right = node;
		pathnum++;
	}

	void addDown() {
		// key++;
		if (row + 1 == map.length || map[row + 1][col] == 0) {
			nowNode.down = null;
			return;
		}else if (nowNode.down == nowNode.parent) {
			return;
		}
		TreeNode node = new TreeNode(key++, map[row + 1][col]);
		nowNode.down = node;
		pathnum++;
	}
}

class Map {
	int[][] map;
	int size;
	Scanner sc = new Scanner(System.in);

	Map() {
		out.print("�п�J�a�Ϥj�p : ");
		size = sc.nextInt();
		map = new int[size][size];

		out.printf("\n�п�J��l������ : \n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}
}