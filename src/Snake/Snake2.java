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
	int pathnum = 0; // �Ҧ��i����|�ƥءA���s�ɴN�����F
	boolean walkDownSignal = true;
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
		out.printf("�X�o�I�� : (%d, %d)�C\n", row, col);
		root = new TreeNode(key++, map[row][col]);
		root.parent = root;
		nowNode = root;
		addAll();
		while (pathnum > 0) {
			out.printf("�ثe�٦� %d �إi�ॼ�����C\n", pathnum);
			if (walkDownSignal == true) {
				while (walkDownSignal == true) {
					if (nowNode.downSignal == true) {
						nowNode.downSignal = false;
						walk(direction.DOWN);
						out.printf("d�{�b��m :(%d, %d);\n", row, col);
					}
				}
				out.println("1");
			} else if (walkDownSignal == false) {
				while (walkDownSignal == false) {
					if (nowNode.topSignal == true) {
						nowNode.topSignal = false;
						walk(direction.UP);
						out.printf("u�{�b��m :(%d, %d);\n", row, col);
					}
				}
				out.println("2");
			}
			out.println("3");
			if (nowNode.rightSignal == true) {
				nowNode.rightSignal = false;
				out.printf("r�{�b��m :(%d, %d);\n", row, col);
				walk(direction.RIGHT);
				walkDownSignal = !walkDownSignal;
				out.printf("r�{�b��m :(%d, %d);\n", row, col);
			}
			out.printf("�ثe�٦� %d �إi�ॼ�����C\n", pathnum);
			goBack();
			// TODO Sum
		}
		out.printf("��������, �@��%d�إi��C\n", walkTime);
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
			out.println("�V�W�L����");
			nowNode.top = null;
			return;
		} else if (walkDownSignal == true) {
			out.println("�V�W�^����`�I");
			return;
		}
		TreeNode node = new TreeNode(key++, map[row - 1][col]);
		out.println("�V�W�����i��");
		nowNode.top = node;
		nowNode.topSignal = true;
		pathnum++;
		walkTime++;
	}

	void addRight() {
		// key++;
		if (col + 1 == map.length || map[row][col + 1] == 0) {
			out.println("�V�k�L����");
			nowNode.right = null;
			return;
		}
		TreeNode node = new TreeNode(key++, map[row][col + 1]);
		out.println("�V�k�����i��");
		nowNode.right = node;
		nowNode.rightSignal = true;
		pathnum++;
		walkTime++;
	}

	void addDown() {
		// key++;
		if (row + 1 == map.length || map[row + 1][col] == 0) {
			out.println("�V�U�L����");
			nowNode.down = null;
			return;
		} else if (walkDownSignal == false) {
			out.println("�V�U�^����`�I");
			return;
		}
		TreeNode node = new TreeNode(key++, map[row + 1][col]);
		out.println("�V�U�����i��");
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