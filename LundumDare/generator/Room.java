class Room {
	public static final int ROOM_SIZE = 9;

	String[][] tiles = new String[ROOM_SIZE][ROOM_SIZE];
	int weight = 1;
	int type;
	boolean north = true;
	boolean east = true;
	boolean south = true;
	boolean west = true;

	@Override
	public String toString() {
		String s = "";
		for (int x = 0; x < ROOM_SIZE; x++) {
			for (int y = 0; y < ROOM_SIZE; y++) {
				s += tiles[x][y];
			}
			s += "\n";
		}
		return s;
	}

	static Room rotateRight(Room r, int amount) {
		Room n = new Room();
		n.weight = r.weight;
		n.type = r.type;

		n.north = r.west;
		n.east = r.north;
		n.south = r.east;
		n.west = r.south;

		String[][] t = new String[ROOM_SIZE][ROOM_SIZE];
		for (int i = 0; i < r.tiles[0].length; i++) {
			for (int j = 0; j < r.tiles.length; j++) {
				t[i][r.tiles.length - 1 - j] = r.tiles[j][i];
			}
		}
		n.tiles = t;

		if (amount == 1) {
			return n;
		} else {
			return rotateRight(n, amount - 1);
		}
	}

}