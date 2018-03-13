import java.util.ArrayList;
import java.util.Random;

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

		if (get(x, y - 1, grid) != null) {
			top = get(x, y - 1, grid);
		}
		if (get(x + 1, y, grid) != null) {
			right = get(x + 1, y, grid);
		}
		if (get(x, y + 1, grid) != null) {
			bottom = get(x, y + 1, grid);
		}
		if (get(x - 1, x, grid) != null) {
			left = get(x - 1, y, grid);
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

	public Cell get(int x, int y, ArrayList<Cell> grid) {
		for (int i = 0; i < grid.size(); i++) {
			if (grid.get(i).x == x && grid.get(i).y == y) {
				return grid.get(i);
			}
		}
		return null;
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