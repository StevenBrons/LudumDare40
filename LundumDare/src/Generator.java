import java.util.ArrayList;
import java.util.Random;

public class Generator {

	String roomsString = Loader.getLevelFile();
	int width;
	int height;

	ArrayList<Cell> grid = new ArrayList<>();
	ArrayList<Cell> stack = new ArrayList<Cell>();

	Cell current;

	public Generator() {
		width = 3;
		height = 3;
	}

	public void generate() {

		grid.clear();
		stack.clear();

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				Cell cell = new Cell(x, y, width, height);
				grid.add(cell);

			}
		}
		current = grid.get(0);
		while (true) {
			Cell next = current.checkNeighbors(grid);
			if (next != null) {
				next.visited = true;

				stack.add(current);

				removeWalls(current, next);

				current = next;

			} else if (stack.size() > 0) {
				current = stack.get(stack.size() - 1);
				stack.remove(stack.size() - 1);
			} else if (stack.size() == 0) {
				break;
			}
		}

	}

	public void removeWalls(Cell a, Cell b) {
		int x = a.x - b.x;
		int y = a.y - b.y;

		if (x == 1) {
			a.west = false;
			b.east = false;
		} else if (x == -1) {
			a.east = false;
			b.west = false;
		}

		if (y == 1) {
			a.north = false;
			b.south = false;
		} else if (y == -1) {
			a.south = false;
			b.north = false;
		}
	}

}

class Cell {

	boolean north = true;
	boolean east = true;
	boolean south = true;
	boolean west = true;
	int x;
	int y;
	boolean visited = false;

	int width;
	int height;

	public Cell(int i, int j, int w, int h) {
		x = i;
		y = j;
		width = w;
		height = h;
	}

	public Cell checkNeighbors(ArrayList<Cell> grid) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();

		Cell top = null;
		Cell right = null;
		Cell bottom = null;
		Cell left = null;

		if (index(x, y - 1) != -1) {
			top = grid.get(index(x, y - 1));
		}
		if (index(x + 1, y) != -1) {
			right = grid.get(index(x + 1, y));
		}
		if (index(x, y + 1) != -1) {
			bottom = grid.get(index(x, y + 1));
		}
		if (index(x - 1, x) != -1) {
			left = grid.get(index(x - 1, y));
		}

		if (top != null && !top.visited) {
			neighbors.add(top);
		}
		if (right != null && !right.visited) {
			neighbors.add(right);
		}
		if (bottom != null && !bottom.visited) {
			neighbors.add(bottom);
		}
		if (left != null && !left.visited) {
			neighbors.add(left);
		}

		if (neighbors.size() > 0) {
			Random rndm = new Random();
			int r = rndm.nextInt(neighbors.size());
			return neighbors.get(r);
		} else {
			return null;
		}

	}

	public int index(int x, int y) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
			return -1;
		}
		return x + y * height;
	}

	@Override
	public String toString() {
		return "Cell: " + x + ":" + y + " [n:" + north + ",e:" + east + ",s:" + south + ",w:" + west + "]";
	}

}

class Room {

	String[][] room = new String[9][9];

	boolean north = false;
	boolean east = false;
	boolean south = false;
	boolean west = false;

	public Room(String r) {

	}

}
